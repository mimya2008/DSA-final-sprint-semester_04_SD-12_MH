package com.keyin.binarytree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeInsertTest {

    @Test
    void inserts_build_expected_shape() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(8);
        bst.insert(4);
        bst.insert(12);

        BinaryNode root = bst.getRoot();
        assertNotNull(root);
        assertEquals(8, root.getValue());
        assertEquals(4, root.getLeft().getValue());
        assertEquals(12, root.getRight().getValue());
    }
}
