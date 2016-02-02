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

class Graph {
    class GNode {
        Integer name;
        private HashSet<Integer> adjacencyList = new HashSet<Integer>();

        public GNode(Integer name) {
            this.name = name;
            this.adjacencyList = null;
        }

        public void addEdge(Integer node) {
            if(!adjacencyList.contains(node)) {
                adjacencyList.add(node);
            }
        }

        public void removeEdge(Integer node) {
            if(adjacencyList.contains(node)) {
                adjacencyList.remove(node);
            }
        }
    }

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
        GNode node_a = nodes.get(a);
        node_a.addEdge(b);
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

    public static void BFS(Graph graph, Integer start) {
        HashSet<Integer> visited = new HashSet<Integer>();
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Add start node to queue + mark it as visited
        visited.add(start);
        queue.add(start);

        while(queue.size() != 0) {
            Integer current = queue.remove();


        }
    }


    /**
     *
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

    }
}
