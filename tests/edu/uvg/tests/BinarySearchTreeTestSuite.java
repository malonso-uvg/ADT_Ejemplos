package edu.uvg.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.uvg.structures.BinarySearchTree;
import edu.uvg.structures.IntegerComparator;

class BinarySearchTreeTestSuite {

	@Test
	void testInsert() {
        BinarySearchTree<Integer, String> myBST = new BinarySearchTree<Integer, String>(new IntegerComparator<Integer>());
        myBST.insert(10, "test");
        assertEquals(1, myBST.count());

        assertEquals("test", myBST.getElements().get(0));
	}

}
