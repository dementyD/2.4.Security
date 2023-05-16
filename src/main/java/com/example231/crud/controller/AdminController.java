package com.example231.crud.controller;


import com.example231.crud.model.User;
import com.example231.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String getAllUser(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "admin";
    }

    @GetMapping("/admin/new")
    public String getNew(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/admin/{id}")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/delete")
    public String delete(@PathVariable(value = "id") Long id, Model model) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

}
