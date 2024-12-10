package day5.src;

import java.util.Arrays;

public class LinkedList {
    private Node<String> head;

    public LinkedList() {
        this.head = null;
    }

    public void add(String value) {
        Node<String> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
        } else {
            Node<String> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
            newNode.setPrevious(current);
        }
    }


    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        if (size() == 1 && index == 0) {
            head = null;
            return;
        }
        if (index == 0) {
            head = head.getNext();
            head.setPrevious(null);
        } else {
            Node<String> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            current.getPrevious().setNext(current.getNext());
            if (current.getNext() != null) {
                current.getNext().setPrevious(current.getPrevious());
            }
        }
    }

    public void removeData(String data) {
        if (head.getData().equals(data) && size() > 1) {
            head = head.getNext();
            head.setPrevious(null);
        } else if (size() == 1 && head.getData().equals(data)) {
            head = null;
        } else {
            Node<String> current = head;
            while (current.getNext() != null) {
                if (current.getNext().getData().equals(data)) {
                    current.setNext(current.getNext().getNext());
                    if (current.getNext() != null) {
                        current.getNext().setPrevious(current);
                    }
                    return;
                }
                current = current.getNext();
            }
            throw new IllegalArgumentException("Data not found");
        }
    }

    public void addWeirdRule(String rule) {
        String[] numbers = rule.split("\\|");
        String num0 = numbers[0].trim();
        String num1 = numbers[1].trim();
        Node<String> node0 = contains(num0);
        Node<String> node1 = contains(num1);

        if (node0 == null && node1 == null) {
            // Create new nodes
            Node<String> newNode0 = new Node<>(num0);
            Node<String> newNode1 = new Node<>(num1);

            // Insert num1 at the end
            insertAfter(getTail(), newNode1);

            // Insert num0 before num1
            insertBefore(newNode0, newNode1);
        } else if (node0 == null) {
            // Node0 doesn't exist, insert it before node1
            Node<String> newNode0 = new Node<>(num0);
            insertBefore(newNode0, node1);
        } else if (node1 == null) {
            // Node1 doesn't exist, insert it after node0
            Node<String> newNode1 = new Node<>(num1);
            insertAfter(newNode1, node0);
        } else if (!isBefore(num0, num1)) {
            // Move node0 before node1 to satisfy the rule
            moveBefore(node0, node1);
        }
    }

    private void insertAfter(Node<String> nodeToInsert, Node<String> targetNode) {
        Node<String> nextNode = targetNode.getNext();
        nodeToInsert.setPrevious(targetNode);
        nodeToInsert.setNext(nextNode);
        targetNode.setNext(nodeToInsert);
        if (nextNode != null) {
            nextNode.setPrevious(nodeToInsert);
        }
    }


    // Safely remove a node from the list
    private void remove(Node<String> node) {
        if (node == head) {
            head = node.getNext();
            if (head != null) {
                head.setPrevious(null);
            }
        } else {
            Node<String> prev = node.getPrevious();
            Node<String> next = node.getNext();
            if (prev != null) {
                prev.setNext(next);
            }
            if (next != null) {
                next.setPrevious(prev);
            }
        }
        node.setPrevious(null);
        node.setNext(null);
    }

    // Safely insert a node before another node
    private void insertBefore(Node<String> nodeToInsert, Node<String> targetNode) {
        if (targetNode == null) {
            // Insert at the beginning
            if (head == null) {
                head = nodeToInsert;
            } else {
                nodeToInsert.setNext(head);
                head.setPrevious(nodeToInsert);
                head = nodeToInsert;
            }
        } else {
            Node<String> prev = targetNode.getPrevious();
            nodeToInsert.setNext(targetNode);
            nodeToInsert.setPrevious(prev);
            targetNode.setPrevious(nodeToInsert);
            if (prev != null) {
                prev.setNext(nodeToInsert);
            } else {
                // Inserting before the head
                head = nodeToInsert;
            }
        }
    }


    // Move a node before another node
    private void moveBefore(Node<String> nodeToMove, Node<String> targetNode) {
        if (nodeToMove == targetNode) {
            // Cannot move a node before itself
            return;
        }
        remove(nodeToMove);
        insertBefore(nodeToMove, targetNode);
    }

    public boolean isBefore(String left, String right) {
        Node<String> current = head;
        while (current != null) {
            if (current.getData().equals(left)) {
                return true;
            }
            if (current.getData().equals(right)) {
                return false;
            }
            current = current.getNext(); // Correctly move to the next node
        }
        return false;
    }


    public Node<String> contains(String data) {
        Node<String> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int count = 0;
        Node<String> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }


    public Node<String> getHead() {
        return head;
    }

    public Node<String> getTail() {
        Node<String> current = head;
        if (current == null) {
            return null;
        }
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current;
    }


    @Override
    public java.lang.String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("doubleLinkedList{");
        Node<String> current = head;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) {
                sb.append(" -> ");
            }
            current = current.getNext();
        }
        sb.append("}");
        return sb.toString();
    }
}