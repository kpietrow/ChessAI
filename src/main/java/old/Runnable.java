package old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;


/*
 * This is going to hold the main method
 * 
 * Creates a board and all of the necessary variables, and then runs an unending game loop, 
 * querying the server and posting moves
 */
public class Runnable {
	public static void main(String args[]) {
		
		// Set scanner to get game number when program started
		Scanner sc = new Scanner(System.in);
		System.out.println("Game number: ");
	    String input = sc.nextLine();
	    
	    System.out.println("team color (1 for white, 2 for black): ");
	    int team = sc.nextInt();
	    sc.close();
	    
	    
	    // Open up the query connection
		HttpURLConnection query = null;
		HttpURLConnection response = null;
		
		// Establish the buffer reader
		BufferedReader reader = null;
		BufferedReader sender = null;
		
		// String used for initial json
		String jsonString = "";
		// JSON Object
		JSONObject json = null;
		
		// Board object, initial state
		Board board = new Board();
		
		// If we can take our turn or not
		boolean ready;
		
		// The root Node
		Node root = null;
		
		// The last move
		String lastMove = "";
		
		// So timeouts don't get too great
		int timeout = 0;
		
		// We're the White side
		if (team == 1) {
			int index = 0;
			int moveNumber = 0;
			
			while (true) {
			
				try {
					// Query to see if it's our turn yet
					query = (HttpURLConnection) (new URL("http://www.bencarle.com/chess/poll/" + input + "/209/fcbd8a97/")).openConnection();
					reader = new BufferedReader(new InputStreamReader(query.getInputStream()));
					jsonString = reader.readLine();
					json = new JSONObject(jsonString);
					
					ready = (boolean) json.get("ready");
					moveNumber = json.getInt("lastmovenumber");
					System.out.println("LOOP: " + jsonString);
					
					if (ready && index == moveNumber) {
						
						lastMove = (String) json.get("lastmove");
						
						if (lastMove.length() > 0) {
							// Make changes to our board to reflect opponent's move
							InterpretMove.interpret(lastMove, board);
						}
						
						// Construct our next move
						root = ConstructWhiteMove.run(board);
						
						System.out.println("root: " + root.bestChild.path);
						
						try {
							// Send the response to the server
							response = (HttpURLConnection) (new URL("http://www.bencarle.com/chess/move/" + input + "/209/fcbd8a97/" + root.bestChild.path + "/")).openConnection();
							sender = new BufferedReader(new InputStreamReader(response.getInputStream()));
							
							// If there was no error, interpret our own move
							InterpretMove.interpret(root.bestChild.path, board);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						
						System.out.println("RESPONSE: " + sender.readLine());
						response.disconnect();
						sender.close();
						
						timeout = 0;
						
						System.out.println("end");
						index += 2;
						
					// Something went wrong...
					// So try it again, with a random answer
					} else if (ready && timeout > 10) {
						System.out.println("TIMEOUT EXCEEDED");
						timeout = 0;
						Random rand = new Random();
						
						root = ConstructWhiteMove.run(board);
						int n = rand.nextInt(root.children.size());
						
						try {
							// Send the response to the server
							response = (HttpURLConnection) (new URL("http://www.bencarle.com/chess/move/" + input + "/209/fcbd8a97/" + root.children.get(n).path + "/")).openConnection();
							sender = new BufferedReader(new InputStreamReader(response.getInputStream()));
							
							// Interpret our own move
							InterpretMove.interpret(root.bestChild.path, board);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						
						System.out.println("RESPONSE: " + sender.readLine());
						response.disconnect();
						sender.close();
						
						
					} else if(ready) {
						timeout++;
					}
					
				// Now... Sleep!
				Thread.sleep(5000);
				
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		// We're the Black side
		} else {
			int index = 1;
			int moveNumber = 0;
			
			while (true) {
				
				try {
					// Query to see if it's our turn yet
					query = (HttpURLConnection) (new URL("http://www.bencarle.com/chess/poll/" + input + "/209/fcbd8a97/")).openConnection();
					reader = new BufferedReader(new InputStreamReader(query.getInputStream()));
					jsonString = reader.readLine();
					
					query.disconnect();
					reader.close();
					
					json = new JSONObject(jsonString);
					
					ready = (boolean) json.get("ready");
					moveNumber = json.getInt("lastmovenumber");
					
					System.out.println("LOOP: " + jsonString);

					
					if (ready && index == moveNumber) {
						
						lastMove = (String) json.get("lastmove");
						
						if (lastMove.length() > 0) {
							// Make changes to our board to reflect opponent's move
							InterpretMove.interpret(lastMove, board);
						}
						
						// Construct our next move
						root = ConstructBlackMove.run(board);
						
						System.out.println("root: " + root.bestChild.path);
						
						try {
							// Send the response to the server
							response = (HttpURLConnection) (new URL("http://www.bencarle.com/chess/move/" + input + "/209/fcbd8a97/" + root.bestChild.path + "/")).openConnection();
							sender = new BufferedReader(new InputStreamReader(response.getInputStream()));
							
							// If there was no error, interpret our own move
							InterpretMove.interpret(root.bestChild.path, board);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						
						System.out.println("RESPONSE: " + sender.readLine());
						response.disconnect();
						sender.close();
						
						timeout = 0;
						
						System.out.println("end");
						index += 2;
						
					// Something went wrong...
					// So try it again, with a random answer
					} else if (ready && timeout > 10) {
						System.out.println("TIMEOUT EXCEEDED");
						timeout = 0;
						Random rand = new Random();
						
						root = ConstructBlackMove.run(board);
						int n = rand.nextInt(root.children.size());
						
						try {
							// Send the response to the server
							response = (HttpURLConnection) (new URL("http://www.bencarle.com/chess/move/" + input + "/209/fcbd8a97/" + root.children.get(n).path + "/")).openConnection();
							sender = new BufferedReader(new InputStreamReader(response.getInputStream()));
							
							// Interpret our own move
							InterpretMove.interpret(root.bestChild.path, board);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						
						System.out.println("RESPONSE: " + sender.readLine());
						response.disconnect();
						sender.close();
						
						
					} else if (ready) {
						timeout++;
					}
					
				// Now... Sleep!
				Thread.sleep(5000);
				
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
