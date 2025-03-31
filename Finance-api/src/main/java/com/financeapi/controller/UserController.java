package com.financeapi.controller;

import com.financeapi.model.User;
import com.financeapi.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserRepository userRepository; // Certifique-se de que o repositório está sendo injetado corretamente

    @PostMapping("/create") // Usando PostMapping para salvar dados
    public String saveUser(@RequestBody User user) {
    if (user != null) {
        //logger.info("Usuário salvo com sucesso: " + user.getName());
        userRepository.save(user);
        return "Usuário salvo com sucesso!"; // ✅ Agora retorna uma string
    }
    return "Erro: Usuário inválido"; // ✅ Retorno alternativo para evitar erros
}


}
