public class DoublyLinkedList {
    private final Node head;
    private final Node tail;

    public DoublyLinkedList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(Node node){
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void moveFirst(Node node){
        remove(node);
        addFirst(node);
    }

    public Node removeLast(){
        if(tail.prev == head)
            return null;
        Node last = tail.prev;
        remove(last);
        return last;
    }
}
