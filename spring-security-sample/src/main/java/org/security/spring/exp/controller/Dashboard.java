package org.security.spring.exp.controller;

import org.security.spring.exp.bo.UserCredential;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Dashboard {

	@RequestMapping(value="/user/home", method = RequestMethod.GET)
	public String securedHome(ModelMap model) {
      /* // Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserCredential user=null;
        if (principal instanceof UserCredential ) {
        	user = ((UserCredential)principal);
        }*/
        //model.addAttribute("username", user.getUsername());
        model.addAttribute("username", "Hello");
        model.addAttribute("message", "welcome to our application");
        return "dashboard";
	}
	
}
