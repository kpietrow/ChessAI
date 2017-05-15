package old;

import java.io.BufferedReader;
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
public class Test {
	public static void test() { 
				
		// Set scanner to get game number when program started
		Scanner sc = new Scanner(System.in);
	    String input = sc.nextLine();
	    
	    String json = "";
				
		try {
			// URL to query the game
			HttpURLConnection query = (HttpURLConnection) (new URL("http://www.bencarle.com/chess/poll/" + input + "/209/fcbd8a97/")).openConnection();
			
			
			// Convert to BufferReader, grab the first line
			BufferedReader reader = new BufferedReader(new InputStreamReader(query.getInputStream()));
			json = reader.readLine();
			
			// Going to attempt to make a json object out of this
			try {
				// It works! JSON object successfully created
				JSONObject j = new JSONObject(json);
				System.out.println("Here goes nothing!");
				System.out.println(j.get("lastmovenumber"));
				
				boolean d = (boolean) j.get("ready");
				if (d) {
					System.out.println("trueee");
				} else {
					System.out.println("falseee");
				}
				// And it has the correct value as well
				
				// Now to try to send something back
				// URL to respond
				
				//This section works on sending a move!
				HttpURLConnection response = (HttpURLConnection) (new URL("http://www.bencarle.com/chess/move/" + input + "/209/fcbd8a97/Pa2a4/")).openConnection();
				BufferedReader reader2 = new BufferedReader(new InputStreamReader(response.getInputStream()));
				System.out.println(reader2.readLine());
				// Sends the move
				
				
								
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
