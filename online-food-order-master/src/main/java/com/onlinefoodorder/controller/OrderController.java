/**
 * Controller class for handling order-related operations.
 */
package com.onlinefoodorder.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodorder.dao.CartDao;
import com.onlinefoodorder.dao.OrderDao;
import com.onlinefoodorder.model.Cart;
import com.onlinefoodorder.model.Orders;
import com.onlinefoodorder.model.User;
import com.onlinefoodorder.utility.Constants;
import com.onlinefoodorder.utility.Helper;

@Controller
public class OrderController {
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private OrderDao orderDao;
    
	/**
     * Handles the order placement process.
     * 
     * @param session The HttpSession containing the active user information.
     * @return ModelAndView object with the result of the order placement.
     */
	@PostMapping("/order")
	public ModelAndView orderfoods(HttpSession session) {
        ModelAndView mv = new ModelAndView();
		
        User user = (User)session.getAttribute("active-user");
        
		String orderId = Helper.getAlphaNumericOrderId(10);
		String orderedDate = LocalDate.now().toString();
		
		List<Cart> carts = cartDao.findByUserId(user.getId());
		
		for(Cart cart : carts) {
			Orders order = new Orders();
			order.setOrderDate(orderedDate);
			order.setOrderId(orderId);
			order.setUserId(user.getId());
			order.setQuantity(cart.getQuantity());
			order.setFoodId(cart.getFoodId());
			order.setDeliveryStatus(Constants.DeliveryStatus.PENDING.value());
			order.setDeliveryDate(Constants.DeliveryStatus.PENDING.value());
			orderDao.save(order);
			cartDao.delete(cart);
		}
		
	
	    mv.addObject("status","Order placed Successfully, Order Id is "+orderId);
		mv.setViewName("index");
		return mv;
	}
	
	/**
     * Displays the list of orders for the logged-in user.
     * 
     * @param session The HttpSession containing the active user information.
     * @return ModelAndView object with the list of orders.
     */
	@GetMapping("/myorder")
	public ModelAndView goToMyOrder(HttpSession session) {
		User user = (User)session.getAttribute("active-user");
		ModelAndView mv = new ModelAndView();
		List<Orders> orders = orderDao.findByUserId(user.getId());
		mv.addObject("orders", orders);
		mv.setViewName("myorder");
		return mv;
	}
	
	/**
     * Searches orders by order ID.
     * 
     * @param orderId The order ID to search for.
     * @return ModelAndView object with the search results.
     */
	@GetMapping("/searchorderbyid")
	public ModelAndView searchByOrderId(@RequestParam("orderid") String orderId) {
		ModelAndView mv = new ModelAndView();
		List<Orders> orders = orderDao.findByOrderId(orderId);
		mv.addObject("orders", orders);
		mv.setViewName("myorder");
		return mv;
	}
	
	/**
     * Searches orders by order date for the logged-in user.
     * 
     * @param orderDate The order date to search for.
     * @param session   The HttpSession containing the active user information.
     * @return ModelAndView object with the search results.
     */
	@GetMapping("/searchorderbydate")
	public ModelAndView searchByOrderDate(@RequestParam("orderdate") String orderDate, HttpSession session) {
		User user = (User)session.getAttribute("active-user");
		ModelAndView mv = new ModelAndView();
		List<Orders> orders = orderDao.findByOrderDateAndUserId(orderDate, user.getId());
		mv.addObject("orders", orders);
		mv.setViewName("myorder");
		return mv;
	}
	
	/**
     * Handles the checkout process.
     * 
     * @param amount The order amount.
     * @return ModelAndView object with the checkout information.
     */
	@PostMapping("/checkout")
	public ModelAndView searchByOrderDate(@RequestParam("amount") String amount) {
		
		ModelAndView mv = new ModelAndView();
	
		mv.addObject("amount", amount);
		mv.setViewName("checkout");
		return mv;
	}
	
	/**
     * Updates the delivery status and date for the specified order.
     * 
     * @param orderId         The order ID to update.
     * @param deliveryStatus  The new delivery status.
     * @param deliveryDate    The new delivery date.
     * @return ModelAndView object with the update status.
     */
	@GetMapping("/updatedeliverydate")
	public ModelAndView addDeliveryStatus(@RequestParam("orderId") String orderId, @RequestParam("deliveryStatus") String deliveryStatus, @RequestParam("deliveryDate") String deliveryDate ) {
		ModelAndView mv = new ModelAndView();
		
		List<Orders> orders = this.orderDao.findByOrderId(orderId);
		
		for(Orders order : orders) {
			order.setDeliveryDate(deliveryDate);
			order.setDeliveryStatus(deliveryStatus);
		    this.orderDao.save(order);
		}
			mv.addObject("status", "Order Delivery Status Updated.");
			mv.setViewName("index");
	        return mv;
	}
	
	
	
}
