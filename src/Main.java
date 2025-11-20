
public class Main {
    public static void main(String[] args){
        //setup random generator
        //Note: it is predictable/will produce same random results as long as it has the same seed
        //change seed or bitNum = change results
        BlumBlumShub BBS = new BlumBlumShub();
        BBS.setUp(11, 19, -2);

        //make graph
        Graph<Integer> test = new DiWeGraph<>();

        //make nodes
        Node<Integer> node1 = new Node<>(BBS.nextByte(8));
        Node<Integer> node2 = new Node<>(BBS.nextByte(8));
        Node<Integer> node3 = new Node<>(BBS.nextByte(8));

        //make vertices
        test.addVertex(node1);
        test.addVertex(node2);
        test.addVertex(node3);

        //make edges
        test.addEdge(node1, node2, 125);
        test.addEdge(node1, node2, 125); //try to make same edge to show it won't work
        test.addEdge(node1, node3, 100);
        test.addEdge(node2, node3, 150);
        test.addEdge(node3, node2, 150);

        // print graph
        System.out.println("===");
        test.printGraph();
        System.out.println("===");

        //remove some edges
        test.removeEdge(node1, node2);
        //remove some vertex
        test.removeVertex(node2);

        // print graph
        System.out.println("===");
        test.printGraph();
        System.out.println("===");
    }
}