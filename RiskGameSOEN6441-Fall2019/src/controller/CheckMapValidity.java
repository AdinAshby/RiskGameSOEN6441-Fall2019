package controller;


import exception.InvalidMapException;
import model.MapBuilder;



public class CheckMapValidity {

	
	/**
     * public Constructor
	 * @throws InvalidMapException 
     */
	private MapBuilder map;
	 public CheckMapValidity(MapBuilder map) {
		 
 
	 }

	 public void isValid()  {
		 try {
		 if (map.getContinentListSize()<2) {
			 
			 throw new InvalidMapException("Continents are less than 2");
		 }
		 
		 } catch (InvalidMapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
	 
//	 public Boolean isValidFile(String fileOfMap, String nameOfFile) throws InvalidMapException {
//	        try {
//	        	
//	        	if( )
//	        	{
//	        		throw new InvalidMapException("file is not valid (the minimun size of continent is two)");
//	        	}
//	        }
//	 } catch (Exception e) {
//		 
//	 }
	        
}
