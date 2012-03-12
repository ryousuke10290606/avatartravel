package com.appspot.avatartravel;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("avatars", Avatar.getAvatars());

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Query query = pm.newQuery(Blog.class);
			query.setOrdering("departureDate desc");
			query.setRange(0, 10);
			List<Blog> blogs = (List<Blog>)query.execute();
			pm.detachCopyAll(blogs);
			req.setAttribute("blogs", blogs);
		} finally {
			pm.close();
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String avatarId = req.getParameter("avatarId");
		String destination = req.getParameter("destination");
		Date departureDate = new Date();
		Date nextPostDate = new Date();
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Blog blog = new Blog(avatarId, destination, departureDate, nextPostDate);
			pm.makePersistent(blog);
			req.setAttribute("blog", blog);
		} finally {
			pm.close();
		}
		RequestDispatcher requestDispatcher = getServletContext()
			.getRequestDispatcher("/WEB-INF/index_post.jsp");
		requestDispatcher.forward(req, resp);

	}

}
