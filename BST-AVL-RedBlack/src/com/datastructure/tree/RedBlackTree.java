package com.datastructure.tree;

class RedBlackTree implements Tree {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private RedBlackTreeNode root;

	private int newElement = 1;
	long valueOfDeletedKey = 0;
	long keyToBeDeleted;

	RedBlackTree() {
		root = null;
	}

	@Override
	public int insert(long key, long value) {
		newElement = 1;
		root = put(root, key, value);
		root.setColor(BLACK);
		return newElement;
	}

	private boolean isRed(RedBlackTreeNode x) {
		if (x == null)
			return false;
		return (x.isColor() == RED);
	}

	private RedBlackTreeNode rotateLeft(RedBlackTreeNode h) {
		// assert (h != null) && isRed(h.right);
		RedBlackTreeNode x = h.getRight();
		h.setRight(x.getLeft());
		x.setLeft(h);
		x.setColor(x.getLeft().isColor());
		x.getLeft().setColor(RED);
		x.setN(h.getN());
		h.setN(size(h.getLeft()) + size(h.getRight()) + 1);
		return x;
	}

	private RedBlackTreeNode rotateRight(RedBlackTreeNode h) {
		// assert (h != null) && isRed(h.left);
		RedBlackTreeNode x = h.getLeft();
		h.setLeft(x.getRight());
		x.setRight(h);
		x.setColor(x.getRight().isColor());
		x.getRight().setColor(RED);
		x.setN(h.getN());
		h.setN(size(h.getLeft()) + size(h.getRight()) + 1);
		return x;
	}

	private void flipColors(RedBlackTreeNode h) {
		// h must have opposite color of its two children
		// assert (h != null) && (h.left != null) && (h.right != null);
		// assert (!isRed(h) && isRed(h.left) && isRed(h.right))
		// || (isRed(h) && !isRed(h.left) && !isRed(h.right));
		h.setColor(!h.isColor());
		h.getLeft().setColor(!h.getLeft().isColor());
		h.getRight().setColor(!h.getRight().isColor());
	}

	private int size(RedBlackTreeNode x) {
		if (x == null)
			return 0;
		return x.getN();
	}

	private RedBlackTreeNode put(RedBlackTreeNode h, long key, long val) {
		if (h == null)
			return new RedBlackTreeNode(key, val, RED, 1);

		// int cmp = key.compareTo(h.key);
		if (key < h.getKey()) {
			h.setLeft(put(h.getLeft(), key, val));
		} else if (key > h.getKey()) {
			h.setRight(put(h.getRight(), key, val));
		} else {
			newElement = 0;
			h.setValue(val);
		}

		// fix-up any right-leaning links
		if (isRed(h.getRight()) && !isRed(h.getLeft())) {
			h = rotateLeft(h);
		}
		if (isRed(h.getLeft()) && isRed(h.getLeft().getLeft())) {
			h = rotateRight(h);
		}
		if (isRed(h.getLeft()) && isRed(h.getRight())) {
			flipColors(h);
		}
		h.setN(size(h.getLeft()) + size(h.getRight()) + 1);

		return h;
	}

	@Override
	public long find(long key) {
		long value = 0;

		if (root == null) {
			return value;
		} else {
			RedBlackTreeNode trav = root; // Node to traverse the tree

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

	@Override
	public long min() {
		return min(root).getKey();
	}

	@Override
	public long max() {
		long maxKey = 0;

		if (root == null) {
			return maxKey;
		} else {
			RedBlackTreeNode trav = root;
			// Traverse to right-most node of the tree.
			while (trav.getRight() != null) {
				trav = trav.getRight();
			}
			maxKey = trav.getKey();
		}
		return maxKey;

	}

	@Override
	public long delete(long key) {
		// delete the key-value pair with the given key

		keyToBeDeleted = key;
		if (find(key) == 0) {
			return 0;
		}

		// if both children of root are black, set root to red
		if (!isRed(root.getLeft()) && !isRed(root.getRight()))
			root.setColor(RED);

		root = delete(root, key);
		if (root != null) {
			root.setColor(BLACK);
		}
		// assert check();

		return valueOfDeletedKey;
	}

	// Assuming that h is red and both h.left and h.left.left
	// are black, make h.left or one of its children red.
	private RedBlackTreeNode moveRedLeft(RedBlackTreeNode h) {
		// assert (h != null);
		// assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

		flipColors(h);
		if (isRed(h.getRight().getLeft())) {
			h.setRight(rotateRight(h.getRight()));
			h = rotateLeft(h);
		}
		return h;
	}

	// Assuming that h is red and both h.right and h.right.left
	// are black, make h.right or one of its children red.
	private RedBlackTreeNode moveRedRight(RedBlackTreeNode h) {
		// assert (h != null);
		// assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
		flipColors(h);
		if (isRed(h.getLeft().getLeft())) {
			h = rotateRight(h);
		}
		return h;
	}

	// the smallest key in subtree rooted at x; null if no such key
	private RedBlackTreeNode min(RedBlackTreeNode x) {
		// assert x != null;
		if (x.getLeft() == null) {
			return x;
		} else {
			return min(x.getLeft());
		}
	}

	// delete the key-value pair with the minimum key
	public void deleteMin() {
		// if (isEmpty()) throw new NoSuchElementException("BST underflow");

		// if both children of root are black, set root to red
		if (!isRed(root.getLeft()) && !isRed(root.getRight()))
			root.setColor(RED);

		root = deleteMin(root);
		if (root != null) {
			root.setColor(BLACK);
		}
		// assert check();
	}

	// delete the key-value pair with the minimum key rooted at h
	private RedBlackTreeNode deleteMin(RedBlackTreeNode h) {
		if (h.getLeft() == null) {
			return null;
		}

		if (!isRed(h.getLeft()) && !isRed(h.getLeft().getLeft())) {
			h = moveRedLeft(h);
		}

		h.setLeft(deleteMin(h.getLeft()));
		return balance(h);
	}

	// restore red-black tree invariant
	private RedBlackTreeNode balance(RedBlackTreeNode h) {
		// assert (h != null);

		if (isRed(h.getRight())) {
			h = rotateLeft(h);
		}
		if (isRed(h.getLeft()) && isRed(h.getLeft().getLeft())) {
			h = rotateRight(h);
		}
		if (isRed(h.getLeft()) && isRed(h.getRight())) {
			flipColors(h);
		}

		h.setN(size(h.getLeft()) + size(h.getRight()) + 1);
		return h;
	}

	// delete the key-value pair with the given key rooted at h
	private RedBlackTreeNode delete(RedBlackTreeNode h, long key) {
		// assert contains(h, key);

		if (key < h.getKey()) {
			if (!isRed(h.getLeft()) && !isRed(h.getLeft().getLeft()))
				h = moveRedLeft(h);
			h.setLeft(delete(h.getLeft(), key));
		} else {
			if (isRed(h.getLeft()))
				h = rotateRight(h);
			if ((key == h.getKey()) && (h.getRight() == null)) {
				if (keyToBeDeleted == key) {
					valueOfDeletedKey = h.getValue();
				}
				return null;
			}
			if (!isRed(h.getRight()) && !isRed(h.getRight().getLeft())) {
				h = moveRedRight(h);
			}
			if (key == h.getKey()) {
				if (keyToBeDeleted == key) {
					valueOfDeletedKey = h.getValue();
				}
				RedBlackTreeNode x = min(h.getRight());
				h.setKey(x.getKey());
				h.setValue(x.getValue());
				// h.val = get(h.right, min(h.right).key);
				// h.key = min(h.right).key;
				h.setRight(deleteMin(h.getRight()));
			} else
				h.setRight(delete(h.getRight(), key));
		}
		return balance(h);
	}

	@Override
	public long size() {
		return (size(root));
	}
}
