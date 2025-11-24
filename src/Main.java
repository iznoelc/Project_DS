import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.random;

public class Main {
    private static final int MAX_NODES = 15;
    private static final int MIN_NODES = 3;

    public DiWeGraph<Integer> generateGraph(int extraRand){
        System.out.println("Generating a Random BlumBlumShub Graph...");
        BlumBlumShub BBS = new BlumBlumShub();
        ArrayList<Integer> nodes;
        Random random = new Random();

        int node_num = random.nextInt(MAX_NODES - MIN_NODES + 1)+ MIN_NODES;
        int nodeCount = node_num;

        BBS.setUp();
        DiWeGraph<Integer> graph = new DiWeGraph<>();

        nodes = BBS.buffer(node_num, extraRand);

        for(Integer node : nodes){
            System.out.println("Node " + node);
            graph.addVertex(new Node<Integer>(node));
            nodeCount -= 1;
        }

        for (Node<Integer> vertex : graph.getGraph()){
            for(int edge = 0; edge < MIN_NODES; edge++){
                Integer n1 = nodes.get((random.nextInt(node_num - MIN_NODES + 1) + MIN_NODES) - 1);
                Node<Integer> node1 = graph.findInputInGraph(n1);
                Integer n2 = nodes.get((random.nextInt(node_num - MIN_NODES + 1) + MIN_NODES) - 1);
                Node<Integer> node2 = graph.findInputInGraph(n2);
                graph.addEdge(node1,node2,BBS.nextByte(extraRand));
            }
        }

        // print graph
        System.out.println("\n==============================================================");
        graph.printGraph();
        System.out.println("================================================================\n");
        return graph;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        FileHandler<Integer> file = new FileHandler<>();
        DiWeGraph<Integer> graph = null;
        String graphFileName;

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Lets start by making a random graph!");
        graph = main.generateGraph(11);

        Integer number = 0;
        Node<Integer> nodenumber = new Node<Integer>(number);

        while(running == true){
            System.out.println("\nWould you like to: \n1. make a new graph\n2. get a path from graph\n3. get a cycle from graph\n4. exit this program");
            int input = scanner.nextInt();

            switch (input){
                case 1:
                    System.out.println("Exporting graph...");
                    System.out.println("What do you want to call your file? (Cannot overwrite so pick a new name if you already exported a graph): ");
                    graphFileName = scanner.next();
                    Cycle<Integer> cycle = new DirectedCycle<>();
                    cycle.findCycle(graph);
                    file.exportGraph(graphFileName, graph, cycle);
                    System.out.println("Please give me a number just to make the graph extra random.");
                    int extra = scanner.nextInt();
                    graph = main.generateGraph(extra);
                    break;
                case 2:
                    System.out.println("Start of Path? Number please.");
                    Integer start = scanner.nextInt();
                    Node<Integer> start_node = graph.findInputInGraph(start);
                    System.out.println("End of Path? Number please.");
                    Integer end = scanner.nextInt();
                    Node<Integer> end_node = graph.findInputInGraph(end);
                    System.out.println("Checking graph for path...");
                    Path<Integer> path = graph.findPath(start_node, end_node); //going to be using helper class once made to fix
                    if (path != null){
                        path.printPath();
                    }
                    break;
                case 3:
                    System.out.println("Checking graph for cycle...");
                    Cycle<Integer> cyclePrint = new DirectedCycle<>();
                    cyclePrint.findCycle(graph);
                    cyclePrint.printCycle();
                    break;
                case 4:
                    System.out.println("Exiting Program...");
                    return;
                default:
                    System.out.println("Invalid Entry. Please try again.");
            }
        }

    }
}