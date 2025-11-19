import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

public class FileHandler {
/*
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

        String text = "";
        text.join("===PRINTING GRAPH===\n");
        for (Node<T> vertex : graph.getGraph()){
            text.join("Vertex " + vertex.getValue());
            for (Edge<T> edge : vertex.getEdgeList()){
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
 */

}


