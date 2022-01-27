package com.example.coffeapp.controllers;

import com.example.coffeapp.dto.payday.PayDayDTO;
import com.example.coffeapp.service.PayDayService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/payday")
@PreAuthorize("hasAuthority('BARISTA')")
public class PayDayController {

    final PayDayService payDayService;

    public PayDayController(PayDayService payDayService) {
        this.payDayService = payDayService;
    }

    @GetMapping
    public String getPayDay(Model model) {
        PayDayDTO payDayDTO = payDayService.getCurrentPayDay();

        if (payDayDTO != null){
            model.addAttribute("PayDayDTO", payDayDTO);
            return "currentPayDay";
        }
        return "payDay";
    }

    @PostMapping
    public String newPayDay(Principal principal, Model model) {
        model.addAttribute("PayDayDTO", payDayService.getNewPayDay(principal.getName()));

        return "currentPayDay";
    }
}
