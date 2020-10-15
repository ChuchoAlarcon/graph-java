package test;

import data.structure.Graph;
import data.structure.Vertex;

public class testGraph {

    static void testPrintAndrea() {
        Graph graph = new Graph(false);
        graph.readFileInput("test01.txt");
        graph.printGraph();
        Vertex vertex = graph.getVertexList().getHead().getData();
        // graph.BFS(vertex);
        graph.DFS(vertex);
    }

    public static void main(String[] args) {
        testPrintAndrea();
    }
}
