package data.structure;

public class Edge {
    private Vertex v1;
    private Vertex v2;
    private double weight;

    public Edge(Vertex v1, Vertex v2, double weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public Edge(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = 0;
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String toString() {
        return "Edge={v1={" + v1.getLabel() + "},v2={" + v2.getLabel() + "},weight={" + weight + "}}";
    }
}
