package file.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import file.service.impl.RoleServiceImpl;
import file.service.impl.UserServiceImpl;
import file.entity.Role;
import file.entity.User;

@WebServlet(name = "UserEditingServlet", urlPatterns = "/user_editing")
public class JSPUserEditingController extends HttpServlet {
	private UserServiceImpl userServiceImpl = new UserServiceImpl();
	private RoleServiceImpl roleServiceImpl = new RoleServiceImpl();
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_editing.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Role ad=new Role();
		Role us=new Role();
		ad.setName("ROLE_ADMIN");
		ad.setId(1L);
		us.setId(2L);
		us.setName("ROLE_USER");
		User user=new User();
        user.setName(request.getParameter("name"));
        user.setPassword("password");
        ArrayList<Role> roles=new ArrayList<Role>();
        int arg=0;
        if (request.getParameter("role1")!= null) {
        	roles.add(arg,ad); arg=1;}
        if (request.getParameter("role2")!= null) {
        	roles.add(arg,us);}
        user.setRoles(roles);
        userServiceImpl.addUser(user);
				
		processRequest(request, response);
	}
}
 
