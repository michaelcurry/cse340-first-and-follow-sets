/*************************************************************************
 *	Project: CSE340 - Project 1
 *	Author: Michael Curry
 *	Date: 2013.02.03
 *	Description: Main execution class for Project 1
 *************************************************************************/

import java.io.*;

public class Main {

	public static void main(String[] args) {
		// Define File Vars
		String file = args[0];
		String fileName = getInputName(file);

		// Get Raw Token Array
		String[] rawTokens = getInput(file).split("\\s+");

		// Init Token Array
		Token[] tokens = new Token[rawTokens.length];

		// Delete output file if exists
		deleteOutput(fileName);

		//Create Tokens Array (For Each Token in Raw Token Array DO:)
		int count = 0;
		for (String arg : rawTokens){
			// Get Token
			Token token = new Token(arg);
			// Allocate Token Onject to token array
			tokens[count] = token;
			// Comsole Output
				//token.oneline();
			// File Output
				//appendToOutput(fileName,token.token+" "+token.message+"\n");
			// Increment Count
			count++;
		}

		// Init Grammar Array
		Grammar grammar = new Grammar(tokens);
	}

	// Delete output file
	protected static void deleteOutput(String file) {
		File f = new File("./output/"+file+".out");
		if(f.exists()){
			f.delete();
		}
	}


	// Append to output file
	protected static void appendToOutput(String file, String str) {
		try{
			File f = new File("./output/"+file+".out");
			if(!f.exists()){
				f.createNewFile();
			}
			FileWriter fileWritter = new FileWriter("./output/"+file+".out",true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(str);
			bufferWritter.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	// Get input file name
	protected static String getInputName(String file) {
		File f = new File(file);
		return f.getName();
	}

	// File to String
	protected static String getInput(String file) {
		String result = null;
		DataInputStream in = null;

		try {
			File f = new File(file);
			byte[] buffer = new byte[(int) f.length()];
			in = new DataInputStream(new FileInputStream(f));
			in.readFully(buffer);
			result = new String(buffer);
		} catch (IOException e) {
			throw new RuntimeException("IO Error", e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {}
		}
		return result;
	}
}