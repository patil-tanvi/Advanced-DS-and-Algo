package com.datastructure.tree;

/**
 * Defines the Binary Tree Node. A binary tree node structure has two children,
 * left and right. The data contained in a single node is key and value.
 * 
 * @author tanvi
 *
 */
class BTreeNode {
	
	long key;
	long value;

	private BTreeNode left;
	private BTreeNode right;

	BTreeNode() {
	}

	BTreeNode(Long key, Long value) {
		this.key = key;
		this.value = value;
		left = null;
		right = null;
	}

	long getKey() {
		return key;
	}

	void setKey(Long key) {
		this.key = key;
	}

	long getValue() {
		return value;
	}

	void setValue(Long value) {
		this.value = value;
	}

	BTreeNode getLeft() {
		return left;
	}

	void setLeft(BTreeNode left) {
		this.left = left;
	}

	BTreeNode getRight() {
		return right;
	}

	void setRight(BTreeNode right) {
		this.right = right;
	}
}
