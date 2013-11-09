package edu.ncsu.epc.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class DatabaseConnection {

	  String myDriver = "com.mysql.jdbc.Driver";
	  String myUrl = "jdbc:mysql://ut0f5Kx6m1m5B:pkUkKEYJuA3tv@75.126.139.210:3307/d20f281e17e7046598cdc98c5ca6a531f";
	  String username = "ut0f5Kx6m1m5B";
	  String password = "pkUkKEYJuA3tv";
	public ArrayList<SearchQuery> getSearchQueries() {
		  ArrayList<SearchQuery> sqlist = new ArrayList<SearchQuery>();
		try
		{
		  // create a java mysql database connection
		  Class.forName(myDriver).newInstance();
		  Connection conn = DriverManager.getConnection(myUrl, username, password);
	
		  String query = "SELECT * FROM Queries";
	
		  PreparedStatement ps = conn.prepareStatement(query);
		  
		  ResultSet rs = ps.executeQuery(query);
		  
		  
		  while(rs.next()) {
			  SearchQuery sq = new SearchQuery();
			  sq.setHomeAddr(rs.getString("home"));
			  sq.setOfcAddr(rs.getString("office"));
			  sq.setBeds(rs.getInt("beds"));
			  sq.setRentMin(rs.getFloat("rentMin"));
			  sq.setRentMax(rs.getFloat("home"));
			  sqlist.add(sq);
		  }
		  
		  conn.close();
		}
		catch (Exception e)
		{
		  System.err.println("Got an exception! ");
//		  System.err.println(e.printS);
		  e.printStackTrace();
		}
		
		return sqlist;
	}
	
	public void saveSearchQueries(SearchQuery sq) {
		try
		{
		  // create a java mysql database connection
		  Class.forName(myDriver);
		  Connection conn = DriverManager.getConnection(myUrl, username, password);
	
		  String query = "INSERT INTO Queries VALUES(?, ?, ?, ?, ?)";
	
		  PreparedStatement ps = conn.prepareStatement(query);
		  ps.setString(1, sq.getHomeAddr());
		  ps.setString(2, sq.getOfcAddr());
		  ps.setInt(3, sq.getBeds());
		  ps.setFloat(4, sq.getRentMin());
		  ps.setFloat(5, sq.getRentMax());
		  
		  
		  ps.execute();

		  
		  conn.close();
		}
		catch (Exception e)
		{
		  System.err.println("Got an exception! ");
		  System.err.println(e.getMessage());
		  e.printStackTrace();
		}
	}
	
}
