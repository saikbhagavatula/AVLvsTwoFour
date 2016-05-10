import com.Algos.AvlNode.AvlNode;

public class AVLFunctions {
	protected AvlNode root; // the root node
	public void insert(int k) {
		// create new node
		AvlNode n = new AvlNode(k);
		// start recursive procedure for inserting the node
		insertAVL(this.root, n);
	}
	public void insertAVL(AvlNode p, AvlNode q) {
		// If node to compare is null, the node is inserted. If the root is
		// null, it is the root of the tree.
		if (p == null) {
			this.root = q;
		} else {

			// If compare node is smaller, continue with the left node
			if (q.key < p.key) {
				if (p.left == null) {
					p.left = q;
					q.parent = p;

					// Node is inserted now, continue checking the balance
					recursiveBalance(p);
				} else {
					insertAVL(p.left, q);
				}

			} else if (q.key > p.key) {
				if (p.right == null) {
					p.right = q;
					q.parent = p;

					// Node is inserted now, continue checking the balance
					recursiveBalance(p);
				} else {
					insertAVL(p.right, q);
				}
			} else {
				// do nothing: This node already exists
			}
		}
	}
	public void recursiveBalance(AvlNode cur) {

		// we do not use the balance in this class, but the store it anyway
		setBalance(cur);
		int balance = cur.balance;

		// check the balance
		if (balance == -2) {

			if (height(cur.left.left) >= height(cur.left.right)) {
				cur = rotateRight(cur);
			} else {
				cur = doubleRotateLeftRight(cur);
			}
		} else if (balance == 2) {
			if (height(cur.right.right) >= height(cur.right.left)) {
				cur = rotateLeft(cur);
			} else {
				cur = doubleRotateRightLeft(cur);
			}
		}

		// we did not reach the root yet
		if (cur.parent != null) {
			recursiveBalance(cur.parent);
		} else {
			this.root = cur;
		}
	}
	public void remove(int k) {
		// First we must find the node, after this we can delete it.
		removeAVL(this.root, k);
	}
	public void removeAVL(AvlNode p, int q) {
		if (p == null) {
			// der Wert existiert nicht in diesem Baum, daher ist nichts zu tun
			return;
		} else {
			if (p.key > q) {
				removeAVL(p.left, q);
			} else if (p.key < q) {
				removeAVL(p.right, q);
			} else if (p.key == q) {
				// we found the node in the tree.. now lets go on!
				removeFoundNode(p);
			}
		}
	}
	public void removeFoundNode(AvlNode q) {
		AvlNode r;
		// at least one child of q, q will be removed directly
		if (q.left == null || q.right == null) {
			// the root is deleted
			if (q.parent == null) {
				this.root = null;
				q = null;
				return;
			}
			r = q;
		} else {
			// q has two children --> will be replaced by successor
			r = successor(q);
			q.key = r.key;
		}

		AvlNode p;
		if (r.left != null) {
			p = r.left;
		} else {
			p = r.right;
		}

		if (p != null) {
			p.parent = r.parent;
		}

		if (r.parent == null) {
			this.root = p;
		} else {
			if (r == r.parent.left) {
				r.parent.left = p;
			} else {
				r.parent.right = p;
			}
			// balancing must be done until the root is reached.
			recursiveBalance(r.parent);
		}
		r = null;
	}
	public AvlNode rotateLeft(AvlNode n) {

		AvlNode v = n.right;
		v.parent = n.parent;

		n.right = v.left;

		if (n.right != null) {
			n.right.parent = n;
		}

		v.left = n;
		n.parent = v;

		if (v.parent != null) {
			if (v.parent.right == n) {
				v.parent.right = v;
			} else if (v.parent.left == n) {
				v.parent.left = v;
			}
		}

		setBalance(n);
		setBalance(v);

		return v;
	}
	public AvlNode rotateRight(AvlNode n) {

		AvlNode v = n.left;
		v.parent = n.parent;

		n.left = v.right;

		if (n.left != null) {
			n.left.parent = n;
		}

		v.right = n;
		n.parent = v;

		if (v.parent != null) {
			if (v.parent.right == n) {
				v.parent.right = v;
			} else if (v.parent.left == n) {
				v.parent.left = v;
			}
		}

		setBalance(n);
		setBalance(v);

		return v;
	}
	public AvlNode doubleRotateLeftRight(AvlNode u) {
		u.left = rotateLeft(u.left);
		return rotateRight(u);
	}
	public AvlNode doubleRotateRightLeft(AvlNode u) {
		u.right = rotateRight(u.right);
		return rotateLeft(u);
	}
	public AvlNode successor(AvlNode q) {
		if (q.right != null) {
			AvlNode r = q.right;
			while (r.left != null) {
				r = r.left;
			}
			return r;
		} else {
			AvlNode p = q.parent;
			while (p != null && q == p.right) {
				q = p;
				p = q.parent;
			}
			return p;
		}
	}
	private int height(AvlNode cur) {
		if (cur == null) {
			return -1;
		}
		if (cur.left == null && cur.right == null) {
			return 0;
		} else if (cur.left == null) {
			return 1 + height(cur.right);
		} else if (cur.right == null) {
			return 1 + height(cur.left);
		} else {
			return 1 + maximum(height(cur.left), height(cur.right));
		}
	}
	private int maximum(int a, int b) {
		if (a >= b) {
			return a;
		} else {
			return b;
		}
	}
	public int height() {   
		return height(root);  
	}
	public int countNodes() {  
		return countNodes(root);  
	}

	private int countNodes(AvlNode r) {
		if (r == null)
			return 0;
		else {
			int l = 1;
			l += countNodes(r.left);
			l += countNodes(r.right);
			return l;
		}
	}

	public boolean search(int val) {
		return search(root, val);
	}
	private boolean search(AvlNode r, int val) {
		boolean found = false;
		while ((r != null) && !found) {
			int rval = r.key;
			if (val < rval)
				r = r.left;
			else if (val > rval)
				r = r.right;
			else {
				found = true;
				break;
			}
			found = search(r, val);
		}
		return found;
	}
	
	private void setBalance(AvlNode cur) {
		cur.balance = height(cur.right) - height(cur.left);
	}
}
