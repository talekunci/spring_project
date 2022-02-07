package ua.goit.springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.goit.springproject.config.AccessForAdmin;
import ua.goit.springproject.dto.ProducerDto;
import ua.goit.springproject.services.ProducerService;

import javax.validation.Valid;

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
    @AccessForAdmin
    public String get(@PathVariable Long id,
                      Model model) {

        model.addAttribute("producer", service.get(id));

        return "producer";
    }

    @GetMapping("/new")
    @AccessForAdmin
    public String getNew(Model model) {

        model.addAttribute("producer", new ProducerDto());

        return "producer";
    }

    @PostMapping
    @AccessForAdmin
    public void create(@Valid @RequestBody ProducerDto dto) {
        service.create(dto);
    }

    @PutMapping("/{id}")
    @AccessForAdmin
    public void update(@PathVariable Long id,
                       @RequestBody ProducerDto dto) {

        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @AccessForAdmin
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
