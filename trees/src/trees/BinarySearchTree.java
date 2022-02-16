package trees;

import org.w3c.dom.Node;

import javax.swing.tree.TreeNode;
import java.util.Iterator;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> implements ISearchTree<T>
{
    private TreeNode root;
    private int size;

    @Override
    public boolean add(T element)
    {
        int oldSize = size;
        root = add(root, element);

        return oldSize != size;
    }

    private TreeNode add(TreeNode current, T element)
    {
        //base case (a null reference where we can put a new node)
        if (current == null)
        {
            size++;
            return new TreeNode(element);
        }

        int comparison = current.data.compareTo(element);
        if (comparison < 0) //right
        {
            current.right = add(current.right, element);
        }
        else if (comparison > 0) //left
        {
            current.left = add(current.left, element);
        }
        return current;
    }

    @Override
    public boolean addAll(T... elements)
    {
        boolean result = true;
        for (T element : elements) {
            //only true if every call to add() is true
            result = result && add(element);
        }
        return result;
    }

    @Override
    public boolean contains(T element)
    {
        return contains(root, element);
    }

    private boolean contains(TreeNode current, T element)
    {
        if (current == null) {
            return false;
        }

        int comparison = current.data.compareTo(element);
        if (comparison < 0) //right
        {
            return contains(current.right, element);
        } else if (comparison > 0) //left
        {
            return contains(current.left, element);
        } else //if (comparison == 0)
        {
            return true;
        }
    }

    @Override
    public boolean remove(T element)
    {
        int oldSize = size;
        root = remove(root, element);
        return oldSize != size;
    }

    private TreeNode remove(TreeNode current, T element)
    {
        //we didn't find the element in the tree (base case!)
        if (current == null)
        {
            return null;
        }

        int comparison = current.data.compareTo(element);
        if (comparison < 0) //right
        {
            current.right = remove(current.right, element);
        }
        else if (comparison > 0) //left
        {
            current.left = remove(current.left, element);
        }
        else //if (comparison == 0)
        {
            //we found it, let's remove it! (base case!)

            //no children
            if (current.left == null && current.right == null)
            {
                size--;
                return null;
            }
            //left child
            else if (current.right == null)
            {
                size--;
                return current.left;
            }
            //right
            else if (current.left == null)
            {
                size--;
                return current.right;
            }
            //two children
            else
            {
                //find the largest element in the left subtree
                T largestElement = findMax(current.left);

                //replace the current node data with that element
                current.data = largestElement;

                //recursively delete the node with the largest
                //element in the left subtree
                remove(current.left, largestElement);
            }
        }
        return current;
    }

    private T findMax(TreeNode current)
    {
        //base case?
        if (current.right == null)
        {
            return current.data;
        }
        return findMax(current.right);
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public List<T> inOrder()
    {
        return null;
    }

    @Override
    public List<T> preOrder()
    {
        return null;
    }

    @Override
    public List<T> postOrder()
    {
        return null;
    }

    @Override
    public int treeHeight()
    {
        return 0;
    }

    @Override
    public Iterator<T> iterator()
    {
        return null;
    }

    private class TreeNode
    {
        private T data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(T data)
        {
            this.data = data;
        }

        public TreeNode(T data, TreeNode left, TreeNode right)
        {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString()
        {
            String left = this.left != null ? this.left.data.toString() : "null";
            String right = this.right != null ? this.right.data.toString() : "null";

            return left + " <-- " + data.toString() + " --> " + right;
        }
    }
}














