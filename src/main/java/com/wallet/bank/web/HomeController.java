package com.wallet.bank.web;

import com.wallet.bank.dto.CustomerDto;
import com.wallet.bank.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@AllArgsConstructor
public class HomeController {

    private final CustomerService customerService;

    @GetMapping("/")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "index";
    }

    @GetMapping("/customer/{id}")
    public String getCustomerById(@PathVariable Long id, Model model) {
        CustomerDto customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customerDetail";
    }
}
