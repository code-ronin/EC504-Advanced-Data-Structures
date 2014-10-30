package bstPack;
import java.util.ArrayList;
import java.io.*;
import java.util.List;


/**
 * Implements a rudimentary Binary Search Tree keyed by Integers
 */
public class BST {
    
    // CLASSES
    
    /**
     * Represents one rotation (ZIG or ZAG) around one key in a Binary Search Tree
     */
    public static class Rotation {
        public static enum RotationType {ZIG, ZAG};
        public Rotation(Integer key, RotationType rot) {
            myKey = key;
            myRot = rot;
        }
        /**
         * @return A human-readable description of the rotation
         */
        public String print() {
            String result="";
            switch (myRot) {
                case ZIG:
                    result="ZIG";
                    break;
                case ZAG:
                    result="ZAG";
                    break;
            }
            result+=" on " + myKey;
            return result;
        }
        private Integer myKey;         // the key that is at the root of the rotation being performed
        private RotationType myRot;    // the type of rotation being performed
    }
    
    // CONSTRUCTORS
    
    /**
     * Constructs a one-node Binary Search Tree with the given key.
     * @param num The key of the one node of the tree
     */
    public BST(Integer num) {
        key = num;
        left = null;
        right = null;
    }
    
    /**
     * Constructs a Binary Search Tree by repeatedly inserting Integers in <nums> in the order they appear in the array
     * @param nums The array of Integers to insert
     */
    public BST(Integer[] nums) {
        this(nums[0]); // call the other constructor with the first Integer
        for (int ii=1; ii< nums.length; ii++) // insert all the Integers, one by one
            insert(nums[ii]);
    }
    
    // METHODS
    /**
     * Inserts the new number as a key into the binary search tree
     * @param num The new key to be inserted
     */
    final public void insert(Integer num) {
        if (num > key) {
            // insert to the right
            if (right==null)
                right = new BST(num);
            else
                right.insert(num);
        }
        else // num<=key; insert to the left
            if (left==null)
                left = new BST(num);
            else
                left.insert(num);
    }
    
    /**
     * Return the BST rooted at this node as a human-readable string
     */
    final public String print() {
        return print(0);
    }
    
    static BST rotateleft(BST node)
    {
   		BST node1  = node.left;
   		node.left = node1.right;
   		node1.right = node;
   		return node1;
    }
    
    static BST rotateright(BST node)
    {
		BST node1 = node.right;
		node.right = node1.left;
		node1.left = node;
		return node1;
    }
    
    /**
     * Return the BST rooted at this node as a human-readable string, indented by <depth> characters
     * @param depth How many characters to indent
     */
    final private String print(int depth) {
        String result="";
        // output the key
        result+=Integer.toString(key)+"\n";
        
        // recurse
        if (left!=null)
            result+=RepeatChar(depth)+"L"+left.print(depth+1);
        if (right!=null)
            result+=RepeatChar(depth)+"R"+right.print(depth+1);
        
        return result;
    }
    
    /**
     * Return a string of <depth> spaces
     * @param depth The number of spaces
     */
    private String RepeatChar(int depth) {
        String result="";
        for (int ii=0; ii<depth; ii++)
            result+=THE_CHAR;
        return result;
    }
    
    // FIELDS
    protected Integer key; // the key stored by this node
    protected BST left;    // the left child of this node
    protected BST right;   // the right child of this node
    
    final char THE_CHAR = '-'; // the character to repeat
    
    /**
     * Compute how to transform BST <first> into BST <second> using rotations.
     * @param first The first BST to transform.
     * @param second The desired transformed BST.
     * @return An ArrayList of rotations indicating which rotations around which nodes
     *  must be performed to transform <first> into <second.
     */
    public static ArrayList<Rotation> transform(BST first, BST second) {
        //This is what you have to do
    	
    	ArrayList<Rotation> values = new ArrayList<Rotation>();
    	
    	// Compute the transformation here and transform first into second
    	first=tryfuc(first, second, values);
    	
    	// This prints out the new tree first after the transformation
    	String val = first.print();
    	String val1 = second.print();
    	System.out.printf("New First\n%s\n\nSecond\n%s\n", val,val1);
    	
    	return values;
    }
    
    public static BST tryfuc(BST first, BST second, ArrayList<Rotation> values){
    	//Find the node in tree 1 with the node in tree 2
    	BST current=findNode(first,second.key);
    	//While target node (first) is not root (second) 
    	while(first.key!=second.key){
    		//Find the root node of the second tree in the first tree
    		BST parent=findParentofNode(current,first);
    		//Apply rotations to bring that node up to the root
    		if(parent==first&&parent.right==current){
    			first=rotateright(parent);
    			values.add(new Rotation(current.key,Rotation.RotationType.ZAG));
    		}
    		else if(parent==first&&parent.left==current){
    			first=rotateleft(parent);
    			values.add(new Rotation(current.key,Rotation.RotationType.ZIG));
    		}
    		if(parent.right==current){
    			findParentofNode(parent,first).right=rotateright(parent);
    			values.add(new Rotation(current.key,Rotation.RotationType.ZAG));
    		}
    		else if(parent.left==current){
    			findParentofNode(parent,first).right=rotateleft(parent);
    			values.add(new Rotation(current.key,Rotation.RotationType.ZIG));
    		}
    	}
    	
    	String val = first.print();
    	String val1 = second.print();
    	System.out.printf("New First\n%s\n\nSecond\n%s\n", val,val1);
    	/*Recursively reshape the left subtree of the first tree to have the same shape
    	as the left subtree of the second tree*/
    	if(first.left!=null&&second.left!=null)
    		first.left=tryfuc(first.left,second.left,values);
    	/*Recursively reshape the left subtree of the first tree to have the same shape 
    	as the left subtree of the second tree.*/
    	if(first.right!=null)
    		first.right=tryfuc(first.right,second.right,values);
    	return first;
    }
    
    public static BST findNode(BST node, Integer keyValue)
    {// A useful function...finds the node with a key value.
    	if(node.key==keyValue){
    		return node;
    	}
    	if(node.left==null && node.right==null){
    		return null;
    	}
    	else if(node.key>keyValue && node.left!=null){
    		return findNode(node.left, keyValue);
    	}
    	else if(node.key<keyValue && node.right!=null){
    		return findNode(node.right,keyValue);
    	}
    	return node;
    }
    
    public static BST findParentofNode(BST node,BST parent)
    {//Another useful function
    	if(parent==node){
    		return parent;
    	}
    	if(parent.left==node||parent.right==node){
    		return parent;
    	}
    	if(parent.left==null && parent.right==null){
    		return null;
    	}
    	else if(node.key>parent.key&&parent.right!=null){
    		return findParentofNode(node,parent.right);
    	}
    	else if(node.key<parent.key&&parent.left!=null){
    		return findParentofNode(node,parent.left);
    	}
    	return null;
    }
    
}    
    
