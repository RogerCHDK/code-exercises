package src.tree;

public class Solution {
    public TreeNode invertTree(TreeNode root) {
       recorrerTree(root);
       return root;
    }

    public void recorrerTree(TreeNode root){
        if (root == null)
            return;
        recorrerTree(root.left);
        recorrerTree(root.right);
        TreeNode right = root.right;
        TreeNode left = root.left;
        root.left = right;
        root.right = left;
    }
}
