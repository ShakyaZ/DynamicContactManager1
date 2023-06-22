package com.work.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.work.demo.Model.UserInfo;
import com.work.demo.Repository.UserRepository;

@Controller
public class SignUpController {
	
	@Autowired
	private UserRepository UR;
	@GetMapping("/signup")
    public String openSignUp() {
    	return "SignUp";
    }
	
	
	@PostMapping("/signup")
	public String userSignUp(@ModelAttribute UserInfo UI, Model model) {
		
		UR.save(UI);
		model.addAttribute("message", "SignUp successful");
		return  "SignUp";
	}
}
