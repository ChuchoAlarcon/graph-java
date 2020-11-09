package data.structure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graph {
    private boolean directed;
    // private boolean weighted;
    private ListLinked<Vertex> vertexList;
    private Vertex[] vertexs;
    private ListLinked<Edge> edgesList;
    private int numVertexs;

    private boolean isConnected;
    private int componentConnected;
    private int time;

    public Graph(boolean directed) {
        this.directed = directed;
        isConnected = false;
        time = 0;
        componentConnected = 0;
        vertexList = new ListLinked<>();
        edgesList = new ListLinked<>();
    }

    public Graph(boolean directed, int numVertexs) {
        this.directed = directed;
        isConnected = false;
        componentConnected = 0;
        vertexs = new Vertex[numVertexs];
    }

    public boolean isConnected() {
        BFS();
        return isConnected;
    }

    public int getComponentConnected() {
        return componentConnected;
    }

    public boolean getDirected() {
        return directed;
    }

    public ListLinked<Vertex> getVertexList() {
        return vertexList;
    }

    public Vertex[] getVertexs() {
        return vertexs;
    }

    public int getNumVertexs() {
        return numVertexs;
    }

    public void addVertex(Vertex vertex) {
        vertexList.add(vertex);
    }

    public void addEdge(Vertex v1, Vertex v2, double weight) {
        Edge edge = new Edge(v1, v2, weight);
        v1.addEdge(edge);
        edgesList.add(edge);
        if (!directed) {
            Edge edge2 = new Edge(v2, v1, weight);
            v2.addEdge(edge2);
            edgesList.add(edge2);
        }
    }

    public void DFS_Stack(Vertex vertex) {
        ListLinked<Vertex> travelBFS = new ListLinked<>();
        // Queue<Vertex> queue = new LinkedList<>();// estructura de datos cola
        Stack<Vertex> stack = new Stack<>();
        stack.push(vertex);
        vertex.setStatus(State.VISITADO);
        travelBFS.add(vertex);
        while (!stack.isEmpty()) {
            vertex = stack.pop();// vertice padre
            ListLinked<Edge> lEdges = vertex.getEdges();
            Node<Edge> node = lEdges.getHead();
            while (node != null) {
                Vertex opposite = node.getData().getV2();// vertices hijos
                if (opposite.getState() == State.NO_VISITADO) {
                    stack.add(opposite);
                    opposite.setStatus(State.VISITADO);

                    opposite.setJumps(vertex.getJumps() + 1);
                    travelBFS.add(opposite);
                }
                node = node.getLink();
            }
            vertex.setStatus(State.PROCESADO);
        }
        Node<Vertex> temp = travelBFS.getHead();
        while (temp != null) {
            System.out.print(temp.getData().getLabel() + "{" + temp.getData().getJumps() + "}\t");
            temp = temp.getLink();
        }
    }

    public void BFS(Vertex vertex) {
        ListLinked<Vertex> travelBFS = new ListLinked<>();
        Queue<Vertex> queue = new LinkedList<>();// estructura de datos cola
        queue.add(vertex);
        vertex.setStatus(State.VISITADO);
        travelBFS.add(vertex);
        while (!queue.isEmpty()) {
            vertex = queue.poll();// vertice padre
            ListLinked<Edge> lEdges = vertex.getEdges();
            Node<Edge> node = lEdges.getHead();
            while (node != null) {
                Vertex opposite = node.getData().getV2();// vertices hijos
                if (opposite.getState() == State.NO_VISITADO) {
                    queue.add(opposite);
                    opposite.setStatus(State.VISITADO);
                    opposite.setParent(vertex);
                    opposite.setJumps(vertex.getJumps() + 1);
                    travelBFS.add(opposite);
                }
                node = node.getLink();
            }
            vertex.setStatus(State.PROCESADO);
        }
        /*
         * Node<Vertex> temp = travelBFS.getHead(); while (temp != null) {
         * System.out.print(temp.getData().getLabel() + "{" + temp.getData().getJumps()
         * + "}\t"); temp = temp.getLink(); }
         */
    }

    public void BFS() {
        Node<Vertex> iterator = vertexList.getHead();
        isConnected = false;
        while (iterator != null) {
            Vertex vertex = iterator.getData();
            if (vertex.getState().compareTo(State.NO_VISITADO) == 0) {
                BFS(vertex);
                componentConnected++;
                isConnected = componentConnected == 1;
            }
            iterator = iterator.getLink();
        }
    }

    public void DFS(Vertex vertex, ListLinked<Vertex> travelDFS) {
        vertex.setStatus(State.VISITADO);
        travelDFS.add(vertex);
        vertex.setTimeEntry(time);
        time++;
        vertex.setReachableAncestor(vertex);
        Node<Edge> nEdge = vertex.getEdges().getHead();
        while (nEdge != null) {
            Vertex oppositeVertex = nEdge.getData().getV2();
            if (oppositeVertex.getState().compareTo(State.NO_VISITADO) == 0) {
                nEdge.getData().setType(TypeEdge.TREE);
                // Asignamos el vertice padre que descubre a un nuevo vertice
                oppositeVertex.setParent(vertex);
                // actualizamos el grado de cada vertice
                vertex.setTreeOutDegree(vertex.getTreeOutDegree() + 1);
                DFS(oppositeVertex, travelDFS);
            } else {
                if (vertex.getParent() != null
                        && vertex.getParent().getLabel().compareTo(oppositeVertex.getLabel()) != 0) {
                    nEdge.getData().setType(TypeEdge.LATER);
                    if (oppositeVertex.getTimeEntry() < vertex.getReachableAncestor().getTimeEntry()) {
                        vertex.setReachableAncestor(oppositeVertex);
                    }
                }
            }
            nEdge = nEdge.getLink();
        }
        // verificamos si el vertice es la raiz y si tiene mas de un hijo
        if (vertex.getParent() == null) {
            if (vertex.getTreeOutDegree() > 1) {
                // System.out.println("\nVertice raiz corte: " + vertex.getLabel() + "\n");
                vertex.setType(TypeVertex.ROOT_CUTNODE);
            }
        }
        // Si el vertice alcanzable desde v es el padre de v,
        // entonces el padre es vertice de articulacion
        else if (vertex.getReachableAncestor().getLabel().compareTo(vertex.getParent().getLabel()) == 0
                && (vertex.getParent() != null)) {
            // System.out.println("\nVertice padre corte: " + vertex.getLabel() + "\n");
            vertex.setType(TypeVertex.PARENT_CUTNODE);
        }
        // vertice padre puente corte
        else if (vertex.getReachableAncestor().getLabel().compareTo(vertex.getLabel()) == 0) {
            vertex.getParent().setType(TypeVertex.BRIDGE_CUTNODE);
            // vertice hijo no hoja
            if (vertex.getTreeOutDegree() > 0) {
                vertex.setType(TypeVertex.BRIDGE_CUTNODE);
            }
        }
        if (vertex.getParent() != null) {
            int time_vertex = vertex.getReachableAncestor().getTimeEntry();
            int time_parent = vertex.getParent().getReachableAncestor().getTimeEntry();
            if (time_vertex < time_parent) {
                vertex.getParent().setReachableAncestor(vertex.getReachableAncestor());
            }
        }
        vertex.setStatus(State.PROCESADO);
        vertex.setTimeExit(time);
        time++;
    }

    public void DFS(Vertex vertexStart) {
        ListLinked<Vertex> travelDFS = new ListLinked<>();
        time = 0;
        DFS(vertexStart, travelDFS);
        Node<Vertex> nVertex = travelDFS.getHead();
        while (nVertex != null) {
            Vertex vertex = nVertex.getData();
            System.out.println(vertex.getLabel() + "=> d(" + vertex.getTimeEntry() + "), f(" + vertex.getTimeExit()
                    + "), state(" + vertex.getState() + "), degreeTree(" + vertex.getTreeOutDegree() + "), reachable("
                    + vertex.getReachableAncestor().getLabel() + "), type(" + vertex.getType() + ")");
            nVertex = nVertex.getLink();
        }
    }

    public void Dijkstra(Vertex vertex) {
        vertex.setStatus(State.VISITADO);
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.offer(vertex);
        while (!queue.isEmpty()) {
            Vertex minWeightVertex = queue.poll();
            minWeightVertex.setStatus(State.PROCESADO);
            // System.out.println(minWeightVertex.getLabel() + " " +
            // minWeightVertex.getState());
            Node<Edge> nEdge = minWeightVertex.getEdges().getHead();
            while (nEdge != null) {
                Edge edge = nEdge.getData();
                Vertex opposite = edge.getV2();
                double pathCost = edge.getWeight() + minWeightVertex.getDijkstraValue();

                if (opposite.getState().compareTo(State.NO_VISITADO) == 0) {
                    opposite.setStatus(State.VISITADO);
                    opposite.setDijkstraValue(pathCost);
                    opposite.setParent(minWeightVertex);
                    queue.offer(opposite);
                } else if (opposite.getState().compareTo(State.VISITADO) == 0) {
                    if (opposite.getDijkstraValue() > pathCost) {
                        opposite.setDijkstraValue(pathCost);
                        opposite.setParent(minWeightVertex);
                    }
                }
                System.out.println(opposite.getLabel() + " " + opposite.getState() + " " + opposite.getDijkstraValue());
                nEdge = nEdge.getLink();
            }
        }
    }

    public void showVertexs() {
        Node<Vertex> nVertex = vertexList.getHead();
        while (nVertex != null) {
            Vertex vertex = nVertex.getData();
            System.out.println("Vertex={" + vertex.getLabel() + "}, state={" + vertex.getState() + "}, dijkstra={"
                    + vertex.getDijkstraValue() + "}");
            nVertex = nVertex.getLink();
        }
    }

    public Vertex getVertexArticulation() {
        return null;
    }

    public void showEdges() {
        Node<Edge> nEdge = edgesList.getHead();
        while (nEdge != null) {
            Edge edge = nEdge.getData();
            System.out.println(edge);
            nEdge = nEdge.getLink();
        }
    }

    public void shortesPaths() {
        BFS(vertexList.getHead().getData());
        System.out.println("Caminos cortos");
        for (int i = 0; i < vertexs.length; i++) {
            String route = printShortPath(getShortPath(vertexs[i]));
            System.out.println(vertexs[i].getLabel() + "(" + vertexs[i].getJumps() + "): " + route);
        }
    }

    public void shortPath(Vertex start, Vertex finish) {
        BFS(start);
        Stack<Vertex> shortPath = getShortPath(finish);
        printShortPath(shortPath);
    }

    public String printShortPath(Stack<Vertex> shortPath) {
        String output = "";
        if (!shortPath.isEmpty()) {
            while (!shortPath.isEmpty()) {
                output += shortPath.pop().getLabel();
                if (shortPath.size() >= 1) {
                    output += " -> ";
                }
            }
        } else {
            output += "Sin caminos cortos, vertice aislado";
        }
        // output += "\n";
        return output;
    }

    public Stack<Vertex> getShortPath(Vertex vertex) {
        Stack<Vertex> shortPath = new Stack<>();
        Vertex parent = vertex.getParent();
        if (parent != null) {
            shortPath.push(vertex);
            while (parent != null) {
                shortPath.push(parent);
                parent = parent.getParent();
            }
        }
        return shortPath;
    }

    public void printGraph() {
        ListLinked<Edge> edges;
        String output = "";
        for (int i = 0; i < vertexs.length; i++) {
            Vertex vertex = vertexs[i];
            output = output + vertex.getLabel();
            edges = vertex.getEdges();
            output = output + "(" + edges.size() + ") -> ";
            Node<Edge> temp = edges.getHead();
            while (temp != null) {
                output = output + "{" + temp.getData().getV2().getLabel() + "} ";
                temp = temp.getLink();
            }
            output = output + "\n";
        }
        System.out.println(output);
    }

    public void readFileInput(String filename) {
        String path = System.getProperty("user.dir") + "\\input\\" + filename;
        try {

            System.out.println(path);
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            String line = "";
            line = scanner.next();
            Pattern pattern;
            Matcher matcher;

            pattern = Pattern.compile("size\\s*=\\s*(\\d+)");
            matcher = pattern.matcher(line);
            matcher.find();
            String strSize = matcher.group(1);
            System.out.println(strSize);

            vertexs = new Vertex[Integer.parseInt(strSize)];
            // Obteniendo las lineas de informacion de vertices
            while (!(line = scanner.nextLine()).equals(";")) {

                pattern = Pattern.compile("(\\d+)\\s*=\\s*(.+)");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    Vertex vertex = new Vertex(matcher.group(2));
                    addVertex(vertex);
                    vertexs[Integer.parseInt(matcher.group(1))] = vertex;
                }
                // System.out.println(line);
            }

            // Obteniendo las lineas de informacion de aristas
            while (!(line = scanner.nextLine()).equals(";")) {
                pattern = Pattern.compile("\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    int posV1 = Integer.parseInt(matcher.group(1));
                    int posV2 = Integer.parseInt(matcher.group(2));
                    Vertex v1 = vertexs[posV1];
                    Vertex v2 = vertexs[posV2];
                    double weight = Double.parseDouble(matcher.group(3));
                    addEdge(v1, v2, weight);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        /*
         */

    }

    public static void main(String[] args) {
        Graph graph = new Graph(false);

        Vertex LaPaz = new Vertex("La Paz");
        Vertex Cochabamba = new Vertex("Cochabamba");
        Vertex SantaCruz = new Vertex("Santa Cruz");
        Vertex Riberalta = new Vertex("Riberalta");

        LaPaz.addEdge(new Edge(LaPaz, Cochabamba));
        LaPaz.addEdge(new Edge(LaPaz, SantaCruz));
        LaPaz.addEdge(new Edge(LaPaz, Riberalta));

        graph.addVertex(LaPaz);
        graph.addVertex(Cochabamba);
        graph.addVertex(SantaCruz);
        graph.addVertex(Riberalta);

        graph.readFileInput("bolivia.txt");
    }
}
