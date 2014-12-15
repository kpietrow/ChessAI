import java.util.ArrayList;


public class Node {
	public Board board;
	public ArrayList children = new ArrayList();
	public String type;		// branch, leaf, root. Branches are assumed to be player moves,
							// while roots are assumed to be opponents moves
	public Node parent;		// Node or null
	public int evalValue;	// The evaluation value
	
	// Root Node
	public Node(Board aBoard, String aType) {
		board = aBoard;
		type = aType;
		parent = null;
	}
	
	// Branch Node
	public Node(Board aBoard, String aType, Node aParent) {
		board = aBoard;
		type = aType;
		parent = parent;
	}
	
	// Leaf Node
	public Node(Board aBoard, String aType, Node aParent, int eval) {
		board = aBoard;
		type = aType;
		parent = aParent;
		evalValue = eval;
	}
}
