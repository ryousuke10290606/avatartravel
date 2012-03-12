package com.appspot.avatartravel;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class BlogServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Long id = Long.valueOf(req.getPathInfo().split("/")[1]);

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Blog blog = pm.getObjectById(Blog.class,id);
			req.setAttribute("blog", blog);
		} finally {
			pm.close();
		}

		RequestDispatcher requestDispatcher = getServletContext()
			.getRequestDispatcher("/WEB-INF/blog.jsp");
		requestDispatcher.forward(req, resp);

	}
}
