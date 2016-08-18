import java.util.*;
import java.io.*;

public class kwic_ADT{


	public static void main(String args[]){
		
		ArrayList<String> inputList = getInputList();
		ArrayList<String> ignoreList = getIgnoreList();

		ArrayList<String> circularList = getCircularList(inputList, ignoreList);

		ArrayList<String> sortedList = sortList(circularList);
		
		printResults(sortedList);
	
	}

	/**
	* Get the list of input
	* @Return  An arraylist of the input
	*/
	public static ArrayList<String> getInputList(){
		String inputFilename;
		ArrayList<String> inputList = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);

		do{
			System.out.print("Input File: ");
			inputFilename = sc.nextLine();
			inputList  = readFile(inputFilename);
		}while(inputList.size() == 0);

		return inputList;
	}

	/**
	* Get the list of ignore words
	* @Return  An arraylist of the ignore words
	*/
	public static ArrayList<String> getIgnoreList(){
		String ignoreWordsFilename;
		ArrayList<String> ignoreList = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);

		System.out.print("Ignore Words File: ");
		ignoreWordsFilename = sc.nextLine();

		ignoreList = readFile(ignoreWordsFilename);
		
		return ignoreList;
	}

	/**
	 * Read the specific textfile and print out all the text in it
	 * @Return An arraylist of content of the textfile
	 */
	public static  ArrayList<String> readFile(String fileName){
		// This will reference one line at a time
        String line = null;
        ArrayList<String> inputArrayList = new ArrayList<String>();
        try {

        	
        	//int count = 0;
        	
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			if(isFileEmpty(fileName)){
            	System.out.println("'" + fileName + "'' is empty");
            }else{
				while((line = bufferedReader.readLine()) != null) {
					//System.out.println(++count + ". " + line);
					inputArrayList.add(line);
				}   
			}			
            bufferedReader.close();   
            
        }catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                   
        }

       
        return inputArrayList;
	}
	
	/**
	 * Check if the file is empty
	 * @return true/false
	 */
	public static boolean isFileEmpty(String fileName){
		File file = new File(fileName);

		if(file.length() == 0){
			return true;
		}
		else{
			return false;
		}
	}


	/**
	* Get the ciruclar list
	* @return the circular list of all inputs 
	*/
	public static ArrayList<String> getCircularList(ArrayList<String> inputList, ArrayList<String> ignoreList){

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
	* Find the index of each sentence which match the ignored words 
	* @return A list of index of for sentence which match the ignore words
	*/
	public static ArrayList<Integer> ignoreWordsIndex(String sentence, ArrayList<String> ignoreList){

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

	/**
	* Circularly shift a sentence
	* @return the list of sentences of the circular shifted sentence
	*/
	public static ArrayList<String> circularShift(String sentence, ArrayList<String> ignoreList){
		ArrayList<String> circularList = new ArrayList<String>();

		ArrayList<Integer> indexArr = ignoreWordsIndex(sentence, ignoreList);
		String[] wordList = sentence.split(" ");

		int totalWords = wordList.length;
		

		for(int i=0; i<totalWords; i++){
			
			if(!indexArr.contains(i)){
				String newSentence = "";
				for(int j=i; j<totalWords; j++){
					newSentence += " " + wordList[j];
				}
				for(int k=totalWords-i-1; k<i; k++){
					newSentence += " " + wordList[k];
				}
				circularList.add(upperCaseFirstLetter(newSentence.trim()));
			
			}
			
		}
		
		return circularList;
	}

	/**
	* Captialise the first alphabet of each sentence
	*/
	public static String upperCaseFirstLetter(String sentence){
		return sentence.substring(0,1).toUpperCase() + sentence.substring(1);
	}

	/**
	* Sort the list in alphabetical order
	* @return return the sorted alphabetical list
	*/
	public static ArrayList<String> sortList(ArrayList<String> unsortedList){
		Collections.sort(unsortedList);
		ArrayList<String> sortedList = unsortedList;

		return sortedList;
	}

	/**
	* Print the output results
	*/
	public static void printResults(ArrayList<String> resultsList){
		for(String newSentence : resultsList){
			System.out.println(newSentence);
		}
	}

}