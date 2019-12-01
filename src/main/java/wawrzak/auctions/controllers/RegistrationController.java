package wawrzak.auctions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import wawrzak.auctions.dtos.UserRegistration;
import wawrzak.auctions.services.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/registration")
    public String postRegistration(@ModelAttribute("user") @Valid UserRegistration user, BindingResult result) {

        if (!user.getPassword().equals(user.getPasswordRepeat())) {
            result.rejectValue("password", "registration.unmatchedPasswords");
        } else if (userService.checkIfMailIsTaken(user.getEmail())) {
            result.rejectValue("email", "registration.emailExists");
        } else if (userService.checkIfNameIsTaken(user.getName())) {
            result.rejectValue("name", "registration.nameExists");
        } else if (!result.hasErrors()) {
            userService.createUser(user);
            return "redirect:/thank-you";
        }
        return "registration";

    }

    @GetMapping("/registration")
    public String getRegistration(Model model) {

        var user = new UserRegistration();
        model.addAttribute("user", user);

        return "registration";
    }

    @GetMapping("/thank-you")
    public String getThankYou() {
        return "thank-you";
    }
}
