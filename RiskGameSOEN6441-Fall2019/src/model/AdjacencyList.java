package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

public class AdjacencyList {

	private Map<Integer, List<Integer>> adjacencyList = new HashMap<Integer, List<Integer>>();

	public void addVertex(int id) {

		adjacencyList.putIfAbsent(id, new CopyOnWriteArrayList<Integer>());

	}

	public void removeVertex(int id) {

		adjacencyList.remove(id);

	}

	public void addEdge(int start, int end) {
		System.out.println("start show  "+start+ " end show  "+ end);
		List<Integer> list;
		
		list = adjacencyList.get(start);
		
		if (!list.contains(end)) {
			list.add(end);
			
		}
		
//		list = adjacencyList.get(end);
//		
//		
//		if (!list.contains(start)) {
//			list.add(start);
//		};
	}

	public void showListEdges() {
		for (Entry<Integer, List<Integer>> entry : adjacencyList.entrySet())
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

	}

	public void removeEdge(int start, int end) {
		List<Integer> list = null;
		
	
		if (adjacencyList.containsKey(start)) {
			list = adjacencyList.get(start);
			Iterator<Integer> iter = list.iterator();
			
			int counter = 0;

			while(iter.hasNext()){
			    int a = iter.next();
				if (a == end) {

					list.remove(counter);
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

			while(iter.hasNext()){
			    int a = iter.next(); 
				if (a == start) {

					list.remove(counter);
				}
				counter++;
				System.out.println(a);
			}
		} else
			System.out.println("there is no key");
	}

	public static void main(String[] args) {

		AdjacencyList test = new AdjacencyList();
		
		test.addVertex(1);
		test.addVertex(2);
		test.addVertex(3);
		test.addVertex(3);
		test.addEdge(1, 3);
		test.addEdge(1, 2);
		test.addEdge(1, 3);
		test.showListEdges();
		test.removeEdge(1, 3);
		test.showListEdges();
	}
	//

}