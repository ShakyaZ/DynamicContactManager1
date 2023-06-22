package com.work.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.work.demo.Model.UserInfo;
import com.work.demo.Repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private UserRepository UR;

	@GetMapping("/login")
	public String loadLogin() {
		return "Login";
	}

	@PostMapping("/login")
	public String loggedUser(@ModelAttribute UserInfo UI, Model model, HttpSession session) {
		UserInfo USI = UR.findByUsernameAndPassword(UI.getUsername(), UI.getPassword());
		
		if (USI != null) {
			session.setAttribute("User", USI);
			return "NewDesign";
		} else {
			model.addAttribute("ERROR", "Login Unsucessful");
			return "Login";
		}

	}
}
