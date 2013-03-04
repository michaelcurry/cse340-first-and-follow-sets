/*************************************************************************
 *	Project: CSE340 - Project 1
 *	Author: Michael Curry
 *	Date: 2013.03.03
 *	Description: Main execution class for Project 1
 *************************************************************************/

import java.io.*;

public class Main {
	// Init Vars
	private static Boolean DEBUG = true;

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
		deleteOutput(fileName+".DEBUG");

		// DEBUG Section
		if (DEBUG) {
			// INPUT
			System.out.println("== INPUT ==");
			appendToOutput(fileName+".DEBUG","== INPUT ==\n");
			System.out.println(getInput(file));
			appendToOutput(fileName+".DEBUG",getInput(file)+"\n");
			// TOKEN
			System.out.println("== TOKENS ==");
			appendToOutput(fileName+".DEBUG","== TOKENS ==\n");
		}

		//Create Tokens Array (For Each Token in Raw Token Array DO:)
		int count = 0;
		for (String arg : rawTokens){
			// Get Token
			Token token = new Token(arg);
			// Allocate Token Onject to token array
			tokens[count] = token;
			// DEBUG Section
			if (DEBUG) {
				// Console Output
				token.oneline();
				// File Output
				appendToOutput(fileName+".DEBUG",token.token+" "+token.message+"\n");
			}
			// Increment Count
			count++;
		}

		// DEBUG Section
		if (DEBUG) {
			System.out.println("== GRAMMAR ==");
			appendToOutput(fileName+".DEBUG","== GRAMMAR ==\n");
		}

		// Init Grammar Array
		Grammar grammar = new Grammar(tokens);
		if (grammar.type == "ERROR") {
			System.out.println(grammar.message);
			appendToOutput(fileName,grammar.message);
			if (DEBUG) {
				appendToOutput(fileName+".DEBUG",grammar.message);
			}
			return;
		} else {
			System.out.print(grammar.toString());
			// DEBUG Section
			if (DEBUG) {
				appendToOutput(fileName+".DEBUG",grammar.toString());
			}
		}

		// DEBUG Section
		if (DEBUG) {
			System.out.println("== FIRST ==");
			appendToOutput(fileName+".DEBUG","== FIRST ==\n");
		}
		// First Sets
		for (Token nonTerminal : grammar.nonTerminals) {
			if (nonTerminal != null) {
				// For each Nonterminal get First Sets
				String firstString = "FIRST(" + nonTerminal.token + ") = {";
				for (Token token : grammar.first(nonTerminal)) {
					if (token != null) {
						firstString += token.token + ", ";
					}
				}
				firstString += "}";
				firstString = firstString.replace(", }","}");
				// DEBUG Section
				if (DEBUG) {
					appendToOutput(fileName+".DEBUG",firstString+"\n");
				}
				// Output First Set
				System.out.println(firstString);
				appendToOutput(fileName,firstString+"\n");
			}
		}

		// DEBUG Section
		if (DEBUG) {
			System.out.println("== FOLLOW ==");
			appendToOutput(fileName+".DEBUG","== FOLLOW ==\n");
		}
		// FOLLOW Sets
		for (Token nonTerminal : grammar.nonTerminals) {
			if (nonTerminal != null) {
				// For each Nonterminal get First Sets
				String followString = "FOLLOW(" + nonTerminal.token + ") = {";
				for (Token token : grammar.follow(nonTerminal)) {
					if (token != null) {
						followString += token.token + ", ";
					}
				}
				followString += "}";
				followString = followString.replace(", }","}");
				// DEBUG Section
				if (DEBUG) {
					appendToOutput(fileName+".DEBUG",followString+"\n");
				}
				// Output First Set
				System.out.println(followString);
				appendToOutput(fileName,followString+"\n");
			}
		}

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