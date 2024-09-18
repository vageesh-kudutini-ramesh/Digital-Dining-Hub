/**
 * The AdminController class handles requests related to admin functionalities in the online food ordering system.
 */
package com.onlinefoodorder.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.onlinefoodorder.dao.AdminDao;
import com.onlinefoodorder.model.Admin;

/**
 * Controller class for handling admin-related requests and navigation.
 */
@Controller
public class AdminController {
	
	@Autowired
	private AdminDao adminDao;
	
	/**
	 * Handles the GET request for the root ("/") endpoint, redirecting to the home page.
	 * @return The logical view name "index" representing the home page.
	 */
	
	@GetMapping("/")
	public String goToHomeDuringStart() {
		return "index";
	}
	
	/**
	 * Handles the GET request for the "/home" endpoint, redirecting to the home page.
	 * @return The logical view name "index" representing the home page.
	 */
	
	@GetMapping("/home")
	public String goToHome() {
		return "index";
	}
	
	/**
	 * Handles the GET request for the "/admindashboard" endpoint, redirecting to the admin dashboard.
	 * @return The logical view name "admin" representing the admin dashboard.
	 */
	
	@GetMapping("/admindashboard")
	public String goToAdminPage() {
		return "admin";
	}
	
	/**
	 * Handles the GET request for the "/adminlogin" endpoint, redirecting to the admin login page.
	 * @return The logical view name "adminlogin" representing the admin login page.
	 */
	
	@GetMapping("/adminlogin")
	public String goToAdminLoginPage() {
		
		return "adminlogin";
	}
	
	/**
	 * Handles the GET request for the "/adminregister" endpoint, redirecting to the admin registration page.
	 * @return The logical view name "adminregister" representing the admin registration page.
	 */
	@GetMapping("/adminregister")
	public String goToAdminRegisterPage() {
		
		return "adminregister";
	}
	
	/**
	 * Handles the POST request for the "/adminregister" endpoint, registering a new admin.
	 * @param admin The Admin object containing registration details.
	 * @return A ModelAndView object with a status message and a view name.
	 */
	@PostMapping("/adminregister")
	public ModelAndView registerAdmin(@ModelAttribute Admin admin) {
		ModelAndView mv = new ModelAndView();
		if(this.adminDao.save(admin)!= null) {
			mv.addObject("status", admin.getFirstname()+" Successfully Registered as ADMIN");
			mv.setViewName("adminlogin");
		}
		
		else {
			mv.addObject("status", admin.getFirstname()+" Failed to Registered as ADMIN");
			mv.setViewName("adminregister");
	
		}
		
		return mv;
	}
	
	/**
	 * Handles the POST request for the "/adminlogin" endpoint, authenticating admin login.
	 * @param request The HttpServletRequest object.
	 * @param emailId The email ID parameter for admin login.
	 * @param password The password parameter for admin login.
	 * @return A ModelAndView object with a status message and a view name.
	 */
	@PostMapping("/adminlogin")
	public ModelAndView loginAdmin(HttpServletRequest request, @RequestParam("emailid") String emailId, @RequestParam("password") String password ) {
		ModelAndView mv = new ModelAndView();
		
		Admin admin = adminDao.findByEmailidAndPassword(emailId, password);
		
		if(admin != null) {
			HttpSession session = request.getSession();
			session.setAttribute("active-user", admin);
			session.setAttribute("user-login","admin");
			mv.addObject("status", admin.getFirstname()+"Successfully Logged in as ADMIN!");
			mv.setViewName("index");
		}
		
		else {
			mv.addObject("status","Failed to login as Admin!");
			mv.setViewName("index");
		}
		
		return mv;
	}
	
}
