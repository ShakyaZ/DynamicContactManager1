package com.work.demo.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.work.demo.Model.UserContacts;
import com.work.demo.Model.UserInfo;
import com.work.demo.Repository.ContactsRepository;
import com.work.demo.Repository.UserRepository;
import com.work.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class ContactController {
	@Autowired
	private UserService US;
	@Autowired
	private UserRepository UR;
	@Autowired
	private ContactsRepository CR;

	@GetMapping("/addcontacts")
	public String loadAddContact() {
		return "AddContacts";
	}

	@PostMapping("/addcontacts")
	public String AddUserContact(@ModelAttribute UserContacts contact, Model model, HttpSession session,
			MultipartFile profile) {

		if (profile != null) {
			try {
				Files.copy(profile.getInputStream(),
						Path.of("src/main/resources/static/ProfileImages/" + profile.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		UserInfo usr = (UserInfo) session.getAttribute("User");
		contact.setUserinfo(usr);
		contact.setImage(profile.getOriginalFilename());
		List<UserContacts> a = usr.getContacts();
		a.add(contact);
		usr.setContacts(a);

		US.addUserContact(contact);
		model.addAttribute("success", "added sucessfully");

		return "AddContacts";
	}

	@GetMapping("/showcontacts")
	public String showContact(Model model, HttpSession session) {

		UserInfo user = (UserInfo) session.getAttribute("User");
		List<UserContacts> u = user.getContacts();
		System.out.println("Number of contacts: " + u.size());
		model.addAttribute("hello", u);
		return "ShowContacts";

	}

	@GetMapping("/edit")

	public String editForm(@RequestParam int id, Model model) {

		UserContacts UC = US.getUserContact(id);

		model.addAttribute("r_user", UC);
		return "EditForm";
	}

	@GetMapping("/delete")
	public String deleteContacts(@RequestParam int id, Model model, HttpSession session) {

		US.deleteUser(id);
		UserInfo user = (UserInfo) session.getAttribute("User");

		// update user's contacts list after deleting a contact
		List<UserContacts> contacts = user.getContacts();
		contacts.removeIf(contact -> contact.getId() == id);
		user.setContacts(contacts);

		model.addAttribute("message", "user deleted sucessfully");
		return "redirect:/showcontacts";
	}

	@PostMapping("/edit")
	public String updateUser(@ModelAttribute UserContacts uc, HttpSession session, MultipartFile profile) {
		if (profile != null) {
			try {
				Files.copy(profile.getInputStream(),
						Path.of("src/main/resources/static/ProfileImages/" + profile.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		int id = uc.getId();
		UserInfo user = (UserInfo) session.getAttribute("User");
		List<UserContacts> user_c = user.getContacts();
		user_c.removeIf(users -> users.getId() == id);
		uc.setImage(profile.getOriginalFilename());
		user_c.add(uc);
		user.setContacts(user_c);
		US.updateUser(uc);

		return "redirect:/showcontacts";

	}

	@GetMapping("/detail")

	public String loadPage(@RequestParam int id, Model model) {
		UserContacts UC = US.getUserContact(id);

		String profilephoto = UC.getImage();
		String[] imagesname = new File("src/main/resources/static/images").list();

		model.addAttribute("object", UC);
		model.addAttribute("receivedimage", profilephoto);
		System.out.println(profilephoto);
		return "DetailPage";

	}

}
