/*************************************************************************
 *	File: Statment.java
 *	Project: CSE340 - Project
 *	Author: Michael Curry
 *	Date: 2013.02.28
 *	Description: Statment class for project 2
 *************************************************************************/

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Statment {

	// Init Vars
	public Token nonTerminal;
	public Token[] definitions = new Token[100];

	// Statment constructor
	public Statment(){

	}

	// Print oneline
	public void oneline() {
		String definitionString = "";
		for (Token token : definitions){
			if (token != null) {
				definitionString += token.token + " ";
			}
		}
		System.out.println(nonTerminal.token + " - " + definitionString);
	}

	// toString
	public String toString() {
		String definitionString = "";
		for (Token token : definitions){
			if (token != null) {
				definitionString += token.token + " ";
			}
		}
		return nonTerminal.token + " - " + definitionString;
	}
}