package ua.goit.springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.goit.springproject.dto.ProducerDto;
import ua.goit.springproject.model.User;
import ua.goit.springproject.services.ProducerService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/producers")
public class ProducerController {

    @Autowired
    private ProducerService service;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("producers", service.getAll());

        return "producers";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Long id,
                      Model model,
                      HttpServletResponse response) throws IOException {

        boolean isAdmin = User.isAdmin(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (!isAdmin) {
            response.sendRedirect("/producers");
            return "producers";
        }

        model.addAttribute("producer", service.get(id));

        return "producer";
    }

    @GetMapping("/new")
    public String getNew(Model model,
                         HttpServletResponse response) throws IOException {

        boolean isAdmin = User.isAdmin(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (!isAdmin) {
            response.sendRedirect("/producers");
            return "producers";
        }

        model.addAttribute("producer", new ProducerDto());

        return "producer";
    }

    @PostMapping
    public void create(@Valid @RequestBody ProducerDto dto) {

        boolean isAdmin = User.isAdmin(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (!isAdmin) return;

        service.create(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody ProducerDto dto) {

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
