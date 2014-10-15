package com.linkedlist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListManipulations {

	/**
	 * Takes a string as parameter, that stores a number in decimal, and returns
	 * the list corresponding that number. The string can have arbitrary length.
	 * 
	 * @param number
	 *            --> Number in String ("6347")
	 * @return linkedList --> Linked List representation of number (7->4->3->6)
	 */
	static LinkedList<Integer> strToNum(String number) {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		int length = number.length();
		for (int i = length - 1; i >= 0; i--) {
			linkedList.add(Character.getNumericValue(number.charAt(i)));
		}
		return linkedList;
	}

	/**
	 * Takes a linked list as parameter and returns the number that it
	 * represents as a string (in decimal). There should be no leading zeroes in
	 * the string.
	 * 
	 * @param linkedList
	 *            --> LinkedList to print
	 * @return number --> LinkedList in string format
	 */
	static String numToStr(LinkedList<Integer> linkedList) {
		StringBuilder str = new StringBuilder();
		String number;
		ListIterator<Integer> iterator = linkedList.listIterator();

		while (iterator.hasNext()) {
			str.append(iterator.next());
		}

		number = new String(str.reverse());
		return number;
	}

	/**
	 * given two lists L1 and L2 as parameter, representing the numbers n1 and
	 * n2 repectively, returns the list corresponding to n1-n2. If the result is
	 * negative, store 0, instead.
	 * 
	 * @param list1
	 *            --> LinkedList representing list1
	 * @param list2
	 *            --> LinkedList representing list2
	 * @return result --> LinkedList representing the result.
	 */
	static LinkedList<Integer> sub(LinkedList<Integer> list1,
			LinkedList<Integer> list2) {
		LinkedList<Integer> result = new LinkedList<Integer>();

		ListIterator<Integer> list1Iterator = list1.listIterator();
		ListIterator<Integer> list2Iterator = list2.listIterator();

		int carry = 0;

		while (list1Iterator.hasNext() && list2Iterator.hasNext()) {
			int var1 = list1Iterator.next(); // var1 = var1 - carry

			boolean setCarry = false;
			if (var1 == 0 && carry != 0) {
				var1 = 9;
				setCarry = true;
			} else {
				var1 = var1 - carry;
			}
			int var2 = list2Iterator.next();

			int diff = 0;

			if (var1 < var2) {
				if (list1Iterator.hasNext()) {
					var1 = var1 + 10;
					carry = 1;
					diff = var1 - var2;
				} else {
					result = new LinkedList<Integer>();
					result.add(0);
					return result;
				}
			} else {
				carry = 0;
				diff = var1 - var2;
			}
			if (setCarry)
				carry = 1;
			// System.out.println(diff);
			result.add(diff);
		}

		// Add the remaining elements of list1, if any.
		while (list1Iterator.hasNext()) {
			int var1 = list1Iterator.next();
			boolean setCarry = false;
			if (var1 == 0 && carry != 0) {
				var1 = 9;
				setCarry = true;
			} else {
				var1 = var1 - carry;
				carry = 0;
			}
			if (setCarry) {
				carry = 1;
			}
			result.add(var1);
		}

		// List2 is greater than list1 (Negative difference)
		while (list2Iterator.hasNext()) {
			result = new LinkedList<Integer>();
			result.add(0);
			return result;
		}

		if (result.size() > 1) {
			// Remove trailing 0s. (Eg : 008 -> 8)
			Iterator<Integer> resultdescendingIterator = result
					.descendingIterator();

			while (resultdescendingIterator.hasNext()) {
				if (resultdescendingIterator.next() == 0)
					resultdescendingIterator.remove();
				else
					break;
			}
		}

		return result;
	}

	/**
	 * Takes two lists as parameters and returns the list corresponding to the
	 * sum of the two numbers represented by those lists.
	 * 
	 * @param list1
	 *            -> LinkedList<Integer> representing list1
	 * @param list2
	 *            -> LinkedList<Integer> representing list2
	 * @return result -> LinkedList<Integer> which stores the summation of the
	 *         two lists
	 */
	static LinkedList<Integer> add(LinkedList<Integer> list1,
			LinkedList<Integer> list2) {
		LinkedList<Integer> result = new LinkedList<Integer>();

		ListIterator<Integer> list1Iterator = list1.listIterator();
		ListIterator<Integer> list2Iterator = list2.listIterator();

		int carry = 0;

		while (list1Iterator.hasNext() && list2Iterator.hasNext()) {
			int sum = list1Iterator.next() + list2Iterator.next() + carry;
			if (sum > 9) {
				carry = 1;
				sum = sum % 10;
			} else {
				carry = 0;
			}
			result.add(sum);
		}

		// Add the remaing elements of list 1, if any.
		while (list1Iterator.hasNext()) {
			int sum = list1Iterator.next() + carry;
			if (sum > 9) {
				carry = 1;
				sum = sum % 10;
			} else {
				carry = 0;
			}
			result.add(sum);
		}

		// Add the remaing elements of list 1, if any.
		while (list2Iterator.hasNext()) {
			int sum = list2Iterator.next() + carry;
			if (sum > 9) {
				carry = 1;
				sum = sum % 10;
			} else {
				carry = 0;
			}
			result.add(sum);
		}

		if (carry == 1)
			result.add(1);

		return result;

	}

	/**
	 * Helper function which tells whether the LinkedList passed in the
	 * parameter equals 0 or not.
	 * 
	 * @param list
	 *            -> LinkedList<Integer> to check
	 * @return isZero -> Boolean value which tells whether the given list
	 *         represents zero or not.
	 */
	static boolean isZero(LinkedList<Integer> list) {
		boolean isZero = true;

		ListIterator<Integer> listIterator = list.listIterator();

		while (listIterator.hasNext()) {
			int num = listIterator.next();
			if (num != 0) {
				return false;
			}
		}

		return isZero;
	}

	/**
	 * Helper function used in mutliplication of two lists. Multiplies a list
	 * with a number and adds the required trailing zeroes.
	 * 
	 * @param list
	 *            -> LinkedList<Integer> , list tp be multiplied
	 * @param num
	 *            -> Number from list2
	 * @param trailingZeroes
	 *            -> Number of zeroes to be added at the end of the product
	 * @return prod -> LinkedList<Integer>, stored the prod of num * list
	 */
	static LinkedList<Integer> multiplyListWithSingleDigit(
			LinkedList<Integer> list, int num, int trailingZeroes) {
		LinkedList<Integer> prod = new LinkedList<Integer>();

		ListIterator<Integer> listIterator = list.listIterator();
		int carry = 0;

		while (listIterator.hasNext()) {
			int listVal = listIterator.next();
			int p = listVal * num;
			p = p + carry;
			if (p > 9) {
				carry = p / 10;
				p = p % 10;
			} else {
				carry = 0;
			}
			prod.add(p);
		}

		if (carry != 0) {
			prod.add(carry);
		}

		// Add trailing zeroes
		while (trailingZeroes != 0) {
			prod.addFirst(0);
			trailingZeroes--;
		}

		return prod;
	}

	/**
	 * implements product of two numbers, given as lists.
	 *
	 * @param list1
	 *            -> LinkedList<Integer> representing list1
	 * @param list2
	 *            -> LinkedList<Integer> representing list2
	 * @return result -> -> LinkedList<Integer> which stores the product.
	 *         Product is calculated as summation of lists.
	 */
	static LinkedList<Integer> multiplication(LinkedList<Integer> list1,
			LinkedList<Integer> list2) {
		LinkedList<Integer> result = new LinkedList<Integer>();

		// list1 should be bigger

		if (list1.size() < list2.size()) {
			LinkedList<Integer> temp = list1;
			list1 = list2;
			list2 = temp;
		}

		ListIterator<Integer> list2Iterator = list2.listIterator();
		int trailingZeroes = 0;

		result.add(0);

		while (list2Iterator.hasNext()) {
			int num = list2Iterator.next();

			LinkedList<Integer> prod = multiplyListWithSingleDigit(list1, num,
					trailingZeroes++);
			result = add(result, prod);
		}

		return result;
	}

	/**
	 * given two lists L1 and L2 as parameter, representing the numbers n1 and
	 * n2 repectively, returns the list corresponding to n1^n2 (n1 to the power
	 * n2).
	 * 
	 * @param list1
	 *            -> LinkedList<Integer> representing list1
	 * @param list2
	 *            -> LinkedList<Integer> representing list2
	 * @return result -> LinkedList<Integer> stored list1^list2. The power
	 *         function uses the multiplication function to calculate the
	 *         result.
	 */
	static LinkedList<Integer> power(LinkedList<Integer> list1,
			LinkedList<Integer> list2) {
		LinkedList<Integer> result = new LinkedList<Integer>();
		LinkedList<Integer> one = new LinkedList<Integer>();
		one.add(1);

		if (isZero(list2)) {
			result.add(1);
			return result;
		}
		result = list1;
		list2 = sub(list2, one);

		while (!isZero(list2)) {

			result = multiplication(result, list1);
			list2 = sub(list2, one);
		}

		return result;
	}

}
