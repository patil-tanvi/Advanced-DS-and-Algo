package com.datastructure.tree;

/**
* Defines the AVL Tree Node. An AVL tree node structure has two children,
* left and right. The data contained in a single node is key, value and the height of that node..
* 
* @author tanvi
*
*/
class AVLTreeNode{
	
	private long key, value;
	private AVLTreeNode left, right;
	private int height;

    AVLTreeNode(){
    }

    AVLTreeNode(long key, long value){
    	left = null;
        right = null;
        this.key = key;
        this.value = value;
        height = 0;
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

	AVLTreeNode getLeft() {
		return left;
	}

	void setLeft(AVLTreeNode left) {
		this.left = left;
	}

	AVLTreeNode getRight() {
		return right;
	}

	void setRight(AVLTreeNode right) {
		this.right = right;
	}

	int getHeight() {
		return height;
	}

	void setHeight(int height) {
		this.height = height;
	}  
}
