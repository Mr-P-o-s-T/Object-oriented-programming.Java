package org.web;

import org.exception.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
	private static String login = "admin", password = "admin";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String log = req.getParameter("login");
		String pass = req.getParameter("password");

		if (!(log.equals(login) && pass.equals(password))) {
			throw new ValidationException("Administrator validation failed");
		} else {
			Cookie nameCookie = new Cookie("username", log);
			resp.addCookie(nameCookie);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
}
