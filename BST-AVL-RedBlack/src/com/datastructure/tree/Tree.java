package com.datastructure.tree;

/**
 * Interface that defines the behavior of a Tree data structure.
 * @author tanvi
 *
 */
interface Tree {
	/**
	 * insert a new entry with key k, value v. If a key with key k already
	 * exists, its value is replaced by v. Insert returns 1 if key k is new, and
	 * 0 if it already existed in the dictionary.
	 * 
	 * @param key -> Type Long
	 * @param value -> Type Long
	 * @return found -> int (0/1)
	 */
	int insert(long key, long value);

	/**
	 * return the value associated with key k.  If there is
               no element with key k, it returns 0.
	 * @param key -> Type long
	 * @return value -> Type long (0, by default)
	 */
	long find(long key);

	/**
	 * return the current smallest key
	 * @return minKey -> Type long. Zero if tree is empty.
	 */
	long min();

	/**
	 * return the current largest key
	 * @return maxKey -> Type long. Zero if tree is empty.
	 */
	long max();

	/**
	 * remove element with key k.  Returns value of deleted
                 element (0 if such a key does not exist).
	 * @param key -> Type long. Key to be deleted
	 * @return value-> Type long. Value of deleted key.
	 */
	long delete(long key);

	/**
	 * return the number of elements currently stored.
	 * @return size -> Type long. Stores the count of nodes.
	 */
	long size();
}
