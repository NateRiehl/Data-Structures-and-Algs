import java.util.Stack;
/**
 * @author Nate Riehl
 *CS216, Fall 2015
 *HW 3
 *
 *Implementation of Mouse class for MazeApp
 */
public class Mouse {
	private Maze myMaze;
	private Stack<Position> myStack;
	private Position myPosition;

	public Mouse(Maze maze){
		myMaze = maze;
		myStack = new Stack<Position>();
		myPosition = new Position(1,0);
		myStack.push(myPosition);
	}
	/**
	 * Returns the mouse's current position 
	 * @return Mouse's current position 
	 */
	public Position getPosition(){
		return myPosition;
	}
	/**
	 * Checks if the mouse has completed the maze
	 * @return true if completed. False otherwise
	 */
	public boolean isOut(){
		int rowEnd = myMaze.getRows()-2;
		int colEnd = myMaze.getCols() - 1;

		if(myPosition.equals(new Position(rowEnd, colEnd))){
			return true;
		}
		return false;
	}
	/**
	 * Returns the stack of Positions that have been moved to
	 * @return stack of previously-moved-to Positions
	 */
	public Stack<Position> getStack(){
		return myStack;
	}
	/**
	 * Implements in-class algorithm to choose the next move for Mouse	
	 * @return true if a move is made. False otherwise
	 */
	public boolean makeMove(){
		char[] direction = {'n', 'e', 's', 'w'};

		for(char d : direction){
			if(myMaze.isOpen(myPosition, d)){
				Position next = myPosition.getAdjacent(d);
				myStack.push(next);
				myMaze.mark(myPosition, 3);
				myPosition = next;
				return true;
			}
		}
		if(myStack.size() > 1){
			myStack.pop();
			Position previous = myStack.peek();
			myMaze.mark(myPosition, 2);
			myPosition = previous;
			return true;
		}
		return false;
	}
}
