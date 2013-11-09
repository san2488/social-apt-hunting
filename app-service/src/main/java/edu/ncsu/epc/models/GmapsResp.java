package edu.ncsu.epc.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.*;


public class GmapsResp {
  
  private String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }
  
  public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
  
  public List<GMapsInfo> getRoutes(String source,String destination) {
      
    //String loc1 = "1556 Varsity Drive, Raleigh, NC";
    String loc1mod = "";
    String loc2mod = "";
	try {
		loc1mod = URLEncoder.encode(source, "UTF-8");
	
    //String loc2 = "Resrch Trngle Pk, NC 27709";
		loc2mod = URLEncoder.encode(destination, "UTF-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    String url = "http://maps.googleapis.com/maps/api/directions/json?origin="+loc1mod+"&destination="+loc2mod+"&sensor=false&mode=transit&departure_time=1383952305&vehicle.type=BUS&alternatives=true";
    System.out.println(url);
    JSONArray arr = null;
    JSONObject json;
	try {
		json = readJsonFromUrl(url);
	
		arr = json.getJSONArray("routes");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    GMapsInfo info1 ;
    List<GMapsInfo> list1 = new ArrayList<GMapsInfo>();
    
    //info1.no_routes = arr.length();
    
    //System.out.println("=============================================");
    for (int i = 0; i < arr.length(); ++i) {
      //System.out.println("Route " + (i+1) + " : ");
      info1  = new GMapsInfo();
      JSONObject routes;
		try {
			routes = arr.getJSONObject(i);
		
	      JSONArray arr1 = routes.getJSONArray("legs");
	      JSONObject legs = arr1.getJSONObject(0);
	      JSONObject duration = legs.getJSONObject("duration");
	      String total_time = duration.getString("text");
	      JSONObject distance = legs.getJSONObject("distance");
	      String total_distance = distance.getString("text");
		
      //JSONObject arr_time = legs.getJSONObject("arrival_time");
     // String arrival_time = arr_time.getString("text");
      //JSONObject dep_time = legs.getJSONObject("departure_time");
     // String departure_time = dep_time.getString("text");
      
      info1.total_distance = total_distance.toString();
      //System.out.println("departure_time : " + departure_time.toString());
     // System.out.println("arrival_time : " + arrival_time.toString());
      info1.total_time =  total_time.toString();
      } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      list1.add(info1);
      //System.out.println("=============================================");
     
      
    }
    //System.out.println(json.toString());
    
    return list1;
    
  }
}