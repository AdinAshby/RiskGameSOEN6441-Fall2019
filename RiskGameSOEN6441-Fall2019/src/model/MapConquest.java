package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * This is MapConquest class which extends MapGeo
 * 
 * @author f_yazdan
 * @author s_shehna
 * @author AdinAshby
 */
public class MapConquest extends MapGeo {
	/**
	 * This method is for reading the conquest map
	 * 
	 * @param mapFileName
	 */
	public boolean readConquest(String mapFileName) {

		File file = new File(mapFolder + "/" + mapFileName + ".map");
		if (!file.exists()) {
			System.out.println(mapFileName + " conquest map file not found. Please try again");
			return false;
		} else {
			System.out.println("Read Conquest File: " + mapFolder + "/" + mapFileName + ".map");
		}

		continentList = new HashMap<Integer, Continent>();
		setCountryAdjacency(new AdjacencyList());
		Continent.setContinentsCounter(0);

		BufferedReader bufferedReader = null;
		try {

			bufferedReader = new BufferedReader(new FileReader(file));

			StringBuffer stringBuffer = new StringBuffer();
			String fileContent = "";
			String line = null;

			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line).append("\n");
				fileContent += line + "\n";
			}
			bufferedReader.close();
			String patternString = "(?<=\\[Continents\\]\\s)(([\\w\\_\\- ]*)=(\\d)+\\s)*";
			Pattern pattern = Pattern.compile(patternString);
			Matcher matcher = pattern.matcher(fileContent);
			String continentLines = "";
			if (matcher.find()) {
				continentLines = matcher.group(0);
			}

			// Start getting continents
			System.out.println("---------Loading Continent-------------");
			patternString = "([\\w\\_\\- ]*)=(\\d)+\\s";
			pattern = Pattern.compile(patternString);
			matcher = pattern.matcher(continentLines);
			int count = 0;
			String continentDetail = "";
			String continentName = "";
			String continentValue = "";

			while (matcher.find()) {
				count++;
				continentDetail = matcher.group();
				continentName = matcher.group(1);
				continentValue = matcher.group(2);

				Continent continent = new Continent(continentName, Integer.parseInt(continentValue));
				addContinent(continent);
				System.out.println("Found Continent " + continent.getContinentId() + " " + continentName + "  Value="
						+ continentValue);
			}

			// Start getting countries
			System.out.println("\n---------Loading Country-------------");
			patternString = "(?<=\\[Territories\\]\\s)([\\w\\d\\,\\s]*)";
			pattern = Pattern.compile(patternString);
			matcher = pattern.matcher(fileContent);
			String countryLines = "";
			int CountryId = 0;
			if (matcher.find()) {
				countryLines = matcher.group(0);
			}
			
			Map<Integer, String[]> AdjCountryList=new HashMap<Integer, String[]>();
			
			String[] countriesOfContinent = countryLines.split("\n\n");
			
			for (int cc = 0; cc < countriesOfContinent.length; cc++) {
				
				String[] countryLine = countriesOfContinent[cc].split("\n");

				
				for (int cl = 0; cl < countryLine.length; cl++) {
					
					String[] currentLine = countryLine[cl].split(",");
					
					String countryName = currentLine[0];
					int countryId = CountryId++;
					String[] AdjThisCountry=new String[currentLine.length-4];
					for(int i=4; i<currentLine.length;i++) {
						AdjThisCountry[i-4]=currentLine[i];	
					}
					AdjCountryList.put(CountryId,AdjThisCountry );
					Country country = new Country(countryName, CountryId);
					getCountryAdjacency().addVertex(countryId);
//					System.out.println(getContinent(cc+1).getContinentName()+"\n\n");
					getContinent(cc+1).addCountry(country);
					System.out.println(CountryId+"- Adding " + countryName + " to " + getContinent(cc+1).getContinentName());
				}
			}
			System.out.println(Arrays.toString(AdjCountryList.get(31)));
			for (Map.Entry<Integer, String[]> entry : AdjCountryList.entrySet()) {
				Integer countryId = entry.getKey();
				String[] AdjThisCountry = entry.getValue();
				//System.out.println("CC="+AdjCountryList.size());
				for(int j=0;j<AdjThisCountry.length;j++) {
					String countryName=AdjThisCountry[j];
					int AdjCountyId=getCountryIdByName(countryName);
					System.out.println("Adding "+countryId+" To "+AdjCountyId);
					addCountryAdjacency(countryId,AdjCountyId );
					}
			}
			
			
		//	System.out.println("Adj for 8"+this.getCountryAdjacency(8));
			
			//System.out.println(getCountryById(3).getCountryName()+"="+Arrays.toString(AdjCountryList.get(3)));
			
			
			
			
		//	System.out.println(getCountryByName("Gray").getContinentName());
			//continentList.get(8).getCountriesList()
			//System.out.println(this.getCountryListNames("Queensbasin"));

//				System.out.println("\n-------------\n"+countryOfContinent[i]+"\n-------------\n");

			// getCountryAdjacency().addVertex(countryId);

//				System.out.println("Add Adj for " + c.getCountryName() + " Adj=" + adjCountriesContent);
//				String[] arrOfAdj = adjCountriesContent.split(" ");
//				for (String adj : arrOfAdj)
//					addCountryAdjacency(countryId, Integer.parseInt(adj));

			System.out.println("-------------------------------");

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (Exception e) {
			System.out.println("Map is invalid");
		}
		
		return true; //validateMap();
	}


	/**
	 * This method is for write the conquest map
	 */
	/**
	 * This method format the map
	 * 
	 * @param fileName
	 * @return
	 */
	public String mapFormat(String fileName) {
		String mapCountries = "";
		String mapContent = "name " + fileName + " Map\r\n" + "\r\n" + "[files]\r\n" + "\r\n" + "[continents]\r\n";
		Iterator<Entry<Integer, Continent>> it = continentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Continent> continentMap = (Map.Entry<Integer, Continent>) it.next();
			int continentId = (int) continentMap.getKey();
			Continent c = continentList.get(continentId);
			mapContent += c.getContinentName() + " " + c.getContinentControlValue() + " #FFFFFF\r\n";
			List<Country> countryList = c.getCountriesList();
			for (Country co : countryList) {
				mapCountries += co.getCountryId() + " " + co.getCountryName() + " " + continentId + " 0 0\r\n";
			}
		}
		mapContent += "\r\n[countries]\r\n" + mapCountries;

		mapContent += "\r\n[borders]\r\n" + showCountryAdjacency();
		return mapContent;
	}

	/**
	 * This method write the map with a string file name
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public boolean writeConquest(String fileName) throws Exception {

		if (validateMap()) {
			File file = new File(mapFolder + "/" + fileName + ".map");

			BufferedWriter br = new BufferedWriter(new FileWriter(file));

			br.write(mapFormat(fileName));

			br.close();
		} else {
			System.out.println("Map is not valid, we can not save it");
		}
		return true;
	}
}
