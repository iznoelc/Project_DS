public class Edge<T> {
    private int weight;
    private Node<T> source;
    private Node<T> sink;

    public Edge(int weight, Node<T> source, Node<T> sink){
        this.weight = weight;
        this.source = source;
        this.sink = sink;
    }

    public int getWeight() {
        return weight;
    }

    public Node<T> getSource(){
        return source;
    }

    public Node<T> getSink(){
        return sink;
    }
}
