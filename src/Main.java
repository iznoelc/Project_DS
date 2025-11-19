
public class Main {
    public static void main(String[] args){
        Node<Integer> node1 = new Node<>(5);
        Node<Integer> node2 = new Node<>(6);

        Graph<Integer> test = new DiWeGraph<>();

        // add vertex 5 and 6
        test.addVertex(node1);
        test.addVertex(node2);

        // print edges of 5
        test.printEdges(node1);

        // add an edge from 5 to 6 with weight 125
        test.addEdge(node1, node2, 125);

        // print updated edges of 5
        test.printEdges(node1);

        // then print the graph
        System.out.println("===");
        test.printGraph();
        System.out.println("===");

        // try to add the same edge
        test.addEdge(node1, node2, 125);

        // print to make sure it wasnt able to do so
        test.printEdges(node1);

        // try removing edge from 5 to 6
        test.removeEdge(node1, node2);

        // check to make sure removed the edge from 5 to 6
        test.printEdges(node1);


//        // try removing vertex 2
//        test.removeVertex(node2);
//
//        // ensure vertex 2 was successfully removed from its connection to 5
//        test.printEdges(node1);
    }
}