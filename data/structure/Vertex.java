package data.structure;



public class Vertex {
    private String label;
    private ListLinked<Edge> edges;
    private State state;
    // 1
    // 2
    // 3

    public Vertex(String label) {
        this.label = label;
        edges = new ListLinked<>();
        state = State.NO_VISITADO;
    }

    public void setStatus(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void addEdge(Vertex v1, Vertex v2, double weight) {
        Edge edge = new Edge(v1, v2, weight);
        edges.add(edge);
    }

    public String getLabel() {
        return label;
    }

    public ListLinked<Edge> getEdges() {
        return edges;
    }

    public String toString() {
        return "Vertex={label={" + label + "},edges={" + edges + "}}";
    }
}
