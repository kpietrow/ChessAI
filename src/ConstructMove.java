import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * This class will handle the construction of the next move
 */
public class ConstructMove {
	
	public static void run(String gameID, Board board) {
		
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
		
		System.out.println("done!");
		
		
	}
	
	/*
	 * This is going to be difficult. This is where the evaluation function will go.
	 * 
	 * Going to do a simple one to start off. Each piece will be added up. 
	 * I am going to take into consideration white and black, with a true 'white'
	 * parameter treated as the AI being white, and a false value means the AI is black
	 */
	public int eval(boolean white, Board board) {
		
		int evalValue = 0;
		
		if (white) {
			// Adds values of white pieces, subtracts that of black
			for (int i = 0; i < board.whitePieces.length; i++) {
				evalValue += board.whitePieces[i].value;
				evalValue -= board.blackPieces[i].value;
			}
			
		// Adds values of black pieces, subtracts that of white	
		} else {
			for (int i = 0; i < board.whitePieces.length; i++) {
				evalValue -= board.whitePieces[i].value;
				evalValue += board.blackPieces[i].value;
			}
		}
		
		return evalValue;
	}
	
}
