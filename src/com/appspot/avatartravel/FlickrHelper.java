package com.appspot.avatartravel;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class FlickrHelper {
	private static final Logger logger = Logger
	.getLogger(FlickrHelper.class.getName());

	private static final String KEY="f1bc14c7bfbb5898bbc8694849cbf878";
	private HashMap<String, String> params = new HashMap<String, String>();
	public FlickrHelper() {}

	public static FlickrHelper newHelper(String keywords){
		try{
			XMLStreamReader reader = null;
			try{
				reader = XMLInputFactory.newInstance()
					.createXMLStreamReader(new URL(buildSearchUrI(keywords)).openStream());
				return createHelperFromReader(reader);
			}finally{
				if(reader != null){
					reader.close();
				}
			}
		}catch (Exception e) {
			if(logger.isLoggable(Level.SEVERE)){
				logger.severe(e.toString());
			}
			return null;
		}
	}

	private static String buildSearchUrI(String keywords) throws UnsupportedEncodingException{
		StringBuilder builder = new StringBuilder();
		builder.append("http://www.flickr.com/services/rest")
			.append("?method=flickr.photos.search").append("&format=rest")
			.append("&api_key=").append(KEY).append("&per_page=1")
			.append("&license=1,2,3,4,5,6")
			.append("&sort=date-posted-desc").append("&text=")
			.append(URLEncoder.encode(keywords, "utf-8"));
		String url = builder.toString();
		return url;

	}

	private static FlickrHelper createHelperFromReader(XMLStreamReader reader)
		throws XMLStreamException {

		FlickrHelper helper = new FlickrHelper();
		moveToPhotoElement(reader);
		for (int i = 0; i < reader.getAttributeCount(); i++) {
			helper.params.put(reader.getAttributeLocalName(i), reader.getAttributeValue(i));
		}
		return helper;
	}

	private static void moveToPhotoElement(XMLStreamReader reader)
		throws XMLStreamException{
		while(!reader.isStartElement()||!"photo".equals(reader.getLocalName())){
			reader.nextTag();
		}
	}

	public String getImageUrl(){
		StringBuilder builder = new StringBuilder();
		builder.append("http://farm").append(params.get("farm"))
			.append(".static.flickr.com").append("/")
			.append(params.get("server")).append("/")
			.append(params.get("id")).append("_")
			.append(params.get("secret")).append("_m.jpg");
		return builder.toString();
	}

	public String getPageUrl(){
		StringBuilder builder = new StringBuilder();
		builder.append("http://www.flickr.com").append("/")
			.append(params.get("owner")).append("/")
			.append(params.get("id"));
		return builder.toString();
	}


}
