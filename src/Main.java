import java.util.Scanner;

public class Main {
    public void generateGraph(int extraRand){
        System.out.println("Generating a Random BlumBlumShub Graph...");
        BlumBlumShub BBS = new BlumBlumShub();
        BBS.setUp();
        //make graph
        Graph<Integer> graph = new DiWeGraph<>();
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
    }

    public static void main(String[] args){
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running==true){
            System.out.println("\nWould you like to: \n1. make a graph\n2. check a path\n3. check a cycle\n4.?\n5. exit the program");
            int input = scanner.nextInt();

            switch (input){
                case 1:
                    System.out.println("Please give me a number just to make the graph extra random.");
                    int extra = scanner.nextInt();
                    main.generateGraph(extra);
                    break;
                case 2:
                    // add a check so that if the user enters a path that is a cycle
                    // (i.e. i want to find a path from 1 to 1!) try to find a cycle instead, as
                    // path method does NOT find cycles
                    System.out.println("Checking graph for path...");
                    break;
                case 3:
                    System.out.println("Checking graph for cycle...");
                    break;
                case 4:
                    System.out.println("Exporting graph...");
                    break;
                case 5:
                    System.out.println("Exiting Program...");
                    return;
                default:
                    System.out.println("Invalid Entry. Please try again.");
            }
        }

    }
}