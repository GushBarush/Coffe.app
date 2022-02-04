package com.example.coffeapp.controllers;

import com.example.coffeapp.dto.order.OrderDTO;
import com.example.coffeapp.service.OrderService;
import com.example.coffeapp.service.ProductService;
import com.example.coffeapp.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
@PreAuthorize("hasAuthority('BARISTA')")
public class OrderController {

    final UserService userService;
    final OrderService orderService;
    final ProductService productService;

    public OrderController(UserService userService, OrderService orderService, ProductService productService) {
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping
    public String newOrder(@RequestParam(name = "id") Long payDayId,
                           @RequestParam(name = "isFree") Boolean isFree,
                           @RequestParam(name = "filter", required = false, defaultValue = "") String filter, Model model) {

        model.addAttribute("PayDayId", payDayId);
        model.addAttribute("filter", filter);

        if (isFree) {
            model.addAttribute("users", userService.userFilterFreeNumber(filter));
            return "newFreeOrder";
        } else  {
            model.addAttribute("users", userService.userFilterNumber(filter));
        }

        return "newOrder";
    }

    @PostMapping
    public String selectUser(@RequestParam(name = "userId") Long userId,
                             @RequestParam(name = "isFree") Boolean isFree,
                             @RequestParam(name = "payDayId") Long payDayId, Model model) {

        model.addAttribute("order", orderService.newOrder(userId, payDayId, isFree));
        model.addAttribute("products", productService.allProduct(false));
        model.addAttribute("productsDop", productService.allProduct(true));

        return "orderForm";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam(name = "orderId") Long orderId,
                             @RequestParam(name = "productId") Long productId,
                             @RequestParam(name = "size") String size, Model model) {

        OrderDTO orderDTO = orderService.updateOrder(orderId, productId, size);

        model.addAttribute("order", orderDTO);
        model.addAttribute("products", productService.allProduct(false));
        model.addAttribute("productsDop", productService.allProduct(true));

        return "orderForm";
    }

    @PostMapping("/clean")
    public String cleanOrder(@RequestParam(name = "orderId") Long orderId,
                            @RequestParam(name = "productPriceId") Long productPriceId, Model model) {

        OrderDTO orderDTO = orderService.cleanOrder(orderId, productPriceId);

        model.addAttribute("order", orderDTO);
        model.addAttribute("products", productService.allProduct(false));
        model.addAttribute("productsDop", productService.allProduct(true));

        return "orderForm";
    }

    @PostMapping("/save")
    public String saveOrder(@RequestParam(name = "orderId") Long orderId,
                            @RequestParam(name = "isCash") Boolean isCash) {

        orderService.saveOrder(orderId, isCash);

        return "redirect:/payday";
    }

    @PostMapping("/delete")
    public String deleteOrder(@RequestParam(name = "orderId") Long orderId) {

        orderService.deleteOrder(orderId);

        return "redirect:/payday";
    }
}
