// package com.financeapi.filter;

// import com.financeapi.model.User;
// import com.financeapi.repository.UserRepository;
// import org.springframework.stereotype.Component;

// import javax.servlet.*;
// import javax.servlet.annotation.WebFilter;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;

// @WebFilter("/*")
// @Component
// public class LoginFilter implements Filter {

//     private final UserRepository userRepository;

//     public LoginFilter(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     @Override
//     public void init(FilterConfig filterConfig) throws ServletException {
//         // Nenhuma inicialização necessária
//     }

//     @Override
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//             throws IOException, ServletException {

//         HttpServletRequest httpRequest = (HttpServletRequest) request;
//         HttpServletResponse httpResponse = (HttpServletResponse) response;

//         String email = httpRequest.getUserPrincipal() != null ? httpRequest.getUserPrincipal().getName() : null;

//         if (email != null) {
//             User user = userRepository.findByEmail(email);
            
//             if (user != null && Boolean.TRUE.equals(user.getLogged())) {  // Verifique se getLogged() existe
//                 chain.doFilter(request, response);  // Permite o acesso
//             } else {
//                 httpResponse.sendRedirect("/login?error");  // Redireciona para login se o usuário não estiver logado
//             }
//         } else {
//             httpResponse.sendRedirect("/login?error");  // Se não houver usuário autenticado, redireciona para o login
//         }
//     }

//     @Override
//     public void destroy() {
//         // Nenhuma ação necessária
//     }
// }
