class Stack {
    Node top;

    public Node push(Integer data) {
        return top.prepend(data);
    }

    public Integer peek() {
        return top.data;
    }

    public Node pop() {
        return top.next;
    }
}

class Queue {
    Node first, last;

    void enqueue(Integer data) {
        if(first == null) {
            last = new Node(data);
            first = last;
        } else {
            last.next = new Node(data);
            last = last.next;
        }
    }

    Integer dequeue() {
        if(first != null) {
            Integer d = first.data;
            first = first.next;
            return d;
        }
        return null;
    }
}

public class StacksAndQueues {
    /**
     * 3.1
     * Threee in One: Describe how you could use a single array to implement three stacks.
     */
    public static void threeInOne() {
        
    }

    /**
     * 3.2
     * Stack Min: How would you design a stack which, in addition to push and pop, has a function
     * min which returns the minimum elelment? Push, pop and min should all operate in O(1) time.
     */
    // public static int stackMin(Stack stack) {
    //
    // }


    public static void main(String[] args) {

    }
}
