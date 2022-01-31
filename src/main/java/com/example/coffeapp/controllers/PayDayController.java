package com.example.coffeapp.controllers;

import com.example.coffeapp.dto.payday.PayDayDTO;
import com.example.coffeapp.service.PayDayService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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
        PayDayDTO payDayDTO = payDayService.getNewPayDay(principal.getName());

        model.addAttribute("PayDayDTO", payDayDTO);
        return "redirect:/payday";
    }

    @PostMapping("/end")
    public String endPayDay(@RequestParam(name = "id") Long id) {
        payDayService.endPayDay(id);

        return "redirect:/payday";
    }
}
