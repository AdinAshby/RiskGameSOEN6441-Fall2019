package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
/**
* This class is made to implement neighbours of countries
*/
public class AdjacencyList {

	private Map<Integer, ArrayList<Integer>> adjacencyList = new HashMap<Integer, ArrayList<Integer>>();

	/**
	 * This method adds a vertex
	 * 
	 * @param id 
	 */
	public void addVertex(int id) {

		adjacencyList.putIfAbsent(id, new ArrayList<Integer>());

	}

	public Map<Integer, ArrayList<Integer>> getAdjacencyList() {
		return adjacencyList;
	}
        
	public void setAdjacencyList(Map<Integer, ArrayList<Integer>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}
        /**
	 * This method removes a vertex
	 * 
	 * @param id 
	 */
	public void removeVertex(int id) {

		adjacencyList.remove(id);

	}
	////
        /**
	 * This method adds an edge.
	 * 
	 * @param id 
	 */
	public void addEdge(int start, int end) {
		System.out.println("Add Edge between "+start+" to "+end);
		
		List<Integer> list;
		list = adjacencyList.get(start);
		
		if (!list.contains(end)) {
			list.add(end);
		}

		list = adjacencyList.get(end);
		if (!list.contains(start)) {
			list.add(start);
		}
		
	}
        /**
	 * This method removes an edge.
	 * 
	 * @param start specifies start of edge
	 * @param end   specifies end of edge
	 */
	public void removeEdge(int start, int end) {
		List<Integer> list = null;

		if (adjacencyList.containsKey(start)) {

			list = adjacencyList.get(start);
			Iterator<Integer> iter = list.iterator();

			int counter = 0;

			while (iter.hasNext()) {
				int a = iter.next();
				if (a == end) {

					list.remove(counter);
					break;

				}
				counter++;
				System.out.println(a);
			}

		} else
			System.out.println("there is no key");

		if (adjacencyList.containsKey(end)) {

			list = adjacencyList.get(end);
			Iterator<Integer> iter = list.iterator();
			int counter = 0;

			while (iter.hasNext()) {
				int a = iter.next();
				if (a == start) {

					list.remove(counter);
					break;
				}
				counter++;
				System.out.println(a);
			}

		} else
			System.out.println("there is no key");
	}
         /**
	 * This method shows list of edges
	 */
	public void showListEdges() {
		for (Entry<Integer, ArrayList<Integer>> entry : adjacencyList.entrySet())
			//System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			System.out.println("Key="+ entry.getKey() +" Adj = " + entry.getValue());

	}

	public static void main(String[] args) {

		AdjacencyList test = new AdjacencyList();

		test.addVertex(555);
		test.addVertex(622);
		test.addVertex(8686);
		test.addVertex(45);
		test.addEdge(555, 8686);
		test.addEdge(622, 8686);
		test.addEdge(45, 555);
		test.showListEdges();
		test.removeEdge(45, 555);
		test.showListEdges();
	}
	//

}
