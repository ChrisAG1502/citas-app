package azc.uam.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping("/cliente")
    public String dashboardCliente(Authentication authentication, Model model) {
        model.addAttribute("usuario", authentication.getName());
        return "dashboard-cliente";
    }

    @GetMapping("/profesional")
    public String dashboardProfesional(Authentication authentication, Model model) {
        model.addAttribute("usuario", authentication.getName());
        return "dashboard-profesional";
    }

}
