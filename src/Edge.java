public class Edge {
    private int weight;
    private Node source;
    private Node sink;

    public Edge(int weight, Node source, Node sink){
        this.weight = weight;
        this.source = source;
        this.sink = sink;
    }

    public int getWeight() {
        return weight;
    }

    public Node getSource(){
        return source;
    }

    public Node getSink(){
        return sink;
    }
}
