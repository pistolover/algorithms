package algorithms.search;

import java.util.ArrayList;
import java.util.List;

import leetcode.utils.TreeNode;
import test.t10;

public class WidenSearch {

    public static void main(String[] args) {
        TreeNode n = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);
        TreeNode n11 = new TreeNode(11);
        TreeNode n12 = new TreeNode(12);
        TreeNode n13 = new TreeNode(13);
        TreeNode n14 = new TreeNode(14);
        TreeNode n15 = new TreeNode(15);
        n.left = n1;
        n.right = n2;
        n1.left = n3;
        n3.left = n4;
        n3.right = n5;
        n4.left = n6;
        n6.left = n7;
        n7.left = n8;
        n8.left = n9;
        n5.left = n10;
        n5.right = n11;
        n11.left = n12;
        n11.right = n13;
        n13.left = n14;
        n14.right = n15;
        
        deepSearch(n, 21);

    }

    public static boolean deepSearch(TreeNode node, int target) {
        if (node == null)
            return false;
        if (target == node.val)
            System.err.println(target);
        if (node.left != null) {
            deepSearch(node.left, target);
        }

        if (node.right != null) {
            deepSearch(node.right, target);
        }
        return false;
    }

}
