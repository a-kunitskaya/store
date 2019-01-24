package com.kunitskaya.service;

import com.kunitskaya.entity.*;
import com.kunitskaya.service.configuration.i18n.EnLocaleBundle;
import com.kunitskaya.service.configuration.i18n.RuLocaleBundle;
import com.kunitskaya.service.database.OrderDatabaseOperations;
import com.kunitskaya.service.database.ProductDatabaseOperations;
import com.kunitskaya.service.database.UserDatabaseOperations;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController
{
	@Autowired
	private UserDatabaseOperations userDatabaseOperations;
	@Autowired
	private ProductDatabaseOperations productDatabaseOperations;
	@Autowired
	private OrderDatabaseOperations orderDatabaseOperations;
	@Autowired
	private User user;
	@Autowired
	private Logger logger;
	@Autowired
	private TradeService tradeService;

	private Order order;
	private String localePath;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main()
	{
		return "main";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndex(Model model, @ModelAttribute("locale") String locale)
	{
		localePath = locale.equals("en_US") ? EnLocaleBundle.class.getName() : RuLocaleBundle.class.getName();
		model.addAttribute("localePath", localePath);
		tradeService.addTestProducts();
		return "index";
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String viewProducts(Model model, @ModelAttribute("username") String username, @ModelAttribute("password") String password)
	{
		List<Product> products = productDatabaseOperations.getProducts();
		model.addAttribute("products", products);
		model.addAttribute("localePath", localePath);

		try
		{
			user = userDatabaseOperations.getUser(username, password);
			return "products";
		}
		catch (EmptyResultDataAccessException e)
		{
			String error = "No such user is found. Please register";
			model.addAttribute("error", error);
			return getIndex(model, localePath);
		}
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String getRegistration(Model model)
	{
		model.addAttribute("localePath", localePath);
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String register(Model model, @ModelAttribute("username") String username, @ModelAttribute("password") String password, @ModelAttribute("role") String role)
	{
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(UserRoles.valueOf(role));
		userDatabaseOperations.addUser(user);
		model.addAttribute("localePath", localePath);
		return "successRegistration";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProductToOrder(Model model, @ModelAttribute("productId") String productId)
	{
		logger.info("Adding product to order, id: " + productId);

		order = orderDatabaseOperations.createOrder(user, order);
		Product product = productDatabaseOperations.getProduct(productId);
		orderDatabaseOperations.addProduct(productId, order);
		order.getProducts().add(product);
		model.addAttribute("localePath", localePath);

		return viewProducts(model, user.getUsername(), user.getPassword());
	}

	@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
	public String removeProductFromOrder(Model model, @ModelAttribute("productId") String productId)
	{
		model.addAttribute("localePath", localePath);
		logger.info("Removing product from order, id: " + productId);
		boolean isProductInOrder = orderDatabaseOperations.isProductInOrder(productId, order);
		if (isProductInOrder)
		{
			orderDatabaseOperations.deleteProductFromOrder(productId, order);
			Product product = productDatabaseOperations.getProduct(productId);
			order.getProducts().remove(product);
			logger.info("Product is removed from order");
		}
		else
		{
			String error = "Product is not in order. To delete a product please add it first";
			model.addAttribute("error", error);
			logger.info("Product is not removed from order");
		}

		return viewProducts(model, user.getUsername(), user.getPassword());
	}

	@RequestMapping(value = "/viewOrder", method = RequestMethod.GET)
	public String viewOrder(Model model)
	{
		model.addAttribute("localePath", localePath);
		logger.info("Getting products in order");
		Map<Product, Integer> orderProducts = orderDatabaseOperations.getProductCount(order);
		model.addAttribute("order", orderProducts);
		return "order";
	}

	@RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
	public String cancelOrder(Model model)
	{
		model.addAttribute("localePath", localePath);
		logger.info("Cancelling order: " + order.getId());
		order.setStatus(OrderStatus.CANCELLED);
		orderDatabaseOperations.deleteOrder(order);

		model.addAttribute("cancelledOrder", order);
		return viewProducts(model, user.getUsername(), user.getPassword());
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout(Model model)
	{
		model.addAttribute("localePath", localePath);
		return "checkout";
	}
}
