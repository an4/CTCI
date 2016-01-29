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

    Node prepend(Integer d) {
        Node head = new Node(d);
        head.next = this;
        return head;
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
    private static int kthToLast(Node list, int k) {
        Node p1 = list;
        Node p2 = list;
        while(p2 != null && k > 0) {
            p2 = p2.next;
            k--;
        }
        if(k != 0) {
            return -1;
        }
        while(p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1.data;
    }

    /**
     * 2.3
     * Delete Middle Node: Implement an algorithm to delete a node in the middle of a singly linked
     * list, given only access to that node.
     * EXAMPLE
     * Input: the node c from the linked list a->b->c->d->e
     * Result: nothing is returned, but the new linked list looks like a->b->d->e
     */
    private static void deleteMiddleNode(Node node) {
        if(node.next == null) {
            node.data = null;
        } else {
            node.data = node.next.data;
            node.next = node.next.next;
        }
    }

    /**
     * 2.4
     * Partition: Write code to partition a linked list around a value x, such that all nodes less
     * than x come before all nodes greater than or equal to x. If x is contained within the list,
     * the values of x only need to be after the elements less than x (See below).
     * EXAMPLE
     * Input 3->5->8->5->10->2->1 [partition = 5]
     * Output 3->1->2->10->5->->5->8
     */
    private static Node partition(Node list, int value) {
        Node head = new Node(list.data);
        list = list.next;
        while(list != null) {
            if(list.data < value) {
                head = head.prepend(list.data);
            } else {
                head.append(list.data);
            }
            list = list.next;
        }
        printList(head);
        return head;
    }

    /**
     * 2.5
     * Sum Lists: You have two numbers represented by a linked list, where each node contains a
     * single digit. The digits are stored in reverse order, such that the 1's digit is at the head
     * of the list. Write a function that adds the two numbers and returns the sum as a linked list.
     * EXAMPLE
     * Input (7->1->6) + (5->9->2). That is, 617 + 295.
     * Output 2->1->9. That is, 912.
     * FOLLOW UP
     * Suppose the digits are stored in forward order. Repeat the above problem.
     * EXAMPLE
     * Input (6->1->7) + (2->9->5). That is, 617 + 295.
     * Output 9->1->2. That is, 912.
     */
    private static Node sumLists(Node a, Node b) {
        Node sum = null;
        int carry = 0;
        while(a != null && b != null) {
            int value = (a.data + b.data + carry)%10;
            carry = (a.data + b.data + carry)/10;
            if(sum == null) {
                sum = new Node(value);
            } else {
                sum.append(value);
            }
            a = a.next;
            b = b.next;
        }

        while(a != null) {
            if(carry != 0) {
                sum.append(a.data + carry);
                carry = 0;
            } else {
                sum.append(a.data);
            }
            a = a.next;
        }

        while(b != null) {
            if(carry != 0) {
                sum.append(b.data + carry);
                carry = 0;
            } else {
                sum.append(b.data);
            }
            b = b.next;
        }

        if(carry != 0) {
            sum.append(carry);
        }

        printList(sum);

        return sum;
    }

    // FOLLOW UP

    private static int getListLength(Node list) {
        int length = 0;
        while(list != null) {
            length++;
            list = list.next;
        }
        return length;
    }

    private static boolean allZeros(Node list) {
        while(list != null) {
            if(list.data != 0) {
                return false;
            }
            list = list.next;
        }
        return true;
    }

    private static Node sumLists2(Node a, Node b) {
        int length_a = getListLength(a);
        int length_b = getListLength(b);

        while(length_a < length_b) {
            a = a.prepend(0);
            length_a++;
        }
        while(length_b < length_a) {
            b = b.prepend(0);
            length_b++;
        }

        while(!allZeros(b)) {
            Node head_a = a;
            Node head_b = b;
            while(a!=null && b!=null) {
                int temp = b.data + a.data;
                a.data = temp%10;
                b.data = temp/10;
                a = a.next;
                b = b.next;
            }
            head_a = head_a.prepend(0);
            head_b.append(0);
            a = head_a;
            b = head_b;
        }

        while(a != null && a.data == 0) {
            a = a.next;
        }
        printList(a);
        return a;
    }

    /**
     * 2.6
     * Palindrome: Implement a function to check if a linked list is a palindrome.
     */

    /**
     * 2.7
     * Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return
     * the interecting node. Note that the intersection is defined based on reference, not value.
     * That is, if the kth node of the first linked list is the exact same node (by reference) as
     * the jth node of the second linked list, then they are intersecting.
     */

    /**
     * 2.8
     * Loop Detection: Given a circular linked list, implement an algorithm that returns the node
     * at the beginning of the loop.
     * DEFINITION
     * Circular linked list: A (corrupt) linked list in which a node's next pointer points to an
     * earlier node, so as to make a loop in the linked list.
     * EXAMPLE
     * Input A->B->C->D->E->C [the same C as earlier]
     * Output C
     */


    public static void main(String[] args) {
        Node a = new Node(7);
        a.append(1);
        a.append(6);

        Node b = new Node(5);
        b.append(9);
        b.append(2);

        Node sum = sumLists2(a, b);
    }
}
