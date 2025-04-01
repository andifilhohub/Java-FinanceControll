// package com.financeapi.controller;

// import com.financeapi.model.User;
// import org.springframework.stereotype.Controller;
// import com.financeapi.repository.UserRepository;
// import javax.servlet.http.HttpServletRequest;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;

// @Controller
// public class HomeController {

//     @Autowired
//     private UserRepository userRepository;

//     @GetMapping("/home")
//     public String home(HttpServletRequest request) {
//         String email = request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : null;

//         if (email != null) {
//             User user = userRepository.findByEmail(email);
//             if (user != null && Boolean.TRUE.equals(user.getLogged())) {
//                 return "home";  // Exibe a página home se o usuário estiver logado
//             }
//         }
//         return "redirect:/login";  // Caso contrário, redireciona para o login
//     }
// }
