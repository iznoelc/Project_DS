import java.io.IOException;
import java.util.Scanner;

public class Main {
    public DiWeGraph<Integer> generateGraph(int extraRand){
        System.out.println("Generating a Random BlumBlumShub Graph...");
        BlumBlumShub BBS = new BlumBlumShub();
        BBS.setUp();
        //make graph
        DiWeGraph<Integer> graph = new DiWeGraph<>();
        //make nodes
        Node<Integer> node1 = new Node<>(BBS.nextByte(2+extraRand));
        Node<Integer> node2 = new Node<>(BBS.nextByte(1+extraRand));
        Node<Integer> node3 = new Node<>(BBS.nextByte(extraRand));
        Node<Integer> node4 = new Node<>(BBS.nextByte(-1+extraRand));
        Node<Integer> node5 = new Node<>(BBS.nextByte(-2+extraRand));
        //make vertices
        graph.addVertex(node1);
        graph.addVertex(node2);
        graph.addVertex(node3);
        graph.addVertex(node4);
        graph.addVertex(node5);
        //make edges
        graph.addEdge(node1, node2, BBS.nextByte(1+extraRand));
        graph.addEdge(node1, node3, -2+extraRand);
        graph.addEdge(node2, node3, 2+extraRand);
        graph.addEdge(node3, node2, -2+extraRand);
        // print graph
        System.out.println("\n==============================================================");
        graph.printGraph();
        System.out.println("================================================================\n");
        return graph;
    }

    public static void main(String[] args){
        Main main = new Main();
        Path path = new Path();
        Cycle cycle = null;
        FileHandler file = new FileHandler();
        DiWeGraph<Integer> graph = null;

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Lets start by making a random graph!");
        graph = main.generateGraph(1);

        while(running==true){
            System.out.println("\nWould you like to: \n1. make a new graph\n2. get a path from graph\n3. get a cycle from graph\n4. exit this program");
            int input = scanner.nextInt();

            switch (input){
                case 1:
                    System.out.println("Please give me a number just to make the graph extra random.");
                    int extra = scanner.nextInt();
                    graph = main.generateGraph(extra);
                    break;
                case 2:
                    System.out.println("Checking graph for path...");
                    break;
                case 3:
                    System.out.println("Checking graph for cycle...");
                    break;
                case 6:
                    System.out.println("Exiting Program...");
                    return;
                default:
                    System.out.println("Invalid Entry. Please try again.");
            }
        }

    }
}