package file.mvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import file.entity.User;
import file.service.impl.UserServiceImpl;

@WebServlet(name = "UserListServlet", urlPatterns = "/user_list")
public class JSPUserListController extends HttpServlet {

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_list.jsp");
		dispatcher.forward(request, response);

	}

	private UserServiceImpl userServiceImpl = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long id = (long) 0;
		request.setCharacterEncoding("UTF-8");
		User USER = userServiceImpl.getUser(id);
		String name = USER.getName();

		/*
		 * String login = request.getParameter("login"); String pass =
		 * request.getParameter("pass"); RegistrationHelper registrationHelper = new
		 * RegistrationHelper(); registrationHelper.setLogin(login);
		 * 
		 * boolean isError = false; if (login == null || login.trim().equals("")) {
		 * String loginMessage = "It's empy";
		 * registrationHelper.setLoginMessage(loginMessage); isError = true; } if (pass
		 * == null || pass.trim().equals("")) { String passMessage = "It's empty.";
		 * registrationHelper.setPassMessage(passMessage); isError = true; }
		 * if(!isError) {
		 * 
		 * 
		 * 
		 * UsersDAO dao = (UsersDAO)
		 * request.getServletContext().getAttribute("usersDAO");
		 */
		request.setAttribute("USER", name); // Will be available as ${product} in JSP
		request.getRequestDispatcher("/WEB-INF/product.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}

}
