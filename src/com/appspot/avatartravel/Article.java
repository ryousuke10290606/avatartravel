package com.appspot.avatartravel;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Article {

	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@PrimaryKey private Key key;

	//親クラスへの参照
	@Persistent private Blog blog;

	@Persistent private Date postDate;
	@Persistent private String text;
	@Persistent private String pageUrl;
	@Persistent private String imageUrl;

	public Article(Date postDate,String text,String pageUrl,String imageUrl) {
		this.postDate = postDate;
		this.text = text;
		this.pageUrl = pageUrl;
		this.imageUrl = imageUrl;
	}

	public Key getKey() {
		return key;
	}

	public Blog getBlog() {
		return blog;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



}
