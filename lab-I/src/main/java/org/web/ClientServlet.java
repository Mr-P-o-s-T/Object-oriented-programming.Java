package org.web;

import org.config.BeanFactory;
import org.exception.ValidationException;
import org.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientServlet extends HttpServlet {
	private ClientService service = (ClientService) BeanFactory.getBean(ClientService.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("clients", service.findAllClients());
		req.getRequestDispatcher("client.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");

		if (!ValidationUtils.validateString(firstname)) {
			throw new ValidationException("Client first name is not valid");
		} else 	if (!ValidationUtils.validateString(lastname)) {
			throw new ValidationException("Client last name is not valid");

		} else {
			service.createClient(firstname, lastname);
			req.setAttribute("clients", service.findAllClients());
			req.getRequestDispatcher("client.jsp").forward(req, resp);
		}
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
