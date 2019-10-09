package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MapBuilder Class to Edit Map
 * 
 * @author f_yazdan
 *
 */
public class MapBuilder {

	final File mapFolder = new File("./MapFiles");

	private List<Continent> continentList = new LinkedList<Continent>();
	private List<Country> countryList = new LinkedList<Country>();

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	/**
	 * 
	 * @return continentList
	 */
	public List<Continent> getContinentList() {
		return continentList;
	}

	/**
	 * 
	 * @param continentList
	 */
	public void setContinentList(List<Continent> continentList) {
		this.continentList = continentList;
	}
        /**
	 * This method reads country name
	 */
	public String getCountryName(int countryId) {
		String countryName = "";
		ListIterator list_Iter = countryList.listIterator();

		while (list_Iter.hasNext()) {

			Country country = (Country) list_Iter.next();
			if (countryId == country.getCountryId())
				countryName = country.getCountryName();

		}
		return countryName;
	}
        /**
	 * This method reads country Id
	 */
	public int getCountryId(String countryName) {
		int countryId = 0;

		ListIterator list_Iter = countryList.listIterator();
		while (list_Iter.hasNext()) {
			Country country = (Country) list_Iter.next();
			if (countryName == country.getCountryName())
				countryId = country.getCountryId();
		}
		return countryId;
	}
        /**
	 * This method reads continent name
	 */
	public String getContinentName(int continentId) {

		ListIterator list_Iter = continentList.listIterator();
		String continentName = "";
		while (list_Iter.hasNext()) {

			Continent continent = (Continent) list_Iter.next();
			if (continentId == continent.getContinentId())
				continentName = continent.getContinentName();
		}
		return continentName;

	}
         /**
	 * This method reads continent Id
	 */
	public int getContinentId(String continentName) {

		ListIterator list_Iter = continentList.listIterator();
		int continentId = 0;
		while (list_Iter.hasNext()) {

			Continent continent = (Continent) list_Iter.next();
			if (continentName == continent.getContinentName())
				continentId = continent.getContinentId();
		}
		return continentId;

	}
        /**
	 * This method adds a new continent
	 */
	public void addContinent(Continent c) {
		continentList.add(c);
	}
        /**
	 * This method removes a continent 
	 */
	public void removeContinent(String continentName) {

		ListIterator list_Iter = continentList.listIterator();
		int continentId = 0;
		while (list_Iter.hasNext()) {

			Continent continent = (Continent) list_Iter.next();
			if (continentName == continent.getContinentName())
				continentList.remove(continent);
		}
	}

  
	
	
	/**
	 * This method will print List of Continents
	 */

	public void printContinentList() {

		ListIterator list_Iter = continentList.listIterator();
		while (list_Iter.hasNext()) {

			Continent continent = (Continent) list_Iter.next();

			System.out.println("continent name is " + continent.getContinentName() + ", continent Id is: "
					+ continent.getContinentId());
			continent.showContinentAdjacency();
			continent.printCountryList();
		}

		System.out.println("------------------------\n");

	}
         /**
	 * This method will print list of maps
	 */
	public void getListOfMaps() {

		for (final File fileEntry : mapFolder.listFiles()) {
			// if (fileEntry.isDirectory()) {
			// getListOfMaps(fileEntry);
			// } else {
			System.out.println(fileEntry.getName());
		}
	}

	public void readMap(String fileName) throws Exception {

		File file = new File(mapFolder + "/" + fileName + ".map");

		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));


		 StringBuffer stringBuffer = new StringBuffer();
		 String fileContent="";
		 String line = null;

		 while((line =bufferedReader.readLine())!=null){
		  stringBuffer.append(line).append("\n");
		  fileContent+=line+"\n";
		 }
		 String patternString = "(?<=\\[continents\\]\\s)([\\w\\_\\-]*\\s(\\d)*\\s(\\#\\w{6}|\\w*)\\s)*";
		 Pattern pattern = Pattern.compile(patternString);
		       Matcher matcher = pattern.matcher(fileContent);
		       String continentLines="";
		       if(matcher.find()) {
		           continentLines=matcher.group(0);
		       }
		       System.out.println(continentLines);
		       
		       patternString = "((([\\w\\_\\-]*)\\s(\\d)*\\s((\\#\\w{6}|\\w*)))*\\s)";
		       pattern = Pattern.compile(patternString);
		       matcher = pattern.matcher(continentLines);
		       int count = 0;
		       String continentDetail="";
		       String continentName="";
		       String continentColor="";
		       String continentValue="";
		       //System.out.println("Found "+matcher.groupCount()+" Continents\n-------------\n");
		       while(matcher.find()) {
		       	count++;
		          continentDetail=matcher.group();
		          continentName=matcher.group(3);
		          continentValue=matcher.group(4);
		          continentColor=matcher.group(5);
		          System.out.println("Found Continent "+count+" "+continentName+"  Value="+continentValue+" Color:"+continentColor+" --\n");
		       }
		       

		}

		

//		while ((line = buf.readLine()) != null) {
//			System.out.println("zzzzzzzzzzzzzzzzzz  " + line);
//			if (isValidContinent == true) {
//				System.out.println("Continent Found  " + line);
//			}
//			if (line.equals("[continents]")) {
//
//				System.out.println("Continent Start ");
//				isValidMap = true;
//				isValidContinent = true;
//				continue;
//
//			} else {
//				System.out.println(line);
//				continue;
//
//			}

			// }else {
			// System.out.println("NO MATCH");
			// isValid= false;
			// }

			// process the line

		

//		br.close();
	

	public String mapFormat(String fileName) {
		String mapCountries = "";
		String mapContent = "name " + fileName + " Map\r\n" + "\r\n" + "[files]\r\n" + "\r\n" + "[continents]\r\n";

		for (Continent c : continentList) {
			mapContent += c.getContinentName() + " " + c.getContinentControlValue() + "\r\n";
			List<Country> countryList = c.getCountriesList();
			for (Country co : countryList) {
				mapCountries += co.getCountryId() + " " + co.getCountryName() + "\r\n";
			}
		}
		mapContent += "\r\n[countries]\r\n" + mapCountries;

		return mapContent;
	}

	public void writeMap(String fileName) throws Exception {

		File file = new File(mapFolder + "/" + fileName + ".map");

		BufferedWriter br = new BufferedWriter(new FileWriter(file));

		br.write(mapFormat(fileName));

		br.close();
	}

}
