
public class Main {
    public static void main(String[] args){
        //setup random generator
        //Note: it is predictable/will produce same random results as long as it has the same seed
        //change seed or bitNum = change results
        BlumBlumShub BBS = new BlumBlumShub();
        BBS.setUp(11, 19, 3);

        //make graph
        Graph<Integer> test = new DiWeGraph<>();

        //make nodes
        Node<Integer> node1 = new Node<>(BBS.nextByte(5));
        Node<Integer> node2 = new Node<>(BBS.nextByte(5));
        Node<Integer> node3 = new Node<>(BBS.nextByte(5));

        //make vertices
        test.addVertex(node1);
        test.addVertex(node2);
        test.addVertex(node3);

        //make edges
        test.addEdge(node1, node2, 125);
        test.addEdge(node1, node3, 100);
        test.addEdge(node2, node3, 150);
        test.addEdge(node3, node2, 150);

        // print graph
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