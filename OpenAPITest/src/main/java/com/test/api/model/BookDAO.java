package com.test.api.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BookDAO {

	public ArrayList<BookDTO> search(String word) {

		ArrayList<BookDTO> blist = new ArrayList<BookDTO>();
		
		String clientId = "Om1ptprD0ZaHUxX9s62C";
        String clientSecret = "9tnaGcMuey";
        
        try {
        	word = URLEncoder.encode(word, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + word;
        

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

        System.out.println(responseBody);
        
        //파싱
        JSONParser parser = new JSONParser();
        
        try {
			
        	JSONObject root 
        		= (JSONObject)parser.parse(responseBody);
        	
        	System.out.println(root.get("total"));
        	
        	JSONArray list = (JSONArray)root.get("items");
        	
        	for (int i=0; i<list.size(); i++) {
        		
        		//JSON Object == 책 1권 > BookDTO 1개
        		
        		BookDTO dto = new BookDTO();
				                				dto.setTitle(((JSONObject)list.get(i)).get("title").toString());
				dto.setLink(((JSONObject)list.get(i)).get("link").toString());
				dto.setImage(((JSONObject)list.get(i)).get("image").toString());
				dto.setAuthor(((JSONObject)list.get(i)).get("author").toString());
				dto.setDiscount(((JSONObject)list.get(i)).get("discount").toString());
				dto.setPublisher(((JSONObject)list.get(i)).get("publisher").toString());
				dto.setDescription(((JSONObject)list.get(i)).get("description").toString());
		
				System.out.println(dto);
				
				blist.add(dto);
        		
        	}//for
        	
        	return blist;
        	
		} catch (Exception e) {
			System.out.println("BookDAO.search");
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }

}
























