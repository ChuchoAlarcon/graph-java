package test;

import data.structure.Graph;

public class testGraph {

    static void testPrintAndrea() {
        Graph graph = new Graph(false);
        graph.readFileInput("bolivia.txt");
        graph.printGraph();
        graph.BFS(graph.getVertexList().getHead().getData());
    }

    public static void main(String[] args) {
        testPrintAndrea();
    }
}
