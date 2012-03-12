package com.appspot.avatartravel;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ArticleTaskServlet extends HttpServlet {

	private static final Logger logger = Logger
	.getLogger(ArticleTaskServlet.class.getName());

	//タスクキューから呼び出される
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		//例外は必ず処理
		try {
			updateBlog(req,resp);
		} catch (Exception e) {
			if(logger.isLoggable(Level.SEVERE)){
				logger.severe(e.toString());
			}
		}
	}

	private void updateBlog(HttpServletRequest req, HttpServletResponse resp) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Long id = Long.valueOf(req.getParameter("id"));
			Blog blog =pm.getObjectById(Blog.class,id);

            /* 新しい記事エンティティを作成する */
            Avatar avatar = Avatar.getAvatar(blog.getAvatarId());
            avatar.createArticle(blog);

			pm.makePersistent(blog);
		} finally {
			pm.close();
		}
	}



}
