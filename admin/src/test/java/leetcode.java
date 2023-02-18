import lombok.Data;
import  java.util.Arrays;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;

public class leetcode {


    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        buildTree(preorder,inorder);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        //生成节点
        if(preorder.length==0){
            return null;
        }
        if(preorder.length==1){
            return new TreeNode(preorder[0]);
        }
        //生成节点
        TreeNode node = new TreeNode(preorder[0]);
        //获取子树的前序遍历和中序遍历
        int root = getIndex(inorder,preorder[0]);

        int[] leftInorder = Arrays.copyOfRange(inorder,0,root);
        int[] leftPreorder = Arrays.copyOfRange(preorder,1,1+root);
        int[] rightInorder = Arrays.copyOfRange(inorder,root+1,inorder.length);
        int[] rightPreorder = Arrays.copyOfRange(preorder,1+root,preorder.length);
        node.left = buildTree(leftPreorder,leftInorder);
        node.right = buildTree(rightPreorder,rightInorder);


        return node;
    }


    public static int getIndex(int[] arr, int target){
        for(int i = 0 ; i < arr.length ; i++){
            if(arr[i] == target){
                return i;
            }
        }

        return -1;
    }


}

@Data
class TreeNode {
    int val;
    TreeNode left;
   TreeNode right;
    TreeNode(int x) { val = x; }
 }