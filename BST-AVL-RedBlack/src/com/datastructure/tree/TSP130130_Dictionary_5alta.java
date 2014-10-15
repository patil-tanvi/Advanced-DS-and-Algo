package com.datastructure.tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class to perform operations on Binary Search Tree, AVL Tree and Red-Black
 * Tree.
 * 
 * @author tanvi
 *
 */
public class TSP130130_Dictionary_5alta {

	private static BufferedReader br = null;

	private class Constants {
		static final String INSERT = "insert";
		static final String FIND = "find";
		static final String MIN = "min";
		static final String MAX = "max";
		static final String SIZE = "size";
		static final String DELETE = "delete";
		static final int INDEX_OF_OPERATION = 0;
		static final int INDEX_OF_KEY = 1;
		static final int INDEX_OF_VALUE = 2;
	}

	public static void main(String args[]) {

		int choice;
		String filename;
		String inputStatements[] = new String[10000000];
		int noOfInputStatements = 0;
		BufferedReader fr;
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter full path of the file from where to read the input : ");
			filename = br.readLine();
			
			fr = new BufferedReader(new FileReader(filename));
			
			String line; // = fr.readLine();
//			System.out.println(line);
			while((line = fr.readLine()) != null){
//				System.out.println(line);
				inputStatements[noOfInputStatements] = line;
				noOfInputStatements += 1;
				
			}
			
			System.out.print("Enter the data structure to test : "
					+ "\n1. Binary Search Tree" + "\n2. AVL Tree"
					+ "\n3. Red Black Tree" + "\nYour choice (1/2/3) : ");
			choice = Integer.parseInt(br.readLine());
			Tree tree = null;
			switch (choice) {
			case 1:
				tree = new BinarySearchTree();
				break;
			case 2:
				tree = new AVLTree();
				break;
			case 3:
				tree = new RedBlackTree();
				break;
			default:
				break;
			}

			String operation;
			long key;
			long value;

			long sum = 0;

//			String line = br.readLine();
			String[] lineArray;
//			lineArray = line.split("\\s+");

			long startTime = System.currentTimeMillis();

			int i = 0;
//			while (line.length() != 0) {
			while(i < noOfInputStatements){
				// System.out.println(line);
				lineArray = inputStatements[i].split("\\s+");
				operation = lineArray[Constants.INDEX_OF_OPERATION];
				operation = operation.toLowerCase();

				switch (operation) {
				case Constants.INSERT:
					key = Long.parseLong(lineArray[Constants.INDEX_OF_KEY]);
					value = Long.parseLong(lineArray[Constants.INDEX_OF_VALUE]);
					int x = tree.insert(key, value);
					sum += x;
					// System.out.println("Insert : " + x);
					break;
				case Constants.FIND:
					key = Long.parseLong(lineArray[Constants.INDEX_OF_KEY]);
					sum += tree.find(key);
					// System.out.println("Find : " + tree.find(key));
					break;
				case Constants.MAX:
					sum += tree.max();
					// System.out.println("Max : " + tree.max());
					break;
				case Constants.MIN:
					sum += tree.min();
					// System.out.println("Min : " + tree.min());
					break;
				case Constants.SIZE:
					sum += tree.size();
					// System.out.println("Size : " + tree.size());
					break;
				case Constants.DELETE:
					key = Long.parseLong(lineArray[Constants.INDEX_OF_KEY]);
					long x2 = tree.delete(key);
					sum += x2;
					// System.out.println("Delete : " + x2);
					break;
				}
				// System.out.println(sum);
				sum = sum % 997;
				i++;
//				line = br.readLine();
//				lineArray = line.split("\\s+");
			}

			long endTime = System.currentTimeMillis();
			// System.out.println(sum);
			System.out.println(sum + "  " + (endTime - startTime));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
