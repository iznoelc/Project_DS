package Exporting;

import Graph.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileHandler<T> {


    /**
     * Takes a graph as an input and creates a text file
     * in the source folder of the graph.
     *
     * Text file name follows the scheme of graph.graphName.txt
     *
     * @param graph
     * @return boolean
     * @throws IOException
     */
    public boolean exportGraph(String fileName, DiWeGraph<T> graph, Cycle<T> cycle, ArrayList<Path<T>> paths) throws IOException {
        File myFile = new File("src//" + "graph." + fileName + ".txt");
        String path = System.getProperty("user.dir") + "\\src\\graph." + fileName + ".txt";
        if (myFile.createNewFile()) {
            System.out.println("File created: " + myFile.getName());
        }
        else {
            System.out.println("File already exists.");
            return false;
        }

        StringBuilder text = new StringBuilder();
        text.append("===GRAPH===\n");
        for (Node<T> vertex : graph.getGraph()){
            text.append("Vertex " + vertex.getValue());
            for (Edge<T> edge : vertex.getEdgeList()){
                text.append("    -> " + edge.getSink().getValue());
            }
            text.append("\n");
        }

        //StringBuilder text = new StringBuilder();
        text.append("===CYCLE===\n");
        if (cycle != null){
            text.append("Cycle found: ");
            for (Node cycleNode : cycle.getCycle()){
                text.append(cycleNode.getValue() + " -> ");

            }
            text.append("\n");
            //text.append(cycle.getCycle().getFirst().getValue().toString());
        } else {
            System.out.println("No cycle found.\n");
            //return false;
        }

        text.append("===USER GENERATED PATHS===\n");
        if (!paths.isEmpty()){
            for (Path<T> p : paths){
                for (Node<T> n : p.getPath()){
                    text.append(n.getValue() + " -> ");
                }
                text.append("\n\n");
            }
        } else {
            text.append("User did not generate any paths.\n");
        }

        try {
            Files.writeString(Paths.get(path), text.toString(), StandardOpenOption.APPEND);
            System.out.println("Cycle exported to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }

        return true;
    }
}


