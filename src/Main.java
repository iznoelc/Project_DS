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
                    System.out.println("Put checking path here");
                    break;
                case 3:
                    System.out.println("Put checking cycle here");
                    break;
                case 4:
                    System.out.println("doing something i dont remember");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Sorry I didn't understand that. Please try again.");
            }
        }

    }
}