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
public class PriorityQueueBinaryTree<K, V> implements IPriorityQueue<K, V> {
	Comparator<K> internalComparador;
	int count;
	ArrayList<TreeNode<K, V>> siblings;
	TreeNode<K, V> root;
	
	public PriorityQueueBinaryTree(Comparator<K> _comparator) {
		internalComparador = _comparator;
		count = 0;
		siblings = new ArrayList<TreeNode<K, V>>(); 
	}

	@Override
	public void insert(K priority, V value) {
		if (isEmpty()) { //Insert in the root
			TreeNode<K, V> newNode = new TreeNode<K, V>(priority, value);
			root = newNode;
			siblings.add(root);
			count++;
		} else { //Insert when the tree is not empty
			//Search in which sibling insert the new node
			boolean slotFound = false;
			TreeNode<K, V> newNode = new TreeNode<K, V>(priority, value);
			
			for (int i = 0; i < siblings.size(); i++) {
				if (siblings.get(i).getLeft() == null) {
					siblings.get(i).setLeft(newNode);
					newNode.setParent(siblings.get(i));
					count++;
					internalSwapBottomUp(newNode);
					return;
				} else if (siblings.get(i).getRight() == null) {
					siblings.get(i).setRight(newNode);
					newNode.setParent(siblings.get(i));
					count++;
					internalSwapBottomUp(newNode);
					return;
				}
			}
			
			
			//if there is no space then create new sibling list
			if (!slotFound) {
				ArrayList<TreeNode<K, V>> newSiblings = new ArrayList<TreeNode<K, V>>();
				for (int i = 0; i < siblings.size(); i++) {
					newSiblings.add(siblings.get(i).getLeft());
					newSiblings.add(siblings.get(i).getRight());
				}
				siblings = newSiblings;
			}
			
			insert(priority, value);
		}//if that verify if the structure is empty
	}
	
	void internalSwapBottomUp(TreeNode<K, V> actual) {
		while (actual.getParent() != null) {
			int result = internalComparador.compare(actual.getId(), actual.getParent().getId());
			if (result > 0) { //Means actual priority is greater than parent priority, then swap
				K tempPriority = actual.getId();
				V tempValue = actual.getValue();
				actual.setId(actual.getParent().getId());
				actual.setValue(actual.getParent().getValue());
				actual.getParent().setId(tempPriority);
				actual.getParent().setValue(tempValue);
				
				internalSwapBottomUp(actual.getParent());
				
			} 
		}
	}

	@Override
	public V get() {
		return root.getValue();
	}

	@Override
	public V remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		return count;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}
	
	

}
