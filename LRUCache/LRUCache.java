import java.util.HashMap;
import java.util.Map;

public class LRUCache<K,V> {
    private final Map<K, Node> map;
    private final DoublyLinkedList ddl;
    private final int capacity;


    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.ddl = new DoublyLinkedList();
        this.capacity = capacity;
    }

    public synchronized V get(K key){
        if(map.containsKey(key)){
            Node<K,V> node = map.get(key);
            ddl.moveFirst(node);
            return node.getValue();
        }else {
            return null;
        }
    }

    public synchronized void put(K key, V value){
        if(map.containsKey(key)){
            Node<K,V> old = map.get(key);
            old.setValue(value);
            ddl.moveFirst(old);
        }else{
            if(map.size() == capacity){
                Node n = ddl.removeLast();
                if(n != null)
                    map.remove(n.getKey());
            }
            Node newNode = new Node(key, value);
            ddl.addFirst(newNode);
            map.put(key, newNode);
        }
    }
    public synchronized void remove(K key){
        if(!map.containsKey(key))
            return;
        Node<K,V> node =  map.get(key);
        ddl.remove(node);
        map.remove(key);
    }
}
