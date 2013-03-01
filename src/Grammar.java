/*************************************************************************
 *	File: Grammar.java
 *	Project: CSE340 - Project
 *	Author: Michael Curry
 *	Date: 2013.02.28
 *	Description: Grammar Parse class for project 2
 *************************************************************************/

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Grammar {

	// Init Vars
	private static final Pattern BASIC_GRAMMAR = Pattern.compile("^((NONTERMINALASSIGNMENT|TERMINALASSIGNMENT)(NONTERMINAL|TERMINAL|PIPE)+)+$");
	private static final Pattern VALID_GRAMMAR = Pattern.compile("^(NONTERMINALASSIGNMENT(NONTERMINAL|TERMINAL|PIPE)+)+$");
	public String type;
	public String message;
	public Statment[] statments = new Statment[100];
	public Token[] nonTerminals = new Token[100];

	// Gramar constructor
	public Grammar(Token[] tokens){
		// Validata Grammar before Parce
		validate(tokens);
		if (type == "ERROR") {
			// Do Not Parce Grammar
		}else {
			// Indexs set
			int statmentIndex = -1;
			int tokenIndex = 0;
			int definitionIndex = 0;
			// Create Statments from tokens array
			do {
				// If Nonterminal before ASSIGNMENT
				if (tokens[tokenIndex].type.equals("NONTERMINAL") && tokens[tokenIndex+1].type.equals("ASSIGNMENT")) {
					// Increment to next Segment Index
					statmentIndex++;
					// Init Statment
					statments[statmentIndex] = new Statment();
					// Assign NONTERMINAL
					statments[statmentIndex].nonTerminal = tokens[tokenIndex];
					// Reset Definition Index on creation of new statment
					definitionIndex = 0;
					// Push past ASSIGNMENT
					tokenIndex++;
				}
				// ElseIf token is PIPE '|'
				else if (tokens[tokenIndex].type.equals("PIPE")) {
					// Increment to next Segment Index
					statmentIndex++;
					// Init Statment
					statments[statmentIndex] = new Statment();
					// Assign NONTERMINAL
					statments[statmentIndex].nonTerminal = statments[statmentIndex-1].nonTerminal;
					// Reset Definition Index on creation of new statment
					definitionIndex = 0;
				}
				// Else is definition
				else {
					// Push Token to Statment Definition
					statments[statmentIndex].definitions[definitionIndex] = tokens[tokenIndex];
					// Increment to next Definition Index
					definitionIndex++;
				}
				// Increment to next Token Index
				tokenIndex++;
			} while (tokenIndex < tokens.length);
		}

		// NonTerminal Array Creation
		int nonTerminalIndex = 0;
		for (Statment statment : statments) {
			if (statment != null) {
				nonTerminals[nonTerminalIndex] = statment.nonTerminal;
				nonTerminalIndex++;
			}
		}
	}

	// Sets error and erroemessage if grammar is not valid
	protected void validate(Token[] tokens) {
		String tokenString = "";
		for ( Token token : tokens ) {
			tokenString += token.type;
		}
		if (!BASIC_GRAMMAR.matcher(tokenString).matches()){
			type = "ERROR";
			message = "ERROR CODE 0: Input not according to format";
			return;
		}
		if (!VALID_GRAMMAR.matcher(tokenString).matches()){
			type = "ERROR";
			message = "ERROR CODE 2: terminal symbol appearing on the lefthand side of a rule";
			return;
		}
	}

	// Print Grammar to console
	public void print() {
		for (Statment statment : statments){
			if (statment != null) {
				statment.oneline();
			}
		}
	}

	// ToString
	public String toString() {
		String grammarString = "";
		for (Statment statment : statments){
			if (statment != null) {
				grammarString += statment.toString() + "\n";
			}
		}
		return grammarString;
	}

}