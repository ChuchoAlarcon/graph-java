package data.structure;

public class Vertex implements Comparable<Vertex> {
    private String label;
    private ListLinked<Edge> edges;
    private State state;
    private int jumps;
    private Vertex parent;
    private int tiemEntry;
    private int tiemExit;
    private double dijkstraValue;

    public Vertex(String label) {
        this.label = label;
        this.jumps = 0;
        this.tiemEntry = 0;
        this.tiemExit = 0;
        dijkstraValue = 0;
        edges = new ListLinked<>();
        state = State.NO_VISITADO;
    }

    public void setDijkstraValue(double dijkstraValue) {
        this.dijkstraValue = dijkstraValue;
    }

    public double getDijkstraValue() {
        return dijkstraValue;
    }

    public void setTimeEntry(int timeEntry) {
        this.tiemEntry = timeEntry;
    }

    public void setTimeExit(int timeExit) {
        this.tiemExit = timeExit;
    }

    public int getTimeEntry() {
        return tiemEntry;
    }

    public int getTimeExit() {
        return tiemExit;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public Vertex getParent() {
        return parent;
    }

    public int getJumps() {
        return jumps;
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
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

    @Override
    public int compareTo(Vertex v) {
        if (v.getDijkstraValue() < getDijkstraValue()) {
            return 1;
        } else {
            return -1;
        }
    }
}
