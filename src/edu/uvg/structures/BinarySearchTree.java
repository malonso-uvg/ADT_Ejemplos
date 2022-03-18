/**
 * 
 */
package edu.uvg.structures;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author MAAG
 *
 */
public class BinarySearchTree<K, V> implements IBinarySearchTree<K, V> {
	
	private int count;
	private TreeNode<K, V> root;
	private Comparator<K> keyComparator;
	
	public BinarySearchTree(Comparator<K> _keyComparator) {
		this.keyComparator = _keyComparator;
		root = null;
		count = 0;
	}
	

	@Override
	public void insert(K id, V value) {
		
		if (isEmpty()) {
			root = new TreeNode<K, V>(id, value);
			count++;
		} else {
			internalInsert(root, id, value);
		}
	}

	@Override
	public V delete(K id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V find(K id) {
		return internalFind(root, id);
	}

	@Override
	public int count() {
		return count;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public ArrayList<V> getElements() {
		ArrayList<V> list = new ArrayList<V>();
		
		internalGetElements(list, root);
		
		return list;
	}

	@Override
	public void inOrder(ITreeTraversal<V> traversal) {
		internalInOrder(root, traversal);
	}

	@Override
	public void preOrder(ITreeTraversal<V> traversal) {
		internalPreOrder(root, traversal);
		
	}

	@Override
	public void postOrder(ITreeTraversal<V> traversal) {
		internalPostOrder(root, traversal);
	}
	

	private void internalInsert(TreeNode<K, V> actual, K id, V value) {
		
		int result = keyComparator.compare(actual.getId(), id);
		
		if (result > 0) { //actual id is greater than new id then search in the left side
			
			if (actual.getLeft() == null) { //Doesn't have left children
				TreeNode<K, V> newNode = new TreeNode<K, V>(id, value);
				actual.setLeft(newNode);
				newNode.setParent(actual);
				count++;
			} else {
				internalInsert(actual.getLeft(), id, value);
			}
			
		} else if (result < 0) { //actual id is smaller than the new then search in the right
			if (actual.getRight() == null) { //Doesn't have left children
				TreeNode<K, V> newNode = new TreeNode<K, V>(id, value);
				actual.setRight(newNode);
				newNode.setParent(actual);
				count++;
			} else {
				internalInsert(actual.getRight(), id, value);
			}
		}
		
	}
	
	private void internalInOrder(TreeNode<K, V> actual, ITreeTraversal<V> traversal) {
		if (actual != null) {
			internalInOrder(actual.getLeft(), traversal);
			
			traversal.Walk(actual.getValue());
			
			internalInOrder(actual.getRight(), traversal);
		}
	}
	
	private void internalPreOrder(TreeNode<K, V> actual, ITreeTraversal<V> traversal) {
		if (actual != null) {
			traversal.Walk(actual.getValue());
			
			internalPreOrder(actual.getLeft(), traversal);
			
			internalPreOrder(actual.getRight(), traversal);
		}
	}
	
	private void internalPostOrder(TreeNode<K, V> actual, ITreeTraversal<V> traversal) {
		if (actual != null) {
		
			internalPostOrder(actual.getLeft(), traversal);
			
			internalPostOrder(actual.getRight(), traversal);
			
			traversal.Walk(actual.getValue());
		}
	}
	
	private V internalFind(TreeNode<K, V> actual, K id) {
		if (actual != null) {
			int result = keyComparator.compare(actual.getId(), id);
			
			if (result > 0) {
				return internalFind(actual.getLeft(), id);
			} else if (result < 0) {
				return internalFind(actual.getRight(), id);
			} else {
				return actual.getValue();
			}
			
		} else {
			return null;
		}
	}
	
	private void internalGetElements(ArrayList<V> list, TreeNode<K, V> actual) {
		if (actual != null) {
			internalGetElements(list, actual.getLeft());
			
			list.add(actual.getValue());
			
			internalGetElements(list, actual.getRight());
		}
	}

}
