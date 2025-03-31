package com.financeapi.controller;

import com.financeapi.model.Notification;
import com.financeapi.model.Expenditures; 
import com.financeapi.model.PaymentMethod;  // Certifique-se de que o caminho esteja correto
import com.financeapi.repository.PaymentMethodRepository;
import com.financeapi.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONObject; 

//message = 'Sua compra no valor de R$ 8,09 em Uber* Pending Osasco Bra foi ESTORNADA no cartão de final 3022.';


@RestController
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private PaymentMethodRepository PaymentMethodRepository;

    @Value("${server.url}")
    private String serverUrl;  // Defina a variável de ambiente aqui fora do método

    @PostMapping("/notifications")
    public String receiveNotification(@RequestBody Notification notification) {
        if (notification != null) {
            notificationRepository.save(notification);
            
            
            // Aqui você cria o Expenditures com os dados extraídos
            Expenditures Expenditures = new Expenditures();
            JSONObject extractedData = extractValuesFromMessage(notification);

            //Preencha o Expenditures com os dados extraídos (ajuste conforme o seu modelo)
            Expenditures.setAmount(extractedData.getBigDecimal("amount"));
            Expenditures.setCompanyName(extractedData.getString("companyName"));
            Expenditures.setCategory(extractedData.getString("category"));
            Expenditures.setDescription(extractedData.getString("description"));
            Expenditures.setInstallmentsCount(extractedData.getInt("installmentsCount"));
            Expenditures.setDate(LocalDateTime.now());
            
            
            

            // Salvar Expenditures no banco
            

            // Agora envia os dados para o endpoint de Expenditures
            RestTemplate restTemplate = new RestTemplate();
            String url = getServerUrl("/expenditures");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Expenditures> request = new HttpEntity<>(Expenditures, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            // Verifique a resposta (se necessário)
            if (response.getStatusCode() == HttpStatus.OK) {
                logger.info("Expenditures saved successfully!");
            } else {
                logger.error("Failed to save Expenditures: " + response.getStatusCode());
            }
        }
        
        return "Notification received successfully!";
    }

    private String getServerUrl(String route) {
        return serverUrl + route;
    }
   


private JSONObject extractValuesFromMessage(Notification notification) {
    String message = notification.getMessage();

    JSONObject result = new JSONObject();
    
    
    // 1. Extrair o valor da compra (amount)
    Pattern amountPattern = Pattern.compile("R\\$ ([0-9,]+)");
    Matcher amountMatcher = amountPattern.matcher(message);
    if (amountMatcher.find()) {
        String amountString = amountMatcher.group(1).replace(",", ".");
        BigDecimal amount = new BigDecimal(amountString);
        result.put("amount", amount);
       
    }
    
    // 2. Extrair o nome da empresa (companyName)
    Pattern companyPattern = Pattern.compile("em (\\w+)");
    Matcher companyMatcher = companyPattern.matcher(message);
    String companyName = null;
    while (companyMatcher.find()) {
        companyName = companyMatcher.group(1);
    }
    if (companyName != null) {
        result.put("companyName", companyName);
        
    }

    // 3. Definir categoria (category)
    String description = notification.getAppName() + " - " + notification.getTitle();
    result.put("description", description);

    // 4. Extrair a descrição (description)
    Pattern categoryPattern = Pattern.compile("(APROVADA|ESTORNADA)");
    Matcher categoryMatcher = categoryPattern.matcher(message);
    if (categoryMatcher.find()) {
        String category = categoryMatcher.group(1);
        result.put("category", category);

    }

    // 5. Extrair o número de parcelas (installmentsCount)
    Pattern installmentsPattern = Pattern.compile("(\\d+)x");
    Matcher installmentsMatcher = installmentsPattern.matcher(message);
    if (installmentsMatcher.find()) {
        int installments = Integer.parseInt(installmentsMatcher.group(1));
        result.put("installmentsCount", installments);
        //logger.info("parcelas: " + installments);
    }
    else {
        result.put("installmentsCount", 1);
    }
    result.put("date", LocalDateTime.now());
    result.put("timeStamp", LocalDateTime.now());
    
    return result;  // Retorna o objeto JSON com os dados extraídos
}

}
