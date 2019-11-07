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
 * 
 * @author f_yazdan
  * @author s_shehna
 * 
 */
public class AdjacencyList {
	/**
	 * This is private adjacencyList object of HashMap
	 */

	private Map<Integer, ArrayList<Integer>> adjacencyList = new HashMap<Integer, ArrayList<Integer>>();

	/**
	 * This method adds a vertex
	 * 
	 * @param id
	 */
	public void addVertex(int id) {

		adjacencyList.putIfAbsent(id, new ArrayList<Integer>());

	}

	/**
	 * This method get the size
	 * 
	 * @return size of the adjacency list
	 */
	public int getSize() {
		return adjacencyList.size();
	}

	/**
	 * This method returns keys of adjacency list
	 * 
	 * @return keys
	 */
	public ArrayList<Integer> getKeys() {
		ArrayList<Integer> keys = new ArrayList<Integer>();
		for (Integer key : adjacencyList.keySet()) {
			keys.add(key);
		}
		return keys;
	}

	/**
	 * @param key
	
	 * @param key
	 *            
	 * @return
	 */
	public ArrayList<Integer> getValues(int key) {
		return adjacencyList.get(key);
	}

	/**
	 * This method is for getting the adjacency list
	 * 
	 * @return adjacencyList
	 */

	public Map<Integer, ArrayList<Integer>> getAdjacencyList() {
		return adjacencyList;
	}

	/**
	 * This method is for setting the adjacency list
	 * 
	 * @param adjacencyList
	 */

	public void setAdjacencyList(Map<Integer, ArrayList<Integer>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	/**
	 * This method returns the adjacency List of Vertex
	 * @param vertexId
	 * @param vertexId
	 * @return adjacency List
	 */
	public ArrayList<Integer> getVertexAdjacency(int vertexId) {
		return adjacencyList.get(vertexId);
	}

	/**
	 * This method return String of the Vertex Adjacency
	 * @param vertexId
	 * @return
	 */
	public String getVertexAdjacencyString(int vertexId) {
		ArrayList<Integer> Adjlist = adjacencyList.get(vertexId);
		String list = "";
		if (Adjlist != null) {

			for (int adj : Adjlist) {
				list += adj + " ";
			}
		}
		return list;

	}

	/**
	 * This method removes a vertex
	 * 
	 * @param id
	 */
	public void removeVertex(Integer id) {

		for (Entry<Integer, ArrayList<Integer>> entry : adjacencyList.entrySet()) {
			ArrayList<Integer> values = entry.getValue();
			values.remove(id);
		}
		adjacencyList.remove(id);

	}

	/**
	 * This method adds an edge.
	 * 
	 * @param id
	 */
	public void addEdge(int start, int end) {
		// System.out.println("Add Edge between " + start + " to " + end);

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
	 * @param start
	 *            specifies start of edge
	 * @param end
	 *            specifies end of edge
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
			}

		} else
			System.out.println("there is no key");
	}

	/**
	 * This method shows list of edges
	 * 
	 * @return listEdges
	 */
	public String showListEdges() {
		String listEdges = "";
		for (Entry<Integer, ArrayList<Integer>> entry : adjacencyList.entrySet()) {
			listEdges += entry.getKey() + " ";
			ArrayList<Integer> values = entry.getValue();
			for (int value : values) {
				listEdges += value + " ";
			}
			;
			listEdges += "\n";
		}
		return listEdges;

	}

	/**
	 * This method check whether the map is commented graph
	 * 
	 * @return true
	 */
	public boolean isConnected() {
		if (adjacencyList.size() == 0) {
			return false;
		}
		for (Entry<Integer, ArrayList<Integer>> entry : adjacencyList.entrySet()) {
			ArrayList<Integer> values = entry.getValue();
			if (values.size() == 0) {
				return false;
			}
		}
		return true;
	}

	public boolean isAdjacent(int vertex1, int vertex2) {
		
		ArrayList<Integer> adjacencyList = getVertexAdjacency(vertex1);
		if (adjacencyList.contains(vertex2)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This main method test the other methods here to check the methods of this
	 * class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		AdjacencyList test = new AdjacencyList();

		test.addVertex(555);
		System.out.println(test.isConnected());
		test.addVertex(622);
		test.addVertex(8686);
		test.addVertex(45);
		test.addEdge(555, 8686);
		test.addEdge(622, 8686);
		System.out.println(test.isConnected());
		test.addEdge(45, 555);
		System.out.println(test.isConnected());
		System.out.println(test.showListEdges());
		test.removeEdge(45, 555);
		System.out.println(test.showListEdges());
		test.removeVertex(555);
		System.out.println(test.showListEdges());

	}

}
