package com.work.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.work.demo.Model.UserContacts;
import com.work.demo.Model.UserInfo;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {

	@GetMapping("/profile")
	public String loadProfile(HttpSession session, Model model) {
		UserInfo ui = (UserInfo) session.getAttribute("User");
		List<UserContacts> uc = ui.getContacts();
		int contactcount = uc.size();
		model.addAttribute("count", contactcount);
		model.addAttribute("user",ui);
		return "PersonalProfile";
	}
}
