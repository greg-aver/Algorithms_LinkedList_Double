package org.gregory;

public class Node {
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value) {
        value = _value;
        next = null;
        prev = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (this.getValue() != node.getValue()) return false;

        if (this.getNext() == null ^ node.getNext() == null
                || this.getPrev() == null ^ node.getPrev() == null) {
            return false;
        }

        if (this.getNext() == null && node.getNext() == null) {
            if (this.getPrev() == null && node.getPrev() == null) {
                return true;
            } else {
                if (this.getPrev().getValue() == node.getPrev().getValue()) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        if (this.getPrev() == null && node.getPrev() == null) {
            if (this.getNext().getValue() == node.getNext().getValue()) {
                return true;
            }
        }
        return false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}
