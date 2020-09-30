package data.structure;

public class ListLinked {
    Node head;
    Node tail;
    int size;

    public ListLinked() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addHead(Object data) {
        Node node = new Node(data);
        if (isEmpty()) {
            tail = node;
        }
        node.setLink(head);
        head = node;
        size++;
    }

    public void addTail(Object data) {
        Node node = new Node(data);
        if (isEmpty()) {
            head = node;
        } else {
            tail.setLink(node);
        }
        tail = node;
        size++;
    }

    public String toString() {
        return "List={head={" + head + "},tail={" + tail + "},size={" + size + "}}";
    }
}
