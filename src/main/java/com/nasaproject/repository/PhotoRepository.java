package com.nasaproject.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nasaproject.bean.Camera;
import com.nasaproject.connection.HttpConnection;

/**
 * A Photo Repository which handles the HttpHeader connection.
 * @author Kami Hassanzadeh
 */
public class PhotoRepository implements IPhotoRepository {

	private static final String PHOTO_LIST_BY_IMAGE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=";
	private static final String PHOTO_LIST_BY_PAGE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&page=";
	private static final String ALL_PHOTO_LIST_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY";

	private HttpConnection httpconnection ;

	public PhotoRepository() {
		httpconnection = new HttpConnection();
	}

	@Override
	public Camera getImageIdByNumber(String input) {

		Camera camera = new Camera();
		try {
			HttpURLConnection connection = httpconnection.getConnectionURL(PHOTO_LIST_BY_IMAGE_URL+ input +"&api_key=DEMO_KEY");

			BufferedReader in = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				
				response.append(inputLine);
				JSONObject jsonObject = new JSONObject(response.toString());
			
				Object object = jsonObject.get("photos");
				JSONArray array = new JSONArray(object.toString()); 
				String entity = array.toString().substring(1, array.toString().length()-1);
				JSONObject json = new JSONObject(entity.toString());
				String resultImage = json.get("img_src").toString();
				String resultId = json.get("id").toString();
				camera.setId(Integer.parseInt(resultId));
				camera.setImage(resultImage);
			}
			in.close();	
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return camera;
	}

	@Override
	public String getAllImage(){
		String result = "";
		try {

			HttpURLConnection connection = httpconnection.getConnectionURL(ALL_PHOTO_LIST_URL);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				result = response.toString();
			}
			in.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Camera getImageIdByPage(String pageNumber) {
		Camera camera = new Camera();

		try {
			HttpURLConnection connection = httpconnection.getConnectionURL(PHOTO_LIST_BY_PAGE_URL+ pageNumber +"&api_key=DEMO_KEY");

			BufferedReader in = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				
				response.append(inputLine);
				JSONObject jsonObject = new JSONObject(response.toString());
				Object object = jsonObject.get("photos");
				JSONArray array = new JSONArray(object.toString()); 
				String entity = array.toString().substring(1, array.toString().length()-1);
				JSONObject json = new JSONObject(entity.toString());
				String resultImage = json.get("img_src").toString();
				String resultId = json.get("id").toString();
				camera.setId(Integer.parseInt(resultId));
				camera.setImage(resultImage);
			}
			in.close();	
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return camera;
	}
}
