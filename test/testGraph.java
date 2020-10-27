package test;

import data.structure.Graph;

public class testGraph {

    static void testPrintAndrea() {
        Graph graph = new Graph(false);
        graph.readFileInput("test01.txt");
        graph.printGraph();
        // Vertex vertex = graph.getVertexList().getHead().getData();
        // graph.BFS(vertex);
        // graph.DFS(vertex);
        // graph.shortesPaths();

        boolean isConnected = graph.isConnected();

        System.out.println("Es connectado: " + isConnected);
        System.out.println("Componentes conectados: " + graph.getComponentConnected());
    }

    public static void main(String[] args) {
        testPrintAndrea();
    }
}
