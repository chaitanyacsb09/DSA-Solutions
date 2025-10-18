/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Index{
    int val;
    public Index(){
        val = 0;
    }
};

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder serializedB = new StringBuilder();
        if(root == null){
            return serializedB.toString();.
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                TreeNode curr = q.poll();
                if(curr == null){
                    serializedB.append("#,");
                    continue;                
                }
                serializedB.append(curr.val).append(",");
                q.add(curr.left);
                q.add(curr.right);
            }
        }
        serializedB.deleteCharAt(serializedB.length()-1);
        // System.out.println("Serialized: " + serializedB.toString());
        return serializedB.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("")){
            return null;
        }
        String[] splitData = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(splitData[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int idx = 1;
        while(!q.isEmpty() && idx < splitData.length){
            int size = q.size();
            while(size-- > 0){
                TreeNode curr = q.poll();
                curr.left = splitData[idx].equals("#") ? null : new TreeNode(Integer.parseInt(splitData[idx]));
                idx++;
                curr.right = splitData[idx].equals("#") ? null : new TreeNode(Integer.parseInt(splitData[idx]));
                idx++;
                if(curr.left != null){
                    q.add(curr.left);
                }
                if(curr.right != null){
                    q.add(curr.right);
                }
            }
        } 
        return root;  
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));