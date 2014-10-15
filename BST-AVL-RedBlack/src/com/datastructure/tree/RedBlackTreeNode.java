package com.datastructure.tree;

public class RedBlackTreeNode {
	
        private long key;           // key
        private long value;         // associated data
        private RedBlackTreeNode left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int n;             // subtree count

        public RedBlackTreeNode(long key, long value, boolean color, int n) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.n = n;
            left = null;
            right = null;
        }

		long getKey() {
			return key;
		}

		void setKey(long key) {
			this.key = key;
		}

		long getValue() {
			return value;
		}

		void setValue(long value) {
			this.value = value;
		}

		RedBlackTreeNode getLeft() {
			return left;
		}

		void setLeft(RedBlackTreeNode left) {
			this.left = left;
		}

		RedBlackTreeNode getRight() {
			return right;
		}

		void setRight(RedBlackTreeNode right) {
			this.right = right;
		}

		boolean isColor() {
			return color;
		}

		void setColor(boolean color) {
			this.color = color;
		}

		int getN() {
			return n;
		}

		void setN(int n) {
			this.n = n;
		}
    
}
