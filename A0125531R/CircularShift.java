import java.util.*;
import java.io.*;

public class CircularShift{
	/**
	* Get the ciruclar list
	* @return the circular list of all inputs 
	*/
	public ArrayList<String> getCircularList(ArrayList<String> inputList, ArrayList<String> ignoreList){

		ArrayList<String> circularList = new ArrayList<String>();

		for(String input: inputList){
			input = input.trim();
			ArrayList<String> sentenceList = circularShift(input, ignoreList);
			for(String sentence : sentenceList){
				circularList.add(sentence);
			}
		} 

		return circularList;
	}

	/**
	* Circularly shift a sentence
	* @return the list of sentences of the circular shifted sentence
	*/
	private static ArrayList<String> circularShift(String sentence, ArrayList<String> ignoreList){
		

		ArrayList<String> sentenceList = new ArrayList<String>();

		ArrayList<Integer> indexArr = ignoreWordsIndex(sentence, ignoreList);
		String[] wordList = sentence.split(" ");

		wordList = capitaliseWord(wordList, indexArr);
		int totalWords = wordList.length;

		for(int i=0; i<totalWords; i++){
			
			String newSentence = "";

			if (!indexArr.contains(i)){
				for(int j=i; j<totalWords; j++){
					newSentence += " " + wordList[j];
				}
				
				for(int k=0; k<i; k++){
					newSentence += " " + wordList[k];
				}

				sentenceList.add(newSentence.trim());
			
			}
			
		}
		
		return sentenceList;
	}

	/**
	* Find the index of each sentence which match the ignored words 
	* @return A list of index of for sentence which match the ignore words
	*/
	private static ArrayList<Integer> ignoreWordsIndex(String sentence, ArrayList<String> ignoreList){

		//ArrayList<String> ignoreList = new ArrayList<String> (Arrays.asList(new String []{"is", "the", "of", "and", "as", "a", "after"}));

		String[] wordList = sentence.split(" ");
		ArrayList<Integer> indexArr = new ArrayList<Integer>();

		for(int i=0; i<wordList.length; i++){
			if(ignoreList.contains(wordList[i].toLowerCase())){
				indexArr.add(i);
			}
		}
		
		
		return indexArr;
	}

	private static String[] capitaliseWord(String[] wordList, ArrayList<Integer> indexArr){
		AlphaShift as = new AlphaShift();

		int totalWords = wordList.length;
		
		for(int i=0; i<totalWords; i++){	
			if (indexArr.contains(i)){
				wordList[i] = as.lowerCaseWord(wordList[i]);
			}else{
				wordList[i] = as.upperCaseFirstLetter(wordList[i]);	

			}
		}

		return wordList;
	}
	


}