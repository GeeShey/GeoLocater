# GeoLocater
This a Java program which can search for nearby locations(by geo-locating the device running this program) and writing a query on Google Places API.

PROJECT DETAILS

This a Java program which can search for nearby locations(by geo-locating the device running this program)
and writting a query on Google Places API
	
Utilizes a wrapper class made by https://github.com/windy1 (repo link below) integrated using Maven
	
This is purely an educational exploration, I wanted to learn about interacting with 
web APIs using Java. The code is far from optimized.
	
My original objective was to get nearby bus-stops and then figure out the timing of the next-bus
using GIS and GTFS data, but from my understanding Places API does not support bus-information yet
even though you can access the nearest bus-stops using the same API.
Even though, I could not achieve that, I ended up learning a lot about interacting with 
Google Maps API
	
FLOW OF CODE

get ip address of device using amazonaws

get longitude & latitude using ipinfo.com

create Google Places API query using https://github.com/windy1/google-places-api-java client

store query results in a list  
	
PRE-REQUISITES

create an account on google developers console and get API key
create an account on ipinfo.com and get API Key
probably a better idea would be to link these to values to a local file to 
avoid uploading your sensitive info(API keys) to the repository
  
WARNINGS

While this may seem like a free solution to geolocate nearby places, the google developers console
places a limit on the number of queries and so does ipinfo.com
Please refer to the guidelines and limits mentioned on both the websites
