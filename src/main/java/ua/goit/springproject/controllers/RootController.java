package ua.goit.springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.goit.springproject.dto.UserDto;
import ua.goit.springproject.services.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/")
public class RootController {

    @Autowired
    private UserService service;

    @GetMapping
    public String get(Model model, HttpServletResponse response) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean authenticated = authentication.isAuthenticated() && authentication.getAuthorities()
                .stream()
                .anyMatch(role -> {
                    boolean admin = role.getAuthority().equalsIgnoreCase("Admin");
                    boolean user = role.getAuthority().equalsIgnoreCase("User");

                    return admin || user;
                });

        if (!authenticated) {
            Map<String, String> buttons = Map.of(
                    "/login", "Log in",
                    "/registration", "Register"
            );

            model.addAttribute("buttons", buttons);
        } else {
            response.sendRedirect("/users");
            return "users";
        }

        return "welcomePage";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("defaultRoleId", service.getRoleByName("Admin").getId());

        return "registration";
    }

    @PostMapping("/registration")
    public void registerUser(@Valid @RequestBody UserDto dto, HttpServletResponse response) throws IOException {
        service.registerNewAccount(dto);

        response.sendRedirect("/login");
    }

}

