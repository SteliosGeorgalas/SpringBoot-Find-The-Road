package gr.mindthecode.findtheroad.controllers.MVC;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController1 {

    @GetMapping("/login")
    public String home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //If not logged in yet return
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }
        return "home";
    }
}
