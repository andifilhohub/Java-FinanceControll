package com.financeapi.controller;

import com.financeapi.model.Expenditures;
import com.financeapi.repository.ExpendituresRepository;
import com.financeapi.repository.PaymentMethodRepository;
import java.util.Map;
import java.util.HashMap;
import com.financeapi.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONObject;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import java.math.BigDecimal;



    @RestController
public class ExpendituresController {
     private static final Logger logger = LoggerFactory.getLogger(ExpendituresController.class);

    @Autowired
    private ExpendituresRepository expenditureRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @GetMapping("/expenditures/totalday")
    public Map<String, BigDecimal> getExpendituresTotal(@RequestParam(value = "date", required = false) String dateString) {
        // Se a data não for passada, usa a data atual
        LocalDate date = (dateString != null) ? LocalDate.parse(dateString) : LocalDate.now();
        Map<String, BigDecimal> response = new HashMap<>();
        response.put("total_day",expenditureRepository.getTotalExpendituresToday(date));
        return response;
    }

     @GetMapping("/expenditures/totalall")
    @ResponseBody
    public Map<String, BigDecimal> getExpendituresSummary() {
        BigDecimal total = expenditureRepository.getTotalExpenditures();

        Map<String, BigDecimal> response = new HashMap<>();
        response.put("total_all", total);


        return response;
    }

    @PostMapping("/expenditures")
    public String saveExpenditure(@RequestBody Expenditures expenditure) {
        PaymentMethod paymentMethod = getPaymentMethodFromTitle(expenditure.getDescription());
        expenditure.setPaymentMethod(paymentMethod);
        
        if ("APROVADA".equals(expenditure.getCategory())) {
            expenditureRepository.save(expenditure);

        }
        else if ("ESTORNADA".equals(expenditure.getCategory())) {
            LocalDateTime timeNow = LocalDateTime.now();
            LocalDateTime startTime = timeNow.minus(2, ChronoUnit.MINUTES);
            LocalDateTime endTime = timeNow;

            // Buscar uma "APROVADA" com os mesmos valores, empresa, categoria e timestamp
            Optional<Expenditures> expenditureToDelete = expenditureRepository
                .findByAmountAndCompanyNameAndCategoryAndDescriptionAndDateBetween(
                    expenditure.getAmount(),
                    expenditure.getCompanyName(),
                    "APROVADA", 
                    expenditure.getDescription(),
                    startTime, endTime
                );

            // Se encontrada, deletar a despesa "APROVADA"
            if (expenditureToDelete.isPresent()) {
                Expenditures expenditureToDeleteObj = expenditureToDelete.get();
                expenditureRepository.delete(expenditureToDeleteObj);
                logger.info("Despesa APROVADA deletada devido ao ESTORNO.");
            }
        }

        logger.info("Expenditure received: " + expenditure);
        return "Expenditure saved successfully!";
    }
    private PaymentMethod getPaymentMethodFromTitle(String title) {
        if (title.toLowerCase().contains("crédito")) {
            return paymentMethodRepository.findByName("Cartão de Crédito").orElse(null);
        } else if (title.toLowerCase().contains("débito")) {
            return paymentMethodRepository.findByName("Débito").orElse(null);
        } else if (title.toLowerCase().contains("pix")) {
            return paymentMethodRepository.findByName("Pix").orElse(null);
        } else if (title.toLowerCase().contains("dinheiro")) {
            return paymentMethodRepository.findByName("Dinheiro").orElse(null);
        }
        return null;
    }
}