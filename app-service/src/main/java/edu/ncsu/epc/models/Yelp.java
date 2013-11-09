/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.epc.models;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class Yelp {

    
    OAuthService service;
    Token accessToken;
    public Yelp(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.service = new ServiceBuilder().provider(YelpApi2.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
        this.accessToken = new Token(token, tokenSecret);
    }

    public String search(String term, String location, String limit) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("location", location);
        request.addQuerystringParameter("limit", limit);
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    // CLI
    public ArrayList<YelpInfo> getYelpItems(String response) {
        // Update tokens here from Yelp developers site, Manage API access.
        int i = 0;
        ArrayList<YelpInfo> list = new ArrayList<YelpInfo>();
        YelpInfo yelpInfo;
        
        //System.out.println(response);
        try
        {
        JSONObject json = (JSONObject) new JSONParser().parse(response);
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(json.get("businesses").toString());
        //System.out.println(json.keySet());
        //JSONObject json1 = (JSONObject) new JSONParser().parse(jsonArray.get(3).toString());
        //System.out.println(json1.keySet());
        
        while(i<jsonArray.size())
        {
            yelpInfo = new YelpInfo();
            JSONObject json1 = (JSONObject) new JSONParser().parse(jsonArray.get(i).toString());
            i++;
            yelpInfo.rating = Double.parseDouble(json1.get("rating").toString());
            JSONObject json2 = (JSONObject) new JSONParser().parse(json1.get("location").toString());
            JSONArray jsonArray2 = (JSONArray) new JSONParser().parse(json2.get("address").toString());
            if(jsonArray2.size() == 0) continue;
            yelpInfo.address = jsonArray2.get(0).toString();
//            if(yelpInfo.address.length()==2) continue;
//            yelpInfo.address = yelpInfo.address.substring(1, yelpInfo.address.length()-1);
//            yelpInfo.address = yelpInfo.address.substring(1, yelpInfo.address.length()-1);
            yelpInfo.address += "," + json2.get("city") + "," + json2.get("state_code");
            list.add(yelpInfo);
            
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return list;
//        Iterator<String> iterator = jsonArray.iterator();
//        while (iterator.hasNext()) {
//            String str = (String)iterator.next();
//            System.out.println(str);
//            JSONObject json1 = (JSONObject) new JSONParser().parse(iterator.next().toString());
//            System.out.println(json1.keySet());
//        }

    }

}
