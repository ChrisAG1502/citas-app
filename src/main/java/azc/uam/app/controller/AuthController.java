package azc.uam.app.controller;

import azc.uam.app.dto.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("login", new LoginRequest());
        return "auth/login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginRequest") LoginRequest login, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "auth/login";
        }
        return "redirect:/dashboard";
    }
}
