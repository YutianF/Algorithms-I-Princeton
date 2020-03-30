/**
 * https://blog.csdn.net/zlp1992/article/details/51406067
 */
package tree;
public class TreeNode{
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


}