#!/bin/bash
javac -verbose -d ./bin ./src/Token.java
javac -verbose -d ./bin -classpath ./bin ./src/Statment.java
javac -verbose -d ./bin -classpath ./bin ./src/Grammar.java
javac -verbose -d ./bin -classpath ./bin ./src/Main.java