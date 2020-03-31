/**
 * https://blog.csdn.net/zlp1992/article/details/51406067
 */
package tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){
        val = x;
    }
    public String toString(){
        return "val:" +val;
    }

    public void visit(TreeNode node){
        System.out.print(node.val+" ");
    }

    public void preOrderRecursion(TreeNode node){
        if(node==null) return;
        visit(node);
        preOrderRecursion(node.left);
        preOrderRecursion(node.right);
    }

    public void inOrderRecursion(TreeNode node){
        if(node==null) return;
        inOrderRecursion(node.left);
        visit(node);
        inOrderRecursion(node.right);
    }

    public void postOrderRecursion(TreeNode node){
        if(node==null) return;
        postOrderRecursion(node.left);
        postOrderRecursion(node.right);
        visit(node);
    }

    public int level(TreeNode node){
        if(node==null) return 0;
        return level(node.left)>level(node.right)? level(node.left)+1:level(node.right)+1;
    }

    /**
     * 层序遍历 
     * 广度优先搜索(BFS) breadth-first search
     */
    public void levelOrder(TreeNode node){
        if(node==null) return;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(node);
        TreeNode curr;
        while(!q.isEmpty()){
            curr = q.poll(); ////取出队列头部的元素，并从队列中移除
            visit(curr);
            if(curr.left!=null) q.add(curr.left);
            if(curr.right!=null) q.add(curr.right);
        }
    }


}