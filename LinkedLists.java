import java.util.HashSet;

class Node {
    Node next = null;
    Integer data;

    public Node(Integer d) {
        data = d;
    }

    void append(Integer d) {
        Node end = new Node(d);
        Node n = this;
        while(n.next != null) {
            n = n.next;
        }
        n.next = end;
    }
}

public class LinkedLists {
    /**
     * 2.1
     * Remove Dups: Write code to remove duplicates from an unosrted linked list.
     * FOLLOW UP
     * How would you solve this problem if a temporary buffer is not allowed?
     */
    private static void removeDups(Node head) {
        printList(head);
        HashSet<Integer> set = new HashSet<Integer>();
        Node previous = null;
        Node list = head;
        while(list != null) {
            if(set.contains(list.data)) {
                previous.next = list.next;
            } else {
                set.add(list.data);
                previous = list;
            }
            list = list.next;
        }

        printList(head);
    }

    public static void printList(Node list) {
        Node head = list;
        while(head != null) {
            if(head.next == null) {
                System.out.print(head.data);
            } else {
                System.out.print(head.data + "->");
            }
            head = head.next;
        }
        System.out.println();
    }

    // FOLLOW UP
    private static void removeDups2(Node head) {
        Node list = head;
        while(list != null) {
            Node next = list;
            while(next.next != null) {
                if(next.next.data == list.data) {
                    next.next = next.next.next;
                } else {
                    next = next.next;
                }
            }
            list = list.next;
        }
        printList(head);
    }

    /**
     * 2.2
     * Return Kth to Last: Implement an algorithm to find th Kth to last element of  a singly linked
     * list.
     */
    private static int kthToLast(Node list) {
        
    }


    public static void main(String[] args) {
        Node list = new Node(0);
        list.append(1);
        list.append(0);
        list.append(1);
        list.append(0);
        list.append(1);
        removeDups2(list);
    }
}
