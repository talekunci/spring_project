package ua.goit.springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.goit.springproject.config.AccessForAdmin;
import ua.goit.springproject.dto.ProductDto;
import ua.goit.springproject.services.ProducerService;
import ua.goit.springproject.services.ProductService;

import javax.validation.Valid;

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
    @AccessForAdmin
    public String get(@PathVariable Long id,
                      Model model) {

        model.addAttribute("product", service.get(id));
        model.addAttribute("producers", producerService.getAll());

        return "product";
    }

    @GetMapping("/new")
    @AccessForAdmin
    public String getNew(Model model) {

        model.addAttribute("product", new ProductDto());
        model.addAttribute("producers", producerService.getAll());

        return "product";
    }

    @PostMapping
    @AccessForAdmin
    public void create(@Valid @RequestBody ProductDto dto) {
        service.create(dto);
    }

    @PutMapping("/{id}")
    @AccessForAdmin
    public void update(@PathVariable Long id,
                       @RequestBody ProductDto dto) {

        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @AccessForAdmin
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
