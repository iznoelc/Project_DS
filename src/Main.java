public class Main {
    public static void main(String[] args){
        // Create some example nodes
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);


        // Example: directed graph
        System.out.println("-----EXAMPLE: DIRECTED GRAPH-----");
        Graph myGraph = new Graph(true);

        // TEST ADDING SOME VERTICES
        myGraph.addVertex(node1);
        myGraph.addVertex(node2);
        myGraph.addVertex(node3);
        myGraph.addVertex(node4);
        myGraph.addVertex(node5);
        myGraph.addVertex(node1); // try adding vertex that already exists

        System.out.println();
        myGraph.printGraph();
        System.out.println();

        // TEST ADDING SOME EDGES
        myGraph.addEdge(node1,node2);
        myGraph.addEdge(node4,node3);
        myGraph.addEdge(node5,new Node(7)); // purposefully test with value that doesn't exist in graph
        myGraph.addEdge(node2,node5);
        myGraph.addEdge(node1,node3);
        myGraph.addEdge(node2,node5); // try adding edge that already exists

        System.out.println();
        myGraph.printGraph();
        System.out.println();

        // TEST REMOVING SOME VERTICES
        myGraph.removeVertex(node3);
        myGraph.removeVertex(node4);
        myGraph.removeVertex(new Node(8)); // test removing vertex that does not exist in the graph

        System.out.println();
        myGraph.printGraph();
        System.out.println();

        // TEST REMOVING SOME EDGES
        myGraph.removeEdge(node1,node2);
        myGraph.removeEdge(node1,node4); // try removing an edge that does not exist

        System.out.println();
        myGraph.printGraph();
        System.out.println();

        // Example: undirected graph
        System.out.println("-----EXAMPLE: UNDIRECTED GRAPH-----");
        Graph myUndirectedGraph = new Graph(false);

        // TEST ADDING SOME VERTICES
        myUndirectedGraph.addVertex(node1);
        myUndirectedGraph.addVertex(node2);
        myUndirectedGraph.addVertex(node3);
        myUndirectedGraph.addVertex(node4);
        myUndirectedGraph.addVertex(node5);
        myUndirectedGraph.addVertex(node1); // try adding vertex that already exists

        System.out.println();
        myUndirectedGraph.printGraph();
        System.out.println();

        // TEST ADDING SOME EDGES
        myUndirectedGraph.addEdge(node1,node2);
        myUndirectedGraph.addEdge(node4,node3);
        myUndirectedGraph.addEdge(node5,new Node(11)); // purposefully test with value that doesn't exist in graph
        myUndirectedGraph.addEdge(node2,node5);
        myUndirectedGraph.addEdge(node1,node3);
        myUndirectedGraph.addEdge(node2,node5); // try adding edge that already exists

        System.out.println();
        myUndirectedGraph.printGraph();
        System.out.println();

        // TEST REMOVING SOME VERTICES
        myUndirectedGraph.removeVertex(node3);
        myUndirectedGraph.removeVertex(node4);
        myUndirectedGraph.removeVertex(new Node(0)); // test removing vertex that does not exist in the graph

        System.out.println();
        myUndirectedGraph.printGraph();
        System.out.println();

        // TEST REMOVING SOME EDGES
        myUndirectedGraph.removeEdge(node1,node2);
        myUndirectedGraph.removeEdge(node1,node4); // try removing an edge that does not exist

        System.out.println();
        myUndirectedGraph.printGraph();
        System.out.println();
    }


}