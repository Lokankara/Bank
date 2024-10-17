package com.wallet.bank.web;

import com.wallet.bank.dto.CustomerDto;
import com.wallet.bank.service.CustomerBankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class HomeController extends BaseController {

    public HomeController(CustomerBankService customerBankService) {
        super(customerBankService);
    }

    @GetMapping("/")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerBankService.getAllCustomers());
        return "index";
    }

    @GetMapping("/customer/{id}")
    public String getCustomerById(@PathVariable Long id, Model model) {
        CustomerDto customer = customerBankService.getById(id);
        model.addAttribute("customer", customer);
        return "customerDetail";
    }
}
