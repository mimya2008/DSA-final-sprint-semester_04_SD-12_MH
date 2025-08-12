package com.keyin.binarytree;

public class BinarySearchTree {
    private BinaryNode root;

    public void insert(int value) {
        root = insert(root, value);
    }

    private BinaryNode insert(BinaryNode cur, int v) {
        if (cur == null) return new BinaryNode(v);

        if (v <= cur.getValue()) {
            cur.setLeft(insert(cur.getLeft(), v));
        } else {
            cur.setRight(insert(cur.getRight(), v));
        }
        return cur;
    }

    public BinaryNode getRoot() {
        return root;
    }
}
