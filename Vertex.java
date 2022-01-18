package sixDegrees;
/**
 * Each vertex represents an Actor. Each actor is attached or linked to some other actors. 
 * The vertex keeps track of connections from other actors to help calculate the degree of separation.
 * @author Rashad Ramaileh & Isaac Finley
 *
 */
public class Vertex {
	String actor;
	Neighbor adjList;   // Each vertex/actor is attached to some neighbors. Linked actors. 
	Boolean visited;    // This flag is used in the BFS traversal of list. 
	int degree;			// Stores the degree of r/ship for Vertex. Used in computing degree.
	int whoBroughtYouIn;// Used to track back from Actor2 to Actor1 for Degree of separation. 
	
	/**
	 * Constructor method that creates a vertex. 
	 * @param _actor
	 * @param neighbors
	 */
	Vertex (String _actor, Neighbor neighbors) {
		actor = _actor;  // Store Actor name as Vertex in graph.
		adjList = neighbors;
		visited = false;
		degree = -1;
		whoBroughtYouIn = -1;
	}
	
	/**
	 * toString() method prints out the adjList for an actor.
	 */
	public String toString () {
		return actor + " : " + adjList;
	}
}
