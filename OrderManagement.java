package btvn1;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Arrays;

class Order implements Comparable<Order> {
    int orderID;
    String customerName;
    int priority;
    int totalValue;

    public Order(int orderID, String customerName, int priority, int totalValue) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.priority = priority;
        this.totalValue = totalValue;
    }

    @Override
    public int compareTo(Order o) {
        if (this.priority != o.priority) {
            return o.priority - this.priority;
        }
        return this.totalValue - o.totalValue;
    }
}

class BSTOrderQueue {
    class Node {
        Order order;
        Node left;
        Node right;

        Node(Order order) {
            this.order = order;
        }
    }

    Node root;

    void insert(Order order) {
        root = insert(root, order);
    }

    private Node insertRecursive(Node root, Order order) {
        if (root == null) {
            return new Node(order);
        }
        if (order.compareTo(root.order) < 0) {
            root.left = insertRecursive(root.left, order);
        } else {
            root.right = insertRecursive(root.right, order);
        }
        return root;
    }

    Order search(int orderID) {
        Node res = searchRecursive(root, orderID);
        return res == null ? null : res.order;
    }

    private Node searchRecursive(Node root, int orderID) {
        if (root == null) {
            return null;
        }
        if (orderID < root.order.orderID) {
            return searchRecursive(root.left, orderID);
            return searchRecursive(root.right, orderID);
        }
        return root;
    }

    void Traversal() {
        inorderReccursive(root);
    }

    private void inorderReccursive(Node root) {
        if (root != null) {
            inorderReccursive(root.right);
            System.out.println("Order:" + root.order.orderID + " Priority:" + root.order.priority + " Value:"
                    + root.order.totalValue);
            inorderReccursive(root.left);
        }
    }
}

public class OrderManagement {
    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
            new Order(1, "Alice", 90, 200),
            new Order(2, "Bob", 85, 150),
            new Order(3, "Charlie", 60, 500),
            new Order(4, "David", 70, 450),
            new Order(5, "Eva", 80, 100),
            new Order(6, "Frank", 55, 80),
            new Order(7, "Grace", 65, 250),
            new Order(8, "Henry", 75, 300),
            new Order(9, "Irene", 95, 50),
            new Order(10, "Jack", 88, 120),
            new Order(11, "Kate", 92, 180),
            new Order(12, "Liam", 50, 400)
        );
        LinkedList<Order> orderList = new LinkedList<>(orders);
        System.out.println("Orders with value < 100:");
        for (Order o : orderList) {
            if (o.totalValue < 100) System.out.println(o.customerName + " - $" + o.totalValue);
        }
        
        Collections.sort(orderList);
        System.out.println("\nSorted Orders:");
        for (Order o : orderList) {
            System.out.println(o.customerName + " Priority: " + o.priority + " Value: " + o.totalValue);
        }
        
        BSTOrderQueue bst = new BSTOrderQueue();
        for (Order o : orders) bst.insert(o);
        
        System.out.println("\nBST Inorder Traversal:");
        bst.inorderTraversal();
    }
}
