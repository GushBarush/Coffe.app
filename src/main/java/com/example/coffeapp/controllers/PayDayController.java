package com.example.coffeapp.controllers;

import com.example.coffeapp.dto.payday.PayDayDTO;
import com.example.coffeapp.service.OrderService;
import com.example.coffeapp.service.PayDayService;
import com.example.coffeapp.service.ProductService;
import com.example.coffeapp.telegram.MyCoffeeBot;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/payday")
@PreAuthorize("hasAuthority('BARISTA')")
public class PayDayController {

    final PayDayService payDayService;
    final OrderService orderService;
    final ProductService productService;
    final MyCoffeeBot myCoffeeBot;

    public PayDayController(PayDayService payDayService, OrderService orderService, ProductService productService, MyCoffeeBot myCoffeeBot) {
        this.payDayService = payDayService;
        this.orderService = orderService;
        this.productService = productService;
        this.myCoffeeBot = myCoffeeBot;
    }

    @GetMapping
    public String getPayDay(Model model) {
        PayDayDTO payDayDTO = payDayService.getCurrentPayDay();

        if (payDayDTO != null){
            model.addAttribute("PayDayDTO", payDayDTO);
            model.addAttribute("orders", orderService.allOrderByPayDay(payDayDTO.getId()));
            model.addAttribute("expenses", orderService.allExpenses(payDayDTO.getId()));
            return "currentPayDay";
        }
        return "payDay";
    }

    @PostMapping
    public String newPayDay(Principal principal) {
        PayDayDTO payDayDTO = payDayService.getNewPayDay(principal.getName());

        myCoffeeBot.openPayDay(payDayDTO);
        return "redirect:/payday";
    }

    @PostMapping("/end")
    public String endPayDay(@RequestParam(name = "id") Long id) {
        payDayService.endPayDay(id);

        return "redirect:/payday";
    }

    @GetMapping("/order/info")
    public String getOrderInfo(@RequestParam(name = "orderId") Long orderId, Model model) {

        model.addAttribute("order", orderService.getOrder(orderId));
        model.addAttribute("products", productService.allProduct(false));
        model.addAttribute("productsDop", productService.allProduct(true));

        return "orderForm";
    }
}
