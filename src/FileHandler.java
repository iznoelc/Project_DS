import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

public class FileHandler<T> {

    public boolean exportGraph(DiWeGraph<Integer> graph) throws IOException {
        File myFile = new File("graph."+"graphName"+".txt");
        String path = System.getProperty("user.dir") + "\\src\\graph." + "graphName" +".txt";
        if (myFile.createNewFile()) {
            System.out.println("File created: " + myFile.getName());
        }
        else {
            System.out.println("File already exists.");
            return false;
        }

        //NOTE TO LANDON!!!
        //Changed node and edge <T> to <Integer> and that seems to have taken away teh errors but idk man, check it
        String text = "";
        text.join("===PRINTING GRAPH===\n");
        for (Node<Integer> vertex : graph.getGraph()){
            text.join("Vertex " + vertex.getValue());
            for (Edge<Integer> edge : vertex.getEdgeList()){
                text.join("    -> " + edge.getSink().getValue());
            }
            text.join("\n");
        }

        try {
            Files.write(Paths.get(path), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean exportCycle(Cycle<Integer> cycle) throws IOException {
        File myFile = new File("cycle."+"graphName"+".txt");
        String path = System.getProperty("user.dir") + "\\src\\cycle." + "graphName" +".txt";
        if (myFile.createNewFile()) {
            System.out.println("File created: " + myFile.getName());
        }
        else {
            System.out.println("File already exists.");
            return false;
        }

        String text = "";
        text.join("===PRINTING CYCLE===\n");
        if (cycle != null){
            text.join("Cycle found: ");
            for (Node cycleNode : cycle.getCycle()){
                text.join(cycleNode.getValue() + " -> ");
            }
            text.join(cycle.getCycle().getFirst().getValue().toString());
        } else {
            System.out.println("No cycle found.");
            return false;
        }

        try {
            Files.write(Paths.get(path), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

}


