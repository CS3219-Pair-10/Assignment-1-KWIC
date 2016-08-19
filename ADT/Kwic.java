import java.util.*;
import java.io.*;

public class Kwic{


	public static void main(String args[]){
		
		ArrayList<String> inputList = getInputList();
		ArrayList<String> ignoreList = getIgnoreList();
		
		CircularShift cs = new CircularShift();
		ArrayList<String> circularList = cs.getCircularList(inputList, ignoreList);

		AlphaShift as = new AlphaShift();
		ArrayList<String> sortedList = as.sortList(circularList);
		
		printResults(sortedList);
	}

	/**
	* Get the list of input
	* @Return  An arraylist of the input
	*/
	private static ArrayList<String> getInputList(){
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
	private static ArrayList<String> getIgnoreList(){
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
	private static  ArrayList<String> readFile(String fileName){
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
	private static boolean isFileEmpty(String fileName){
		File file = new File(fileName);

		if(file.length() == 0){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	* Print the output results
	*/
	private static void printResults(ArrayList<String> resultsList){
		for(String newSentence : resultsList){
			System.out.println(newSentence);
		}
	}

}