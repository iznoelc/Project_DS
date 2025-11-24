import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.random;

public class Main {
    public DiWeGraph<Integer> generateGraph(){
        System.out.println("Generating a Random BlumBlumShub Graph...");
        BlumBlumShub BBS = new BlumBlumShub();
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        Random random = new Random();
        int max = 15;
        int min = 3;
        Integer node_num = random.nextInt(max-min+1)+min;
        System.out.println("node num = " + node_num);
        BBS.setUp();
        //make graph
        DiWeGraph<Integer> graph = new DiWeGraph<>();
        //make nodes

        while(node_num>0){
            //System.out.println(extraRand);
            Integer next = BBS.nextByte();
            graph.addVertex(new Node<Integer>(next));
            nodes.add(next);
            node_num-=1;
        }
        //make vertices
        for(Integer vertex: nodes){
            Node<Integer> node = graph.findInputInGraph(vertex);
            graph.addVertex(node);
        }
        //make edges
        int Max = nodes.size() - 1;
        int edge_num = min;

        for(int edge = 0;edge < edge_num;edge++){
            Integer n1 = nodes.get(random.nextInt(max-min+1)+min);
            Node node1 = graph.findInputInGraph(n1);
            Integer n2 = nodes.get(random.nextInt(max-min+1)+min);
            Node node2 = graph.findInputInGraph(n2);
            graph.addEdge(node1,node2,BBS.nextByte());
        }
        // print graph
        System.out.println("\n==============================================================");
        graph.printGraph();
        System.out.println("================================================================\n");
        return graph;
    }

    public static void main(String[] args){
        Main main = new Main();
        FileHandler file = new FileHandler();
        DiWeGraph<Integer> graph = null;

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Lets start by making a random graph!");
        graph = main.generateGraph();

        Integer number = 0;
        Node nodenumber = new Node<Integer>(number);

        while(running==true){
            System.out.println("\nWould you like to: \n1. make a new graph\n2. get a path from graph\n3. get a cycle from graph\n4. exit this program");
            int input = scanner.nextInt();

            switch (input){
                case 1:
                    System.out.println("Please give me a number just to make the graph extra random.");
                    int extra = scanner.nextInt();
                    graph = main.generateGraph();
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
                    Cycle cycle = new DirectedCycle();
                    cycle.findCycle(graph);
                    cycle.printCycle();
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