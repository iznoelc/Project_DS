public class Node<T> {
    private T value;

    public Node(T value){
        this.value = value;
    }

    public T getValue(){ return this.value; }
}
