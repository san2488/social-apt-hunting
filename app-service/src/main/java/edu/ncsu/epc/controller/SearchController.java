package edu.ncsu.epc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ncsu.epc.models.*;

@Controller
@RequestMapping("/search")
public class SearchController {    
    static String consumerKey = "XUUoxfhcBxuORKj8DcwvOQ";
    static String consumerSecret = "d4K0txeRYBvBQUZjUth9xeVufrU";
    static String token = "DWtwvvJnmnEwEM6Dwr1mnhD-pBDaYnhU";
    static String tokenSecret = "uXE0ITnHlHUVG6xMDQvcs-ncqOY";
    
	@RequestMapping(value="{name}", method = RequestMethod.GET)
	public @ResponseBody ArrayList<SearchResults> getSearchResults(@PathVariable String name) {

		ArrayList<SearchResults> sr = new ArrayList<SearchResults>();
        GetInfo m = new GetInfo();
        //String response = m.sendGetRequest();
        ArrayList<YelpInfo> items;
        Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
        String responseYelp = yelp.search("apartments near", "602 E Harrison St Wa", "10");
        items = yelp.getYelpItems(responseYelp);
        ApartmentInfo aptInfo;
        for(YelpInfo item : items) {
        	m.setRestURL(item.address);
        	String response = m.sendGetRequest();
        	aptInfo = m.parseJSON(response, item.address);
        	System.out.println("resturl: " + m.getRestURL());
        	System.out.println("aptInfo: " + aptInfo);
        	if(aptInfo == null) continue;
            GmapsResp gm = new GmapsResp();
            List<GMapsInfo> list = gm.getRoutes(item.address, "333 Boren Ave N, Seattle, WA 98109");
            if(list == null) list = new ArrayList<GMapsInfo>();
        	sr.add(new SearchResults(aptInfo.addr, aptInfo.city, aptInfo.zip, 
        			(float)aptInfo.noOfBedrooms, (float)aptInfo.noOfBathrooms, 
        			aptInfo.rent, (float) item.rating, list.size()));
        }

		return sr;

	}

}
