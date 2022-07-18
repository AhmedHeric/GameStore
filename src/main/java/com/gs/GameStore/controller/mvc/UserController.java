package com.gs.GameStore.controller.mvc;

import com.gs.GameStore.controller.entity.User;
import com.gs.GameStore.controller.repository.UserRepository;
import com.gs.GameStore.controller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/")
//    public String viewUsers() {
//        return "index";
//    }

    @GetMapping("/")
    public String viewAdmin() {
        return "admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Neisparavan korisnički ID: " + id));
        userRepository.delete(user);
        return "redirect:/";
    }

    @GetMapping("/users")
    public String viewUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("listaKorisnika", userList);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        User user = new User();
        model.addAttribute("prazanUser", user);
        return "register";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("prazanUser") User user) {
        String plainPassword = user.getPassword();
        String hashedPassword = passwordEncoder.encode(plainPassword);
        user.setPassword(hashedPassword);
        user.setPrivilege("USER");
        userService.saveUser(user);
        return "redirect:/";
    }
}
