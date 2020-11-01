package test;

import data.structure.Graph;

public class testDFS {
    public static void testGraphDFS() {
        Graph graph = new Graph(false);
        graph.readFileInput("test01.txt");
        graph.printGraph();
        graph.DFS(graph.getVertexList().getHead().getData());
    }

    public static void main(String[] args) {
        testGraphDFS();
    }
}
