// package com.financeapi.controller;

// import com.financeapi.model.User;
// import com.financeapi.repository.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestMapping;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     @Autowired
//     private UserRepository userRepository;

//     @PostMapping("/login")
//     public String login(@RequestParam String email, @RequestParam String password) {
//         // Buscar usuário no banco
//         User user = userRepository.findByEmail(email);

//         if (user != null && user.getPassword().equals(password)) {
//             user.setLogged(true);
//             userRepository.save(user);
            
//             return "redirect:/home";
//         } else {
//             return "redirect:/login?error";
//         }
//     }
//      @PostMapping("/logout")
//     public String logout(@RequestParam String email) {
//         User user = userRepository.findByEmail(email);
        
//         if (user != null) {
//             user.setLogged(false);
//             userRepository.save(user);
//         }

//         return "redirect:/login";  // Redireciona para a página de login após o logout
//     }
// }
