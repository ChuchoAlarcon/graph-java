package data.structure;

public class Node {
    private Object data;
    private Node link;

    public Node(Object data) {
        this.data = data;
        this.link = null;
    }

    public Node(Object data, Node link) {
        this.data = data;
        this.link = link;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }

    public String toString() {
        return "Node={data={" + data + "},link={" + link + "}}";
    }

}
