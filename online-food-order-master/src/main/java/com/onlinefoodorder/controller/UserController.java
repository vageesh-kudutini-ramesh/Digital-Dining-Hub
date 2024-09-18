/**
 * Controller class for handling user-related actions such as login, registration, and password recovery.
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
import com.onlinefoodorder.dao.UserDao;
import com.onlinefoodorder.model.User;

@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * Handles GET requests to "/userlogin" and returns the user login page.
	 * @return The name of the user login page.
	 */
	@GetMapping("/userlogin")
	public String goToLoginPage() {
		return "userlogin";
	}
	
	/**
	 * Handles GET requests to "/userregister" and returns the user registration page.
	 * @return The name of the user registration page.
	 */
	@GetMapping("/userregister")
	public String goToRegisterPage() {
		return "userregister";
	}
	
	/**
	 * Handles GET requests to "/logout" and logs out the user.
	 * @param session The HttpSession to invalidate.
	 * @return A ModelAndView with the status message and view name.
	 */
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		session.removeAttribute("active-user");
		session.removeAttribute("user-login");
		mv.addObject("status","Logged out Successfully");
		mv.setViewName("index");
		
		return mv;
	}
	
	/**
	 * Handles POST requests to "/userregister" and registers a new user.
	 * @param user The User object containing user registration details.
	 * @return A ModelAndView with the registration status and view name.
	 */
	@PostMapping("/userregister")
	public ModelAndView registerAdmin(@ModelAttribute User user) {
		ModelAndView mv = new ModelAndView();
		//User savedUser = userDao.save(user);
		if(this.userDao.save(user)!= null) {
			mv.addObject("status", user.getFirstname()+" Successfully Registered!");
			mv.setViewName("userlogin");
		}
		
		else {
			mv.addObject("status", user.getFirstname()+" Failed to Registered User!");
			mv.setViewName("userregister");
	
		}
		
		
		return mv;
	}
	
	/**
	 * Handles POST requests to "/forgetpassword" and updates the user's password.
	 * @param forgetPassDetail The User object containing email, mobile, and new password.
	 * @return A ModelAndView with the password change status and view name.
	 */
	@PostMapping("/forgetpassword")
	public ModelAndView forgetpassword(@ModelAttribute User forgetPassDetail) {
		ModelAndView mv = new ModelAndView();
		
		User user = userDao.findByEmailidAndMobileno(forgetPassDetail.getEmailid(), forgetPassDetail.getMobileno());
		
		if(user != null) {
			user.setPassword(forgetPassDetail.getPassword());
			userDao.save(user);
			
			mv.addObject("status", "Password Changed");
			mv.setViewName("userlogin");
		}
		
		else {
			mv.addObject("status", "No User found!");
			mv.setViewName("userregister");
		}
		
		return mv;
	}
	
	/**
	 * Handles POST requests to "/userlogin" and authenticates the user.
	 * @param request The HttpServletRequest object.
	 * @param emailId The user's email.
	 * @param password The user's password.
	 * @return A ModelAndView with the login status and view name.
	 */
	@PostMapping("/userlogin")
	public ModelAndView loginAdmin(HttpServletRequest request, @RequestParam("emailid") String emailId, @RequestParam("password") String password ) {
		ModelAndView mv = new ModelAndView();
		
		User user = userDao.findByEmailidAndPassword(emailId, password);
		
		/*
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("active-user", user);
			session.setAttribute("user-login","user");
			mv.addObject("status", user.getFirstname()+" Successfully Logged In!");
			mv.setViewName("index");
		}
		*/
		if(user != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("active-user", user);
	        session.setAttribute("user-login","user");

	        // Check if firstname is null and handle it appropriately
	        if (user.getFirstname() != null) {
	            mv.addObject("status", user.getFirstname() + " Successfully Logged In!");
	        } else {
	            mv.addObject("status", "Successfully Logged In!");
	        }

	        mv.setViewName("index");
	    }
		
		else {
			mv.addObject("status","Failed to login!");
			mv.setViewName("index");
		}
		
		return mv;
	}
	

}
