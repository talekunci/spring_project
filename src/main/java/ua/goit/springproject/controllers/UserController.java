package ua.goit.springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.goit.springproject.config.AccessForAdmin;
import ua.goit.springproject.dto.RoleDto;
import ua.goit.springproject.dto.UserDto;
import ua.goit.springproject.model.Role;
import ua.goit.springproject.services.RoleService;
import ua.goit.springproject.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
@AccessForAdmin
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private RoleService roleService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", service.getAllDto());

        return "users";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Long id,
                      Model model) {

        model.addAttribute("user", service.get(id));

        return "user";
    }

    @GetMapping("/new")
    public String getNew(Model model) {
        model.addAttribute("user", new UserDto());

        return "user";
    }

    @PostMapping
    public String create(@Valid @RequestBody UserDto dto, BindingResult br,
                         Model model) {

        if (br.hasErrors()) {
            model.addAttribute("user", dto);
            model.addAttribute("errorMessage", br.getAllErrors().toString());

            return "user";
        }

        service.create(dto);
        return "users";
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @Valid @RequestBody UserDto dto) {

        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

//    Roles

    @GetMapping("/roles/{id}")
    public String getRoles(@PathVariable Long id,
                           Model model) {

        UserDto dto = service.get(id);

        model.addAttribute("user", dto);
        model.addAttribute("userRoles", dto.getRoles());
        model.addAttribute("roles", roleService.getAll());

        return "userRoles";
    }

    @PutMapping("/roles/{id}")
    public void addRole(@PathVariable Long id,
                        @RequestParam Long roleId) {

        UserDto user = service.get(id);
        Role role = roleService.getById(roleId);

        if (user != null && role != null
                && !user.getRoles().contains(role)) {
            user.getRoles().add(role);

            service.update(id, user);
        }
    }

    @DeleteMapping("/roles/{id}")
    public void removeRole(@PathVariable Long id,
                           @RequestParam Long roleId) {

        UserDto user = service.get(id);
        RoleDto role = roleService.getDto(roleId);

        if (user != null && role != null
                && !role.getName().equals("User")) {

            user.getRoles().removeIf(r -> r.getId().equals(roleId));

            service.update(id, user);
        }
    }
}
