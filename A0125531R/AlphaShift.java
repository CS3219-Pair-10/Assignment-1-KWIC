import java.util.*;
import java.io.*;

public class AlphaShift{

	/**
	* Sort the list in alphabetical order
	* @return return the sorted alphabetical list
	*/
	public ArrayList<String> sortList(ArrayList<String> unsortedList){
		Collections.sort(unsortedList);
		ArrayList<String> sortedList = unsortedList;

		return sortedList;
	}

	/**
	* Captialise the first alphabet of each word
	* @return captialise the first alphabet of each word
	*/
	public String upperCaseFirstLetter(String word){
		return word.substring(0,1).toUpperCase() + word.substring(1);
	}

	/**
	* Lower case the word
	* @return lower case of each word
	*/
	public String lowerCaseWord(String word){
		return word.toLowerCase();
	}
}