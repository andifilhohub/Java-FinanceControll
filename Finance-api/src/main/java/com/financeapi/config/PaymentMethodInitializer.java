package com.financeapi.config;

import com.financeapi.model.PaymentMethod;
import com.financeapi.repository.PaymentMethodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PaymentMethodInitializer {

    @Bean
    public CommandLineRunner initializePaymentMethods(PaymentMethodRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                    new PaymentMethod("Crédito"),
                    new PaymentMethod("Débito"),
                    new PaymentMethod("Pix"),
                    new PaymentMethod("Dinheiro")
                ));
            }
        };
    }
}
