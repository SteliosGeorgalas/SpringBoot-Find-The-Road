package gr.mindthecode.findtheroad.controllers.MVC;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Authentication check on backend if needed
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "home";
        }
        return "home";
    }
}
