package sixDegrees;

/**
 * Creates a neighbor. A neighbor has a vertex number, a movie, and the neighbor its next to. 
 * @author Rashad Ramaileh & Isaac Finley
 *
 */
public class Neighbor {
	int vertexNum; 
	String movie;
	Neighbor next;
	
	/**
	 * Construcor method that creates a neighbor. 
	 * @param num
	 * @param _movie
	 * @param ngh
	 */
	public Neighbor (int num, String _movie, Neighbor ngh ) {
		vertexNum = num;
		movie = _movie;
		next = ngh;
	}
	
	/**
	 * toString() method prints neighbor. 
	 */
	public String toString() {
		return ":--> " + movie + next;
	}
}
