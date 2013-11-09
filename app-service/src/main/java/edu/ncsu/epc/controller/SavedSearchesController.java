package edu.ncsu.epc.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ncsu.epc.models.DatabaseConnection;
import edu.ncsu.epc.models.SearchQuery;

@Controller
public class SavedSearchesController {

	@RequestMapping(value="/history", method = RequestMethod.GET)
	public @ResponseBody ArrayList<SearchQuery> getSearchHistory(){
		DatabaseConnection dc = new DatabaseConnection();
		
		ArrayList<SearchQuery>sqlist = dc.getSearchQueries();
		
		return sqlist;
		
	}
	
}
