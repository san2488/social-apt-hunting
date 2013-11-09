/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ncsu.epc.models;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author nabajyoti
 */
public class GetInfo {
    
    
    private String restURL;
    private final String USER_AGENT = "Mozilla/5.0";
    public String sendGetRequest() {		
 
		URL obj;
		StringBuffer response = new StringBuffer();
		try {
			obj = new URL(getRestURL());
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                return response.toString();
                
		//print result
		//System.out.println(response.toString());
 
	}
    public ApartmentInfo parseJSON(String response,String addr)
    {
       ApartmentInfo aptInfo = null;
       try
       {
        JSONObject json = (JSONObject) new JSONParser().parse(response);
        JSONArray jsonArray = (JSONArray)new JSONParser().parse(json.get("collection").toString());
        for(int i=0;i<jsonArray.size();i++)       
        {
            JSONObject json2 = (JSONObject)new JSONParser().parse(jsonArray.get(i).toString());
            if(addr.startsWith(json2.get("address").toString()))
            {
                aptInfo = new ApartmentInfo();
                aptInfo.addr = json2.get("address").toString();
                if(json2.get("city") != null)
                	aptInfo.city = json2.get("city").toString();
                if(json2.get("state") != null)
                	aptInfo.state = json2.get("state").toString();
                if(json2.get("bedrooms") != null)
                	aptInfo.noOfBedrooms = Double.parseDouble(json2.get("bedrooms").toString());
                if(json2.get("rent") != null)
                	aptInfo.rent=Double.parseDouble(json2.get("rent").toString());
                aptInfo.zip=(String)json2.get("zip_code");
                if(json2.get("year_built") != null)
                	aptInfo.year_built = Double.parseDouble(json2.get("year_built").toString());
                
                
            }
        }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return aptInfo;
    }
	public String getRestURL() {
		return restURL;
	}
	public void setRestURL(String address) {
		try {
			this.restURL = "http://www.rentmetrics.com/api/v1/apartments.json?address=" + URLEncoder.encode(address, "UTF-8") + "&bedrooms=2&api_token=TEST&offset=0&limit=2&include_images=false";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}
