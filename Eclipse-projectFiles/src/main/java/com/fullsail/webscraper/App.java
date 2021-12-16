package com.fullsail.webscraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import io.ipinfo.api.IPinfo;
import io.ipinfo.api.errors.RateLimitedException;
import io.ipinfo.api.model.IPResponse;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;

public class App {
	
	
	//PROJECT DETAILS
	//This a Java program which can search for nearby locations(by geo-locating the device running this program)
	//and writting a query on Google Places API
	
	//Utilizes a wrapper class made by https://github.com/windy1 (repo link below) integrated using Maven
	
	//This is purely an educational exploration, I wanted to learn about interacting with 
	//web APIs using Java. The code is far from optimized.
	
	//My original objective was to get nearby bus-stops and then figure out the timing of the next-bus
	//using GIS and GTFS data, but from my understanding Places API does not support bus-information yet
	//even though you can access the nearest bus-stops using the same API.
	//Even though, I could not achieve that, I ended up learning a lot about interacting with 
	//Google Maps API
	
	//FLOW OF CODE
	//get ip address of device using amazonaws
	//get longitude & latitude using ipinfo.com
	//create Google Places API query using "https://github.com/windy1/google-places-api-java" client
	//store query results in a list  
	
	
	//PRE-REQUISITES
	//create an account on google developers console and get API key
	//create an account on ipinfo.com and get API Key
	//TODO
	//probably a better idea would be to link these to values to a local file to 
	//avoid uploading your sensitive info(API keys) to the repository
	private static String ipInfoApiKey = "";
	private static String GoogleApiKey = "";
	
	//TODO
	//WARNINGS
	//While this may seem like a free solution to geolocate nearby places, the google developers console
	//places a limit on the number of queries and so does ipinfo.com
	//Please refer to the guidelines and limits mentioned on both the websites
	
	private static String ip,latitude,longitude;
	private static Double radius = (double) 1000;
	
	//method to get the public ip address
	public static String getIp() throws Exception {
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			String ip = in.readLine();
			return ip;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {

		IPinfo ipInfo = new IPinfo.Builder().setToken(ipInfoApiKey).build();

		try {
			ip = getIp();
			System.out.println("Public IP: " + ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
  
		  try {
		  		  
			  IPResponse response = ipInfo.lookupIP(ip);
			  
			  // Print out the hostname System.out.println(response.getHostname());
			  
			  System.out.println(response.getCity());
			  System.out.println(response.getLatitude() +", "+ response.getLongitude());
			  latitude = response.getLatitude();
			  longitude = response.getLongitude();
		  
		  }
		  catch (RateLimitedException ex) { // Handle rate limits here.
		  }
		  
		  GooglePlaces client = new GooglePlaces(GoogleApiKey);
		  Double lat = Double.parseDouble(latitude);
		  Double lng = Double.parseDouble(longitude);
		  
		  //example of a query
		  //List<Place> places = client.getNearbyPlaces(lat,lng,radius,10);
		  
		  //this query override is quite flexible, lot of params can be added such as placeType and keyword
		  List<Place> places = client.getPlacesByQuery("Full Sail", 10,Param.name("type").value("University"), Param.name("radius").value(radius));
		  
		  for (Place place : places) {
				/*
				 * if (place.getName().equals(targetPlaceName)) { printBasicDetails(place); }
				 */
			  printBasicDetails(place);
			  
		  }	 

	}
	
	public static void printBasicDetails(Place _p)
	{
		if (_p != null) {
			  
		      // Just an example of the amount of information at your disposal:
		      System.out.println("Name: " + _p.getName());
		      System.out.println("Address: " + _p.getAddress());
		      System.out.println("Maps ID: " + _p.getPlaceId());
		      System.out.println();

		  }
	}

}
