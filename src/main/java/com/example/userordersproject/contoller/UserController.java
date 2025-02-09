package com.example.userordersproject.contoller;


import com.example.userordersproject.dto.CredentialsDTO;
import com.example.userordersproject.dto.UserInsertDTO;
import com.example.userordersproject.dto.UserReadOnlyDTO;
import com.example.userordersproject.exceptions.UserAlreadyExistsException;
import com.example.userordersproject.model.User;
import com.example.userordersproject.model.enums.Role;
import com.example.userordersproject.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userService;


    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }


    @PostMapping("/login")
    public String loginUser(@ModelAttribute CredentialsDTO loginDTO, Model model) {
        try {
            User user = userService.getUserByUsername(loginDTO.getUsername());
            String password = loginDTO.getPassword();
            if (user != null && passwordEncoder.matches(password, user.getPassword()))  {
                if (user.getRole().equals(Role.ADMIN)) {
                    return "redirect:/admin-dashboard";
                } else  {
                    return "redirect:/dashboard";
                }
            } else {
                model.addAttribute("error", "Invalid credentials");
                return "auth/login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Invalid credentials");
            return "auth/login";
        }
    }
    @GetMapping("/signup")
    public String showSignUpPage(Model model) {
        model.addAttribute("userInsertDTO", new UserInsertDTO());
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String createUser(@ModelAttribute UserInsertDTO userInsertDTO, Model model) {
        try {
            userService.createUser(userInsertDTO);
            return "redirect:/login";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("error", "User already exists!");
            return "auth/signup";
        }
    }

}
