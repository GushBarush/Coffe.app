package com.example.coffeapp.controllers;

import com.example.coffeapp.dto.payday.PayDayDTO;
import com.example.coffeapp.service.OrderService;
import com.example.coffeapp.service.PayDayService;
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

    public PayDayController(PayDayService payDayService, OrderService orderService) {
        this.payDayService = payDayService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getPayDay(Model model) {
        PayDayDTO payDayDTO = payDayService.getCurrentPayDay();

        if (payDayDTO != null){
            model.addAttribute("PayDayDTO", payDayDTO);
            model.addAttribute("orders", orderService.allOrder());
            return "currentPayDay";
        }
        return "payDay";
    }

    @PostMapping
    public String newPayDay(Principal principal) {
        payDayService.getNewPayDay(principal.getName());

        return "redirect:/payday";
    }

    @PostMapping("/end")
    public String endPayDay(@RequestParam(name = "id") Long id) {
        payDayService.endPayDay(id);

        return "redirect:/payday";
    }
}
