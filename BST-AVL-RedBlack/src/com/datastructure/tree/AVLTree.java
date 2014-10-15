package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree implements Tree {

	private AVLTreeNode root;
	int newElement = 1; // To keep track whether the key being inserted
						// in the AVL Tree already existed or is a new
						// key.
	long valueOfDeletedKey = 0;
	long keyToBeDeleted;

	/**
	 * Constructor that instantiates a new AVL Tree and sets its root to null
	 */
	AVLTree() {
		root = null;
	}

	/**
	 * Function that tells whether the BinarySearchTree is empty
	 * 
	 * @return true, if root is null. False, otherwise.
	 */
	boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}

	/**
	 * Function that returns the height of a node
	 * 
	 * @param node
	 *            -> Type AVLTreeNode. Node whose height is needed
	 * @return node.height. -1, if node is null.
	 */
	private int height(AVLTreeNode node) {
		return node == null ? -1 : node.getHeight();
	}

	/**
	 * insert a new entry with key k, value v. If a key with key k already
	 * exists, its value is replaced by v. Insert returns 1 if key k is new, and
	 * 0 if it already existed in the dictionary.
	 * 
	 * @param key
	 *            -> Type Long
	 * @param value
	 *            -> Type Long
	 * @return found -> Type int. Tells whether the key to be added already
	 *         existed or not.
	 */
	@Override
	public int insert(long key, long value) {
		newElement = 1;
		root = insert(key, value, root);
		return newElement;
	}

	/**
	 * Funnction to recursively insert key, value in the tree
	 * 
	 * @param key
	 *            -> Type Long. Key to be inserted.
	 * @param value
	 *            -> Type Long. Value of the key.
	 * @param t
	 *            -> Node under consideration
	 * @return
	 */
	private AVLTreeNode insert(long key, long value, AVLTreeNode t) {
		if (t == null)
			t = new AVLTreeNode(key, value);
		else if (key < t.getKey()) {
			t.setLeft(insert(key, value, t.getLeft()));
			if (height(t.getLeft()) - height(t.getRight()) == 2)
				if (key < t.getLeft().getKey())
					t = rotateWithLeftChild(t);
				else
					t = doubleWithLeftChild(t);
		} else if (key > t.getKey()) {
			t.setRight(insert(key, value, t.getRight()));
			if (height(t.getRight()) - height(t.getLeft()) == 2)
				if (key > t.getRight().getKey())
					t = rotateWithRightChild(t);
				else
					t = doubleWithRightChild(t);
		} else {
			newElement = 0;
			t.setValue(value);
		}// Duplicate; do nothing
			// t.height = max( height( t.left ), height( t.right ) ) + 1;
		t.setHeight(Math.max(height(t.getLeft()), height(t.getRight())) + 1);
		return t;
	}

	private AVLTreeNode doubleWithRightChild(AVLTreeNode k1) {
		k1.setRight(rotateWithLeftChild(k1.getRight()));
		return rotateWithRightChild(k1);
	}

	private AVLTreeNode rotateWithRightChild(AVLTreeNode k1) {
		AVLTreeNode k2 = k1.getRight();
		// if (k2 != null) {

		k1.setRight(k2.getLeft());
		k2.setLeft(k1);
		k1.setHeight(Math.max(height(k1.getLeft()), height(k1.getRight())) + 1);
		k2.setHeight(Math.max(height(k2.getRight()), k1.getHeight()) + 1);
		// }
		return k2;
	}

	private AVLTreeNode doubleWithLeftChild(AVLTreeNode k3) {
		k3.setLeft(rotateWithRightChild(k3.getLeft()));
		return rotateWithLeftChild(k3);
	}

	private AVLTreeNode rotateWithLeftChild(AVLTreeNode k2) {
		AVLTreeNode k1 = k2.getLeft();
		// if (k1 != null) {
		k2.setLeft(k1.getRight());
		k1.setRight(k2);
		k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);
		k1.setHeight(Math.max(height(k1.getLeft()), k2.getHeight()) + 1);
		// }
		return k1;
	}

	/**
	 * return the value associated with key k. If there is no element with key
	 * k, it returns 0.
	 * 
	 * @param key
	 *            -> Type long
	 * @return value -> Type long (0, by default)
	 */
	@Override
	public long find(long key) {
		long value = 0;

		if (isEmpty()) {
			return value;
		} else {
			AVLTreeNode trav = root; // Node to traverse the tree

			while (trav != null) {
				trav.getKey();
				// If current Node's value is greater than the key, traverse
				// left
				if (trav.getKey() > key) {
					trav = trav.getLeft();
				}
				// If current Node's value is less than the key, traverse right
				else if (trav.getKey() < key) {
					trav = trav.getRight();
				}
				// If current Node's value is equal to the key, update value
				else {
					value = trav.getValue();
					break;
				}
			}
		}

		return value;
	}

	@Override
	public long delete(long key) {
		valueOfDeletedKey = 0;
		keyToBeDeleted = key;
	
		root = delete(root, key);

		return valueOfDeletedKey;
	}

	private AVLTreeNode delete(AVLTreeNode x, long key) {
		if (x == null) {
			valueOfDeletedKey = 0;
			return null;
		}
		if (key < x.getKey()) {
			x.setLeft(delete(x.getLeft(), key));
			if (height(x.getLeft()) - height(x.getRight()) == 2)
				if (key < x.getLeft().getKey())
					x = rotateWithLeftChild(x);
				else
					x = doubleWithLeftChild(x);
		} else if (key > x.getKey()) {
			x.setRight(delete(x.getRight(), key));
			if (height(x.getRight()) - height(x.getLeft()) == 2)
				if (key > x.getRight().getKey())
					x = rotateWithRightChild(x);
				else
					x = doubleWithRightChild(x);
		} else {
			long tmp = x.getValue();

			if (x.getLeft() != null && x.getRight() != null) {
				AVLTreeNode t = x;
				x = getMin(t.getRight());
				x.setRight(deleteMin(t.getRight()));
				x.setLeft(t.getLeft());
			} else {
				x = (x.getLeft() != null) ? x.getLeft() : x.getRight();
			}
			valueOfDeletedKey = tmp;			
		}

		if (x != null)
			x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
		return x;
	}

	private AVLTreeNode deleteMin(AVLTreeNode root) {

		AVLTreeNode parent = null, trav = root;

		while (trav.getLeft() != null) {
			parent = trav;
			trav = trav.getLeft();
		}

		if (trav.getRight() != null) {
			parent.setLeft(trav.getRight());
		} else {
			parent.setLeft(null);
		}

		return root;
	}

	/**
	 * return the current smallest key
	 * 
	 * @return minKey -> Type long. Zero if tree is empty.
	 */
	@Override
	public long min() {
		long minKey = 0;

		if (isEmpty()) {
			return minKey;
		} else {
			AVLTreeNode trav = root;
			// Traverse to left-most node of the tree.
			while (trav.getLeft() != null) {
				trav = trav.getLeft();
			}
			minKey = trav.getKey();
		}
		return minKey;

	}

	/**
	 * return the current largest key
	 * 
	 * @return maxKey -> Type long. Zero if tree is empty.
	 */
	@Override
	public long max() {
		long maxKey = 0;

		if (isEmpty()) {
			return maxKey;
		} else {
			AVLTreeNode trav = root;
			// Traverse to right-most node of the tree.
			while (trav.getRight() != null) {
				trav = trav.getRight();
			}
			maxKey = trav.getKey();
		}
		return maxKey;

	}

	/**
	 * return the number of elements currently stored.
	 * 
	 * @return size -> Type long. Stores the count of nodes.
	 */
	@Override
	public long size() {
		long size = 0;

		// Traverse the Binary Search Tree in BFS and keep a count of the number
		// of nodes.

		Queue<AVLTreeNode> queue = new LinkedList<AVLTreeNode>();

		if (isEmpty()) {
			return size;
		} else {
			queue.add(root);

			while (!queue.isEmpty()) {
				AVLTreeNode temp = queue.remove();
				size += 1;
				if (temp.getLeft() != null) {
					queue.add(temp.getLeft());
				}
				if (temp.getRight() != null) {
					queue.add(temp.getRight());
				}
			}
		}
		return size;
	}

	private AVLTreeNode getMax(AVLTreeNode r) {
		AVLTreeNode trav = r;

		while (trav.getRight() != null) {
			trav = trav.getRight();
		}

		return trav;
	}

	private AVLTreeNode getMin(AVLTreeNode r) {
		AVLTreeNode trav = r;

		while (trav.getLeft() != null) {
			trav = trav.getLeft();
		}

		return trav;
	}

}
