package com.triplemovie.pjt.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.triplemovie.pjt.api.model.youtube.YmDTO;

public class Youtube {
	public static String search(String search) throws IOException {
		String apiurl = "https://www.googleapis.com/youtube/v3/search";
		apiurl += "?key=AIzaSyCCzSTl21rNIStYFCGtOOjb2c8ToYMX1vU";
		apiurl += "&part=snippet&type=video&maxResults=1&videoEmbeddable=true";
		apiurl += "&q="+URLEncoder.encode(search+"영화예고편","UTF-8");
		
		URL url = new URL(apiurl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close();
		String result = response.toString();
		Gson gs = new Gson();
		YmDTO vo = gs.fromJson(result, YmDTO.class);
		
		if(vo.getItems().length == 0) {
			return null;
		}
		return vo.getItems()[0].getId().getVideoId();
	}
}