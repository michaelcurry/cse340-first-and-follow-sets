#!/bin/bash
javac -d ./bin ./src/Token.java
javac -d ./bin -classpath ./bin ./src/Statment.java
javac -d ./bin -classpath ./bin ./src/Grammar.java
javac -d ./bin -classpath ./bin ./src/Main.java