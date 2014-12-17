import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;


/*
 * This is going to hold the main method
 */
public class Runnable {
	public static void main(String args[]) {
		
		/*
		
		// Set scanner to get game number when program started
		Scanner sc = new Scanner(System.in);
		System.out.println("Game number: ");
	    String input = sc.nextLine();
	    
	    System.out.println("team color (1 for white, 2 for black): ");
	    int team = sc.nextInt();
	    
	    
	    // Open up the query connection
		HttpURLConnection query = null;
		
		// Establish the buffer reader
		BufferedReader reader = null;
		
		// String used for initial json
		String jsonString = "";
		// JSON Object
		JSONObject json = null;
		
		
		int counter = 0;
		
		while (counter < 2) {
		
			try {
				query = (HttpURLConnection) (new URL("http://www.bencarle.com/chess/poll/" + input + "/209/fcbd8a97/")).openConnection();
				reader = new BufferedReader(new InputStreamReader(query.getInputStream()));
				jsonString = reader.readLine();
				json = new JSONObject(jsonString);
				
				System.out.println(json);
				
			
			
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} */
		Test.test();
		Board board = new Board();
		
		ConstructWhiteMove.run("0", board);
		
		System.out.println("done!");
		
		/*
		HttpURLConnection response;
		try {
			response = (HttpURLConnection) (new URL("http://www.bencarle.com/chess/move/" + gameID + "/209/fcbd8a97/Pa2a4/")).openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getInputStream()));
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
}
