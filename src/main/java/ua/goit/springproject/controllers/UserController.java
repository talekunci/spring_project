package ua.goit.springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.goit.springproject.dto.UserDto;
import ua.goit.springproject.model.User;
import ua.goit.springproject.services.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", service.getAllDto());

        return "users";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Long id,
                      Model model,
                      HttpServletResponse response) throws IOException {

        boolean isAdmin = User.isAdmin(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (!isAdmin) {
            response.sendRedirect("/users");
            return "users";
        }

        model.addAttribute("user", service.get(id));
        model.addAttribute("rolesList", service.getRoles());

        return "user";
    }

    @GetMapping("/new")
    public String getNew(Model model,
                         HttpServletResponse response) throws IOException {

        boolean isAdmin = User.isAdmin(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (!isAdmin) {
            response.sendRedirect("/users");
            return "users";
        }

        model.addAttribute("user", new UserDto());
        model.addAttribute("rolesList", service.getRoles());

        return "user";
    }

    @PostMapping
    public void create(@Valid @RequestBody UserDto dto) {

        boolean isAdmin = User.isAdmin(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (!isAdmin) return;

        service.create(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody UserDto dto) {

        boolean isAdmin = User.isAdmin(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (!isAdmin) return;

        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        boolean isAdmin = User.isAdmin(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (!isAdmin) return;

        service.delete(id);
    }
}
