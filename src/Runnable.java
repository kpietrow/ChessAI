import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;


/*
 * This is going to hold the main method
 */
public class Runnable {
	public static void main(String args[]) { 
				
		// Set scanner to get game number when program started
		Scanner sc = new Scanner(System.in);
	    String input = sc.nextLine();
	    
	    String json = "";
				
		try {
			// Queries the URL for the game
			HttpURLConnection c = (HttpURLConnection) (new URL("http://www.bencarle.com/chess/poll/" + input + "/209/fcbd8a97/")).openConnection();
			int resp = c.getResponseCode();
			
			// Convert to BufferReader, grab the first line
			BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
			json = reader.readLine();
			
			// Going to attempt to make a json object out of this
			try {
				JSONObject j = new JSONObject(json);
				System.out.println("Here goes nothing!");
				System.out.println(j.get("lastmovenumber"));
				
				// It works! JSON object successfully created
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		  
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		 * Sample creation of JSON object
		 */
		JSONObject jo = new JSONObject();
		try {
			jo.put("site", "homeboy");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			jo.put("yoyoma", "hello");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(jo);
		
	}
	
}
