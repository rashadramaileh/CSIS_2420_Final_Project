package sixDegrees;
import java.util.*;

/**
 * Creates the graph for the actors.
 * @author Rashad Ramaileh & Isaac Finley
 *
 */
public class Graph {
	Vertex [] adjLists;
	private int numActors;  // Keeps track of the number of actors
	
	/**
	 * Constructor creates the graph based on the number of actors passed along.
	 * @param x
	 */
	public Graph(int x) {
		// Create an Adjacency list of x actors (Over 2000 actors for our program)
		adjLists = new Vertex[x];
		numActors = 0; 
	}
	
	/**
	 * Add connections between actors 1 and 2 to Adjacency list.
	 * @param actor1
	 * @param actor2
	 * @param movie
	 */
	public void makeEdge (String actor1, String actor2, String movie) {
		int i, j;
		i = getVertex (actor1);
		j = getVertex (actor2);
		if (i == -1) {
			adjLists[numActors] = new Vertex(actor1, null);
			i = numActors;
			numActors++;
		}
		if (j == -1) {
			adjLists[numActors] = new Vertex(actor2, null);
			j = numActors;
			numActors++;
		}
		adjLists[i].adjList	 = new Neighbor (j, movie, adjLists[i].adjList);
		adjLists[j].adjList	 = new Neighbor (i, movie, adjLists[j].adjList);
	}
	
	/**
	 * Returns vertex index if it is present, else it returns -1.
	 * @param actor
	 * @return
	 */
	public int getVertex (String actor) {
		for (int i = 0; i < numActors; i++)
		{
			if (adjLists[i].actor.equals(actor)) return i;
		}
		return -1;
	}
	
	/**
	 * Returns index of vertex in list when given Vertex
	 * @param actor
	 * @return
	 */
	public int getVertex (Vertex actor) {
		for (int i = 0; i < numActors; i++) {
			if (adjLists[i].equals(actor))
				return i;
		}
		return -1;
	}
	
	/**
	 * For two given actors, method returns movie that connected them. 
	 * @param act1Index
	 * @param act2Index
	 * @return
	 */
	public String getMovie(int act1Index, int act2Index) {
		for (Neighbor nbr = adjLists[act1Index].adjList; nbr != null; nbr = nbr.next)
		{
			if (nbr.vertexNum == act2Index)
				return nbr.movie;
		}
		return null;
	}
	
	/**
	 * Compute path for given vector. Uses bfs algorithm to compute the path
	 * from actor1 to actor2 following relationships. 
	 * @param actor1
	 * @param actor2
	 * @return
	 */
	public int computePaths(String actor1, String actor2)
	{
		// Vertex path = new Vertex(actor1, null);
		Queue <Vertex> vertexQueue = new LinkedList<Vertex>();
		// Array of visited values. 
		
		// On queue, vertices are added in terms of levels. 1 level has act1 only. 
		// Next level of additions to queue is after all neighbors of act1 are enqueued.
		// Next level is after all neighbors of neighbors of ac1 are enqueued. And so on. 
		// We only go to the next level, degree of separation, after one level of neighbors is dequeued.
		
		int vertIndex = 0;  // Index to index into LinkedList array for actors. 
		vertIndex = getVertex(actor1);
		// Add vertex to Queue if it exists. Else return sentinel, there is no path!
		if (vertIndex != -1) vertexQueue.add(adjLists[vertIndex]);
		else return -1;
		
		// If actor2 is not in our List, return sentinel, there is no path! 
		if (getVertex(actor2) == -1 ) return -1;
		
		while(!vertexQueue.isEmpty()) {
			// Pop vertex from Queue
			Vertex act1 = vertexQueue.poll();  
			// Mark Vertex as visited.
			vertIndex = getVertex(act1);
			adjLists[vertIndex].visited = true;
			
			
			for (Neighbor nbr=act1.adjList; nbr!=null; nbr = nbr.next) 
			{
				// Add all vertex neighbors to queue, if they are not visited yet. 
				if (!adjLists[nbr.vertexNum].visited ) {
					vertexQueue.add(adjLists[nbr.vertexNum]);
					adjLists[nbr.vertexNum].visited = true;
					adjLists[nbr.vertexNum].degree = act1.degree + 1;  // Incrementing degree basing on parent.
					adjLists[nbr.vertexNum].whoBroughtYouIn = vertIndex;
					// Abandon search if degree is greater than 6
					if (adjLists[nbr.vertexNum].degree > 6)
						return -1;  // Sentinel value for high degree
				}
				// If we find needed actor, return degree.
				if (adjLists[nbr.vertexNum].actor.equals(actor2))
					return nbr.vertexNum;
			}
		}
		return -1;
	}
						
	/**
	 * Prints the current graph out in the terminal. 
	 */
	public void printGraph() {
		
		for (int i = 0; i < numActors; i++)
		{
			System.out.print(adjLists[i].actor);
			for (Neighbor nbr=adjLists[i].adjList; nbr != null; nbr = nbr.next)
			{
				System.out.print(" --> " + adjLists[nbr.vertexNum].actor + ": " + nbr.movie);
				System.out.println();
			}
			System.out.println("========================================================================");
		}
	}
}

