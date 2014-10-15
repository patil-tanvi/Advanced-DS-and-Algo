package com.linkedlist;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class TSP130130_LinkedListManipulationsDriver {

	private class Constants {
		static final String ASSIGN = "assign";
		static final String ADD = "add";
		static final String SUBSTRACTION = "sub";
		static final String MULTIPILICATION = "mult";
		static final String POWER = "power";
		static final String DISPLAY = "display";

		static final int INDEX_OF_LHS_VARIABLE = 0;
		static final int INDEX_OF_VARIABLE_1 = 2;
		static final int INDEX_OF_VARIABLE_2 = 4;
	}

	static int lineNoToExecute = 1;
	static int lineNoExpected = 1; // Line number expected from user

	static Hashtable<Integer, String> seqOfCommands = new Hashtable<Integer, String>(); // Hashtable
	// that store all the commands
	// mapped to the respective key numbers
	static Hashtable<Character, LinkedList<Integer>> varToLinkedList = new Hashtable<Character, LinkedList<Integer>>();

	// Mapping of variable to its linkedlist

	/**
	 * Function to interpret the command.
	 * 
	 * @param cmd
	 *            -> command to interpret
	 * @return Constants.<operation> Null if operation not interpreted.
	 */
	private static String parseCommand(String cmd) {

		final int INDEX_OF_BINARY_OPERATOR = 3;
		final int CMD_LENGTH = cmd.length();

		if (CMD_LENGTH == 1) {
			return Constants.DISPLAY;
		} else if (CMD_LENGTH > INDEX_OF_BINARY_OPERATOR
				&& cmd.charAt(INDEX_OF_BINARY_OPERATOR) == '+') {
			return Constants.ADD;
		} else if (CMD_LENGTH > INDEX_OF_BINARY_OPERATOR
				&& cmd.charAt(INDEX_OF_BINARY_OPERATOR) == '-') {
			return Constants.SUBSTRACTION;
		} else if (CMD_LENGTH > INDEX_OF_BINARY_OPERATOR
				&& cmd.charAt(INDEX_OF_BINARY_OPERATOR) == '*') {
			return Constants.MULTIPILICATION;
		} else if (CMD_LENGTH > INDEX_OF_BINARY_OPERATOR
				&& cmd.charAt(INDEX_OF_BINARY_OPERATOR) == '^') {
			return Constants.POWER;

		} else {
			return Constants.ASSIGN;
		}
	}

	/**
	 * Function to detect whether the command is to loop or to perform
	 * arithmetic operation
	 * 
	 * @param cmd
	 *            --> Command
	 * @return loop --> Boolean value. True of it is looping command.
	 */
	private static boolean isLoopingCommand(String cmd) {
		boolean loop = true;

		if (!cmd.contains("?")) {
			loop = false;
		}
		return loop;
	}

	/**
	 * var=NumberInDecimal # sets var to be that number. Function that takes
	 * input in the form of string and stores it in varToLinkedList.
	 * 
	 * @param command
	 *            = "var=NumberInDecimal" (Eg: "c=3243242")
	 */
	private static void assign(String command) {
		char variable = command.charAt(Constants.INDEX_OF_LHS_VARIABLE);
		String number = command.split("=")[1];
		LinkedList<Integer> linkedList = LinkedListManipulations
				.strToNum(number);
		varToLinkedList.put(variable, linkedList);
	}

	/**
	 * var # print the value of the variable to stdout
	 * 
	 * @param command
	 *            -->"var" (Eg : "a")
	 */
	private static void display(String command) {
		String number = null;

		char var = command.charAt(Constants.INDEX_OF_LHS_VARIABLE);
		LinkedList<Integer> linkedList = varToLinkedList.get(var);

		if (linkedList != null) {
			System.out.println(LinkedListManipulations.numToStr(linkedList));
		} else {
			System.out.println("Variable " + var + " not defined");
		}
	}

	/**
	 * var=var+var # add two numbers and assign to var on left
	 * 
	 * @param command
	 *            --> "var=var1+var2" (Eg. "c=a+b")
	 */
	private static void add(String command) {
		char target = command.charAt(Constants.INDEX_OF_LHS_VARIABLE);
		char var1 = command.charAt(Constants.INDEX_OF_VARIABLE_1);
		char var2 = command.charAt(Constants.INDEX_OF_VARIABLE_2);

		LinkedList<Integer> result = new LinkedList<Integer>();
		LinkedList<Integer> list1 = varToLinkedList.get(var1);
		LinkedList<Integer> list2 = varToLinkedList.get(var2);
		result = LinkedListManipulations.add(list1, list2);

		varToLinkedList.put(target, result);
	}

	/**
	 * var=var-var # difference of two numbers
	 * 
	 * @param command
	 *            --> "var=var1-var2" (Eg. "c=a-b")
	 */
	private static void sub(String command) {
		char target = command.charAt(Constants.INDEX_OF_LHS_VARIABLE);
		char var1 = command.charAt(Constants.INDEX_OF_VARIABLE_1);
		char var2 = command.charAt(Constants.INDEX_OF_VARIABLE_2);

		LinkedList<Integer> result = new LinkedList<Integer>();
		LinkedList<Integer> list1 = varToLinkedList.get(var1);
		LinkedList<Integer> list2 = varToLinkedList.get(var2);
		result = LinkedListManipulations.sub(list1, list2);

		varToLinkedList.put(target, result);
	}

	/**
	 * var=var*var # product of two numbers
	 * 
	 * @param command
	 *            --> "var=var1*var2" (Eg. "c=a*b")
	 */
	private static void multiply(String command) {
		char target = command.charAt(Constants.INDEX_OF_LHS_VARIABLE);
		char var1 = command.charAt(Constants.INDEX_OF_VARIABLE_1);
		char var2 = command.charAt(Constants.INDEX_OF_VARIABLE_2);

		LinkedList<Integer> result = new LinkedList<Integer>();
		LinkedList<Integer> list1 = varToLinkedList.get(var1);
		LinkedList<Integer> list2 = varToLinkedList.get(var2);
		result = LinkedListManipulations.multiplication(list1, list2);
		varToLinkedList.put(target, result);
	}

	/**
	 * var?LineNumber # if var value is not 0, then go to Line number
	 * 
	 * @param command
	 *            -> "var?num" (Eg. x?4) Interpreted as, if x is not 0, go to
	 *            line 4
	 * @return lineToExecute -> int representing the value of num. -1, if x is
	 *         0.
	 */
	private static int parseLoopingCommand(String command) {
		int lineToExecute = -1;

		char var = command.charAt(Constants.INDEX_OF_LHS_VARIABLE);
		LinkedList<Integer> varLinkedList = varToLinkedList.get(var);
		if (!LinkedListManipulations.isZero(varLinkedList)) {
//			lineToExecute = Character.getNumericValue(command
//					.charAt(Constants.INDEX_OF_VARIABLE_1));
			
			lineToExecute = Integer.parseInt(command.substring(Constants.INDEX_OF_VARIABLE_1));
		}
		return lineToExecute;
	}

	/**
	 * var=var^var # power
	 * 
	 * @param command
	 *            -> "var1=var2+var3" (Eg. x=a^a)
	 */
	private static void power(String command) {
		char target = command.charAt(Constants.INDEX_OF_LHS_VARIABLE);
		char var1 = command.charAt(Constants.INDEX_OF_VARIABLE_1);
		char var2 = command.charAt(Constants.INDEX_OF_VARIABLE_2);

		LinkedList<Integer> result = new LinkedList<Integer>();
		LinkedList<Integer> list1 = varToLinkedList.get(var1);
		LinkedList<Integer> list2 = varToLinkedList.get(var2);
		result = LinkedListManipulations.power(list1, list2);

		varToLinkedList.put(target, result);
	}

	public static void main(String args[]) {

		int linenum;
		String cmd;
		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
//			System.out.println(lineNoToExecute);
			linenum = in.nextInt();
			cmd = in.next();
			seqOfCommands.put(linenum, cmd);

			// do-while loop to handle the looping command
			do {

				if (lineNoToExecute > lineNoExpected) // If the looping command
														// sets the execution to
														// a forward line
					break;
				cmd = seqOfCommands.get(lineNoToExecute);

				if (isLoopingCommand(cmd)) {
					int line = parseLoopingCommand(cmd);
					if (line == -1) {
						lineNoToExecute++;
					} else {
						lineNoToExecute = line;
					}
				} else {
					String interpretedOperation = parseCommand(cmd);

					if (interpretedOperation == null) {
						interpretedOperation = "default";
					}
					switch (interpretedOperation) {
					case Constants.ASSIGN:
						assign(cmd);
						break;
					case Constants.DISPLAY:
						display(cmd);
						break;
					case Constants.ADD:
						add(cmd);
						break;
					case Constants.SUBSTRACTION:
						sub(cmd);
						break;
					case Constants.MULTIPILICATION:
						multiply(cmd);
						break;
					case Constants.POWER:
						power(cmd);
						break;
					default:
						System.out.println("Unknown operation");
					}

					lineNoToExecute++;
				}
			} while (lineNoToExecute <= lineNoExpected);

			lineNoExpected++;
		}
	}
}
