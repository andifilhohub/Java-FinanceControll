package com.financeapi.controller;

import com.financeapi.model.PaymentMethod;
import com.financeapi.repository.PaymentMethodRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/paymentmethods")
public class PaymentMethodController {

    private final PaymentMethodRepository repository;

    public PaymentMethodController(PaymentMethodRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<PaymentMethod> getAllPaymentMethods() {
        return repository.findAll();
    }
}
