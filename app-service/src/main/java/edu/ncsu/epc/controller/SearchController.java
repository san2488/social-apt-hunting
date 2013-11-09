package edu.ncsu.epc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ncsu.epc.models.*;

@Controller
public class SearchController {    
    static String consumerKey = "XUUoxfhcBxuORKj8DcwvOQ";
    static String consumerSecret = "d4K0txeRYBvBQUZjUth9xeVufrU";
    static String token = "DWtwvvJnmnEwEM6Dwr1mnhD-pBDaYnhU";
    static String tokenSecret = "uXE0ITnHlHUVG6xMDQvcs-ncqOY";

	@RequestMapping(value="/search", method = RequestMethod.POST)
	public @ResponseBody ArrayList<SearchResults> getSearchResults(@RequestParam("home") String home,
			@RequestParam("office") String office,
			@RequestParam("beds") int beds, 
			@RequestParam("rentMin") float rentMin,
			@RequestParam("rentMax") float rentMax,
			@RequestParam("save") boolean save) {
		
		if(false) {
			DatabaseConnection dc = new DatabaseConnection();
			SearchQuery sq = new SearchQuery();
			sq.setHomeAddr(home);
			sq.setOfcAddr(home);
			sq.setBeds(beds);
			sq.setRentMin(rentMin);
			sq.setRentMax(rentMax);
			
			dc.saveSearchQueries(sq);
		}
		
		ArrayList<SearchResults> sr = new ArrayList<SearchResults>();
        GetInfo m = new GetInfo();
        //String response = m.sendGetRequest();
        ArrayList<YelpInfo> items;
        Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
        String responseYelp = yelp.search("apartments near", home, "10");
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
            List<GMapsInfo> list = gm.getRoutes(item.address, office);
            if(list == null) list = new ArrayList<GMapsInfo>();
        	sr.add(new SearchResults(aptInfo.addr, aptInfo.city, aptInfo.zip, 
        			(float)aptInfo.noOfBedrooms, (float)aptInfo.noOfBathrooms, 
        			aptInfo.rent, (float) item.rating, list.size()));
        }

		return sr;

	}

}
