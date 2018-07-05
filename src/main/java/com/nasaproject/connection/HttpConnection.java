package com.nasaproject.connection;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Set an Accept GET to HttpURLConnection 
 * and set ReadTimeout and ConnectTimeOut
 * @author Kami Hassanzadeh
 */

public class HttpConnection {

	public HttpConnection(){}
	
	public HttpURLConnection getConnectionURL(String url) {
		
		HttpURLConnection connection = null;
		try {
			URL obj = new URL(url);
			connection = (HttpURLConnection) obj.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			connection.setRequestMethod("GET");
			connection.setReadTimeout(30000);
			connection.setConnectTimeout(3000);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
