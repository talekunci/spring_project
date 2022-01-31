package ua.goit.springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.goit.springproject.dto.ProductDto;
import ua.goit.springproject.model.User;
import ua.goit.springproject.services.ProducerService;
import ua.goit.springproject.services.ProductService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private ProducerService producerService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("products", service.getAll());

        return "products";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Long id,
                      Model model,
                      HttpServletResponse response) throws IOException {

        boolean isAdmin = User.isAdmin(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (!isAdmin) {
            response.sendRedirect("/products");
            return "products";
        }


        model.addAttribute("product", service.get(id));
        model.addAttribute("producers", producerService.getAll());

        return "product";
    }

    @GetMapping("/new")
    public String getNew(Model model,
                         HttpServletResponse response) throws IOException {

        boolean isAdmin = User.isAdmin(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (!isAdmin) {
            response.sendRedirect("/products");
            return "products";
        }


        model.addAttribute("product", new ProductDto());
        model.addAttribute("producers", producerService.getAll());

        return "product";
    }

    @PostMapping
    public void create(@Valid @RequestBody ProductDto dto) {

        boolean isAdmin = User.isAdmin(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (!isAdmin) return;


        service.create(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody ProductDto dto) {

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
