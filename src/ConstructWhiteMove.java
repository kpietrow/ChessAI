import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * This class will handle the construction of the next move
 */
public class ConstructWhiteMove {
	
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
	 * This class is dedicated to the white player, so only white's situation will
	 * be considered.
	 */
	public int eval(Board board) {
		
		int evalValue = 0;
		
		// Adds values of white pieces, subtracts that of black
		for (int i = 0; i < board.whitePieces.length; i++) {
			if (board.whitePieces[i].active) {
				evalValue += board.whitePieces[i].value;
			}
			
			if (board.blackPieces[i].active) {
				evalValue -= board.blackPieces[i].value;
			}
		}
			
		
		return evalValue;
	}
	
}
