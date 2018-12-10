/*
package file.mvc;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import file.entity.Cart;
import file.entity.User;
import file.service.CartService;
import file.service.UserService;

@Controller
public class CartController {
	
	private CartService cartService;
	
	private UserService userService;

	@RequestMapping(value = "/cart/getCart", method = RequestMethod.GET)
	public ModelAndView getCartId() {

		ModelAndView mav = new ModelAndView();
		User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = authenticatedUser.getName();
		User u = userService.getUser(username);

		mav.addObject("cartId", u.getCart().getId());
		return mav;
	}

	@RequestMapping("/cart/getCart/{cartId}")
	public @ResponseBody Cart getCartItems(@PathVariable(value = "cartId") Long cid) {
		return cartService.getCart(cid);
	}
}
*/