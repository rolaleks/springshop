package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.persist.enity.Role;
import ru.geekbrains.persist.service.interdafaces.RoleServiceInterface;

@RequestMapping("/role")
@Controller
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private RoleServiceInterface service;

    @Autowired
    public RoleController(RoleServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public String roleList(Model model) {
        logger.info("Role list");

        model.addAttribute("roles", service.findAll());
        return "role/roles";
    }

    @GetMapping("new")
    public String createRole(Model model) {
        logger.info("Create role form");

        model.addAttribute("role", new Role());

        return "role/form";
    }
    @GetMapping("update/{id}")
    public String editRole(@PathVariable("id") Long id, Model model) {

        model.addAttribute("role", service.findById(id).orElseThrow(() -> new NotFoundException("not found")));

        return "role/form";
    }


    @PostMapping
    public String saveRole(Role role) {
        logger.info("Save role method");

        service.save(role);
        return "redirect:/role";
    }
}
