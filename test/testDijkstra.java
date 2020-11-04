package test;

import data.structure.Graph;

public class testDijkstra {
    public static void testGraphDijkstra() {
        Graph graph = new Graph(false);
        graph.readFileInput("testDijkstraGraph.txt");
        graph.printGraph();
        graph.Dijkstra(graph.getVertexList().getHead().getData());
        graph.showVertexs();
        // graph.showEdges();
    }

    public static void main(String[] args) {
        testGraphDijkstra();
    }
}
