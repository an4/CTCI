import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

class Tree {
    Tree left;
    Tree right;
    Integer data;

    public Tree(Integer data) {
        this.data = data;
    }

    public void insert(Integer data) {
        Tree node = new Tree(data);
        Tree tree = this;
        while(tree != null) {
            if(data <= tree.data) {
                if(tree.left != null) {
                    tree = tree.left;
                } else {
                    tree.left = node;
                    return;
                }
            } else {
                if(tree.right != null) {
                    tree = tree.right;
                } else {
                    tree.right = node;
                    return;
                }
            }
        }
    }

    public Tree search(Integer value) {
        Tree tree = this;
        if(tree == null) {
            return null;
        }
        while(tree != null) {
            if(tree.data == value) {
                return tree;
            } else if(value <= tree.data) {
                tree = tree.left;
            } else {
                tree = tree.right;
            }
        }
        return null;
    }
}

class GNode {
    Integer name;
    private HashSet<Integer> adjacencyList = new HashSet<Integer>();

    public GNode(Integer name) {
        this.name = name;
    }

    public void addEdge(Integer node) {
        this.adjacencyList.add(node);
    }

    public void removeEdge(Integer node) {
        if(this.adjacencyList.contains(node)) {
            this.adjacencyList.remove(node);
        }
    }

    public HashSet<Integer> getNeighbours() {
        return this.adjacencyList;
    }
}

class Graph {
    HashMap<Integer, GNode> nodes = new HashMap<Integer, GNode>();

    public void addNode(Integer node_name) {
        GNode node = new GNode(node_name);
        nodes.put(node_name, node);
    }

    public void addEdgeBoth(Integer a, Integer b) {
        this.addEdge(a, b);
        this.addEdge(b, a);
    }

    public void addEdge(Integer a, Integer b) {
        if(this.nodes.containsKey(a)) {
            GNode node_a = this.nodes.get(a);
            node_a.addEdge(b);
        }
    }

    public void removeEdgeBoth(Integer a, Integer b) {
        this.removeEdge(a, b);
        this.removeEdge(b, a);
    }

    public void removeEdge(Integer a, Integer b) {
        GNode node_a = nodes.get(a);
        node_a.removeEdge(b);
    }
}

public class TreesAndGraphs {
    /* Pre-Order Traversal */
    public static void preOrder(Tree tree) {
        if(tree == null) {
            return;
        }
        System.out.println(tree.data);
        preOrder(tree.left);
        preOrder(tree.right);
    }

    /* In-Order Traversal */
    public static void inOrder(Tree tree) {
        if(tree == null) {
            return;
        }
        inOrder(tree.left);
        System.out.println(tree.data);
        inOrder(tree.right);
    }

    /* Post-Order Traversal */
    public static void postOrder(Tree tree) {
        if(tree == null) {
            return;
        }
        postOrder(tree.left);
        postOrder(tree.right);
        System.out.println(tree.data);
    }

    public static void BFS(Graph graph) {
        HashSet<Integer> visited = new HashSet<Integer>();
        LinkedList<Integer> queue = new LinkedList<Integer>();
        Integer start = 0;
        System.out.println("Breadth-First Search:");
        queue.add(start);
        visited.add(start);

        while(queue.size() != 0) {
            Integer current = queue.remove();
            System.out.println(current);
            for(Integer n: graph.nodes.get(current).getNeighbours()) {
                if(!visited.contains(n)) {
                    queue.add(n);
                    visited.add(n);
                }
            }
        }
    }

    public static void DFSsearch(Integer node, HashSet<Integer> visited, Graph graph) {
        if(node == null) {
            return;
        }
        System.out.println(node);
        visited.add(node);
        GNode n = graph.nodes.get(node);
        for(Integer adj: n.getNeighbours()) {
            if(!visited.contains(adj)) {
                DFSsearch(adj, visited, graph);
            }
        }
    }

    public static void DFS(Graph graph) {
        HashSet<Integer> visited = new HashSet<Integer>();
        Integer start = 0;
        System.out.println("Depth-First Search:");
        DFSsearch(0, visited, graph);
    }

    /**
     * 4.1
     * Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is
     * a route between two nodes.
     */
    private static boolean routeBetweenNodes(Graph graph, Integer a, Integer b) {
        HashSet<Integer> visited = new HashSet<Integer>();
        LinkedList<Integer> queue = new LinkedList<Integer>();

        queue.add(a);
        visited.add(a);

        while(queue.size() != 0) {
            int current = queue.remove();
            for(Integer n: graph.nodes.get(current).getNeighbours()) {
                if(!visited.contains(n)) {
                    visited.add(n);
                    queue.add(n);
                    if(n == b) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 4.2
     * Minimal Tree: Given a sorted (increading order) array with unique integer elements, write an
     * algorithm to create a binary search tree with minimal height.
     */
    private static Tree makeMiddleRoot(int[] arr, int start, int end) {
        if(start > end) {
            return null;
        }
        int middle = (start+end)/2;
        Tree tree = new Tree(arr[middle]);
        tree.left = makeMiddleRoot(arr, start, middle-1);
        tree.right = makeMiddleRoot(arr, middle+1, end);
        return tree;
    }

    private static Tree minimalTree(int[] arr) {
        return makeMiddleRoot(arr, 0, arr.length-1);
    }

    /**
     * 4.3
     * List of Depths: Given a binary tree, design an algorithm which creates a linked list of all
     * the nodes at each depth (e.g., if you have a tree with depth D, you'll create D linked lists)
     */
    private static ArrayList<LinkedList<Tree>> listOfDepths(Tree tree) {
        ArrayList<LinkedList<Tree>> output = new ArrayList<LinkedList<Tree>>();
        // Add root to queue
        LinkedList<Tree> list = new LinkedList<Tree>();
        list.add(tree);
        output.add(list);

        while(list.size() != 0) {
            LinkedList<Tree> next = new LinkedList<Tree>();
            for(Tree current : list) {
                if(current.left != null) {
                    next.add(current.left);
                }
                if(current.right != null) {
                    next.add(current.right);
                }
            }
            if(next.size() > 0) {
                output.add(next);
            }
            list = next;
        }
        return output;
    }

    private static void test_listOfDepths() {
        int[] arr = {0,1,2,3,4,5,6};

        Tree tree = minimalTree(arr);

        ArrayList<LinkedList<Tree>> list = listOfDepths(tree);

        for(LinkedList<Tree> l : list) {
            for(Tree el: l) {
                System.out.print(el.data + " ");
            }
            System.out.println();
        }
    }

    /**
     * 4.4
     * Check Balanced: Implement a function to check if a bunary tree is balanced. For the purpose
     * of this question, a balanced tree is defined to be a tree such that the heights of the two
     * subtrees of any node never differ by more than one.
     */
    public static int checkBalanced(Tree tree) {
        if(tree == null) {
            return 0;
        }
        int leftHeight = checkBalanced(tree.left);
        if(leftHeight == -1) {
            return -1;
        }
        int rightHeight = checkBalanced(tree.right);
        if(rightHeight == -1) {
            return -1;
        }

        if(Math.abs(rightHeight - leftHeight) > 1) {
            return -1;
        } else {
            return Math.max(rightHeight, leftHeight) + 1;
        }
    }

    public static boolean isBalanced(Tree tree) {
        if(checkBalanced(tree) == -1) {
            return false;
        }
        return true;
    }

    /**
     * 4.5
     * Validate BST: Implement a function to check if a binary tree is a binary search tree.
     */
    public static boolean checkBST(Tree tree, Integer min, Integer mx) {
        if(tree == null) {
            return true;
        }
        if((min != null && tree.data <= min) || (max != null && tree.data > max)) {
            return false;
        }
        if(!checkBST(tree.left, min, tree.data) || !checkBST(tree.right, tree.data, max)) {
            return false;
        }
        return true;
    }

    public static boolean validateBST(Tree tree) {
        return checkBST(tree, null, null);
    }

    /**
     * 4.6
     * Successor: Write an algorithm to find the "next" node (i.e., in-order successor) of a given
     * node in a binary search tree. You may assume that each node has a link to its parent.
     */

    /**
     * 4.7
     * Build Order: You are given a list of projects and a list of dependencies (which is a list of
     * pairs of projects, where the first project is depenedent on the second project). All of a
     * project's dependencies must be built before the project is. Find a build order that will
     * allow the projects to be built. If there is no valid build order, return an error.
     * EXAMPLE
     * Input:
     *  projects: a, b, c, d, e, f
     *  dependencies: (d,a), (b,f), (d,b), (a,f), (c,d)
     * Output: f,e,a,b,d,c
     */


    public static void main(String[] args) {
        /**
         * Test tree
         *        5
         *     /    \
         *    3      7
         *  /  \   /  \
         * 1   4  6   9
         */
        Tree tree = new Tree(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);
        tree.insert(9);
        tree.insert(6);

        System.out.println("Pre-order traversal");
        preOrder(tree);
        System.out.println("In-order traversal");
        inOrder(tree);
        System.out.println("Post-order traversal");
        postOrder(tree);

        /**
         * Test graph
         *
         *
         */
        Graph graph = new Graph();
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addEdge(0,1);
        graph.addEdge(0,4);
        graph.addEdge(0,5);
        graph.addEdge(1,4);
        graph.addEdge(1,3);
        graph.addEdge(2,1);
        graph.addEdge(3,2);
        graph.addEdge(3,4);

        DFS(graph);
        BFS(graph);

        // System.out.println(routeBetweenNodes(graph, 4, 5));
        // System.out.println(routeBetweenNodes(graph, 1, 2));

        // test_listOfDepths();

        System.out.println(isBalanced(tree));
    }
}
