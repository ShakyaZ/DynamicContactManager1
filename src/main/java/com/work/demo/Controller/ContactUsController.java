package com.work.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.work.demo.Util.MyUtil;

@Controller
public class ContactUsController {

	@Autowired
	private MyUtil util;

	@GetMapping("/contactus")
	public String loadContactUs() {
		return "ContactUs";
	}

	@GetMapping("/sendemail")
	public String sendEmail(@RequestParam String To_email, @RequestParam String username,
			@RequestParam String useremail, @RequestParam String body) {

		util.sendEmailToUs(To_email, username, useremail, body);
		return "ContactUs";
	}
}
