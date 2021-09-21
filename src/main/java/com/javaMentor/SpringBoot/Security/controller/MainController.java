package com.javaMentor.SpringBoot.Security.controller;

import com.javaMentor.SpringBoot.Security.model.User;
import com.javaMentor.SpringBoot.Security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String getMain(Model model){
        model.addAttribute("users", userService.showAllUsers());
        return "mainPage";
    }

    @GetMapping("/user")
    public String getUserPage(Model model){
        model.addAttribute("user",  userService.findUserByName(userService.getCurrentUsername()));
        return "user";
    }

    @GetMapping("/admin/{id}")
    public String showUser(@PathVariable("id") Integer id, Model model){
        model.addAttribute("user", userService.showUser(id));
        return "showUser";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user){
        userService.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String updateUser(Model model, @PathVariable("id") Integer id){
        model.addAttribute("user", userService.showUser(id));
        return "editUser";
    }

    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return "redirect:/admin";

    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
