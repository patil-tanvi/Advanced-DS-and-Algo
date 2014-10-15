package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implementation of the Tree interface to make it function as a binary search
 * tree.
 * 
 * @author tanvi
 *
 */
class BinarySearchTree implements Tree {
	private static BTreeNode root = null;

	BinarySearchTree() {
		root = null;
	}

	/**
	 * Get the root node of the BST.
	 * 
	 * @return root -> Type BTreeNode
	 */
	BTreeNode getRoot() {
		return root;
	}

	/**
	 * Function that tells whether the BinarySearchTree is empty
	 * 
	 * @return true, if root is null. False, otherwise.
	 */
	boolean isEmpty() {
		if (root == null) {
			return true;
		} else {
			return false;
		}
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
	public int insert(long key, long value) {
		int newElement = 1;

		if (isEmpty()) {
			root = new BTreeNode(key, value);
		} else {
			BTreeNode trav = root; // Node to traverse the tree
			BTreeNode parent = null; // Node to keep track of parent

			while (trav != null) {
				trav.getKey();
				// If current Node's value is greater than the key, traverse
				// left
				if (trav.getKey() > key) {
					parent = trav;
					trav = trav.getLeft();
				}
				// If current Node's value is less than the key, traverse right
				else if (trav.getKey() < key) {
					parent = trav;
					trav = trav.getRight();
				}
				// If current Node's value is equal to the key, update value
				else {
					newElement = 0;
					trav.setValue(value);
					break;
				}
			}

			// Add the new node in the BST.
			if (newElement != 0) {
				BTreeNode newNode = new BTreeNode(key, value);
				// if parent's key is less than the new key, make the new node
				// the right child
				if (parent.getKey() < key) {
					parent.setRight(newNode);
				}
				// if parent's key is greater than the new key, make the new
				// node the left child
				else {
					parent.setLeft(newNode);
				}
			}
		}

		return newElement;
	}

	// void print(BTreeNode node) {
	//
	// if (node.getLeft() != null) {
	// System.out.println("here");
	// print(node.getLeft());
	// }
	// if (node != null) {
	// System.out.println("root : " + node.getKey());
	// // System.out.println(root.getLeft().getKey());
	// // System.out.println(root.getRight().getKey());
	// }
	// if (node.getRight() != null) {
	// print(node.getRight());
	// }
	// }

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
			BTreeNode trav = root;
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
			BTreeNode trav = root;
			// Traverse to right-most node of the tree.
			while (trav.getRight() != null) {
				trav = trav.getRight();
			}
			maxKey = trav.getKey();
		}
		return maxKey;
	}

	/**
	 * remove element with key k. Returns value of deleted element (0 if such a
	 * key does not exist).
	 * 
	 * @param key
	 *            -> Type long. Key to be deleted
	 * @return value-> Type long. Value of deleted key.
	 */
	@Override
	public long delete(long key) {
		long value = 0;

		// Tree is empty
		if (root == null) {
			return value;
		} else { // Get the value for the key.
			value = find(key);

			root = delete(root, key);
		}
		return value;
	}

	/**
	 * Function that recursively deletes a specific node.
	 * 
	 * @param root
	 *            -> root of current sub-tree under consideration.
	 * @param key
	 *            -> The key of the node to be deleted.
	 * @return root.
	 */
	private BTreeNode delete(BTreeNode root, long key) {

		BTreeNode p, p2, n;
		if (root.getKey() == key) {
			BTreeNode left, right;
			left = root.getLeft();
			right = root.getRight();

			if (left == null && right == null) {
				return null;
			} else if (left == null) {
				p = right;
				return p;
			} else if (right == null) {
				p = left;
				return p;
			} else { // If the node to delete has left and right child
						// Traverse to minimum value node in right sub-tree, and
						// set the min node's left child to the left sub-tree of
						// node to
						// delete
				p2 = right;
				p = right;
				while (p.getLeft() != null)
					p = p.getLeft();
				p.setLeft(left);
				return p2;
			}
		}
		if (key < root.getKey()) {
			n = delete(root.getLeft(), key);
			root.setLeft(n);
		} else {
			n = delete(root.getRight(), key);
			root.setRight(n);
		}

		return root;
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

		Queue<BTreeNode> queue = new LinkedList<BTreeNode>();

		if (isEmpty()) {
			return size;
		} else {
			queue.add(root);

			while (!queue.isEmpty()) {
				BTreeNode temp = queue.remove();
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
			BTreeNode trav = root; // Node to traverse the tree

			while (trav != null) {

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

}
