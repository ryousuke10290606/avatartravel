package com.appspot.avatartravel;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions.Builder;


@SuppressWarnings("serial")
public class ArticleServlet extends HttpServlet {

	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery(Blog.class);
		query.setFilter("nextPostDate <= :currentDate");
		query.setOrdering("nextPostDate");
		List<Blog> blogs = (List<Blog>)query.execute(new Date());
		
		for(Blog blog:blogs){
			Queue queue = QueueFactory.getQueue("article-queue");
			queue.add(Builder.withUrl("/task/article").param("id", blog.getId().toString()));
		}
		
	}
}
