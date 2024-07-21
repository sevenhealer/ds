import java.util.Iterator;

public class DoublyLinkedList implements Iterable<Integer>{
    int size = 0;
    Node head = null;
    Node tail = null;

    private class Node{
        int data;
        Node prev;
        Node next;

        private Node(int data , Node prev , Node next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private void clear() throws Exception{
        if(head == null) throw new Exception("Empty List");
        Node trav = head;
        while(trav != null){
            Node next = trav.next;
            trav.prev = trav.next = null;
            trav = next;
        }
        head = tail = null;
        size = 0;
    }

    private int size(){
        return size;
    }

    private boolean isEmpty(){
        return size() == 0;
    }

    private void add(int element){
        addLast(element);
    }

    private void addLast(int element){
        if(isEmpty())
        head = tail = new Node(element, null, null);
        else{
        tail.next = new Node(element, tail, null);
        tail = tail.next;
        }
        size++;
    }

    private void addFirst(int element){
        if(isEmpty())
        head = tail = new Node(element, null, null);
        else{
        head.prev = new Node(element, null, head);
        head = head.prev;
        }
        size++;
    }

    private void addAt(int index , int data) throws Exception{
        if(index < 0 && index >= size) throw new Exception("Illegal Index");
        if(index == 0){
            addFirst(data);
            return;
        }
        if(index == size){
            addLast(data);
            return;
        }
        Node trav = head;
        for(int i = 0 ; i < index - 1 ; i++){
            trav = trav.next;
        }
        Node newNode = new Node(data, trav, trav.next);
        trav.next = newNode;
        newNode.next.prev = newNode;

        size++;
    }

    private int peekFirst(){
        if(isEmpty()) throw new RuntimeException("Empty List");
        return head.data;
    }

    private int peekLast(){
        if(isEmpty()) throw new RuntimeException("Empty List");
        return tail.data;
    }

    private int removeFirst(){
        if(isEmpty()) throw new RuntimeException("Empty List");
        int data = head.data;
        head = head.next;
        if(head == null) tail = null;
        else{
            head.prev = null;
        }
        size--;
        return data;
    }

    private int removeLast(){
        if(isEmpty()) throw new RuntimeException("Empty List");
        int data = tail.data;
        tail = tail.prev;
        if(tail == null) head = null;
        else{
        tail.next = null;
        }
        size--;
        return data;
    }

    private int remove(Node node) {
        if (node == null) throw new NullPointerException("Node cannot be null");
    
        if (node.prev == null) return removeFirst();
        if (node.next == null) return removeLast();
    
        node.prev.next = node.next;
        node.next.prev = node.prev;
    
        int data = node.data;
    
        node.prev = node.next = null; // Clearing references
        size--;
    
        return data;
    }

    private int removeAt(int index){
        if(index < 0 || index > size) throw new IllegalArgumentException();
        if(index == 0) return removeFirst();
        if(index == size-1) return removeLast();
        
        Node trav = head;
        for (int i = 0; i < index; i++) {
            trav = trav.next;
        }
        return remove(trav);
    }
    
    public boolean remove(Object obj) {
        Node trav = head;

            if (obj == null) {
          for (trav = head; trav != null; trav = trav.next) {
            if (trav.data == (Integer)null) {
              remove(trav);
              return true;
            }
          }
        } else {
          for (trav = head; trav != null; trav = trav.next) {
            if (obj.equals(trav.data)) {
              remove(trav);
              return true;
            }
          }
        }
        return false;
      }

      public int indexOf(Object obj) {
        int index = 0;
        Node trav = head;
    
        if (obj == null) {
          for (; trav != null; trav = trav.next, index++) {
            if (trav.data == (Integer)null) {
              return index;
            }
          }
        } else {
          for (; trav != null; trav = trav.next, index++) {
            if (obj.equals(trav.data)) {
              return index;
            }
          }
        }
        return -1;
      }

      public boolean contains(Object obj) {
        return indexOf(obj) != -1;
      }

    @Override
    public Iterator<Integer> iterator(){
        return new Iterator<Integer>() {
            private Node current = head;
            @Override
            public boolean hasNext(){
                return current != null;
            }
            @Override
            public Integer next(){
                int data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("[ ");
      Node trav = head;
      while (trav != null) {
        sb.append(trav.data);
        if (trav.next != null) {
          sb.append(", ");
        }
        trav = trav.next;
      }
      sb.append(" ]");
      return sb.toString();
    }

        public static void main(String[] args) throws Exception {
            DoublyLinkedList dll = new DoublyLinkedList();
            // Node n1 = dll.new Node(0, null, null);
            // dll.head = n1;
            // Node n2 = dll.new Node(1,n1,null);
            // n1.next = n2;
            // dll.tail = n2;
            // dll.size = 2;
            // System.out.println("Head Data : " + n.prev.data);
            // System.out.println("Tail Data : " + n.next.data);
            dll.add(1);
            dll.add(2);
            dll.add(4);
            dll.add(5);
            dll.addFirst(0);
            dll.addAt(3, 3);
            System.out.println("Size : " + dll.size());
            System.err.print("Values : ");
            Iterator it = dll.iterator();
            while(it.hasNext()){
                System.out.print(it.next() + " ");
            }
            System.out.println("");
            System.out.println("First Element : " + dll.peekFirst());
            System.out.println("Last Element : " + dll.peekLast());
            // dll.clear();
            dll.removeFirst();
            dll.removeLast();
            // dll.removeLast();
            System.out.println("Removed : " + dll.remove(dll.tail));
            System.out.println("Removed : " + dll.removeAt(2));
            System.out.println("Size : " + dll.size());
            // dll.remove(2);
            System.err.println("Index : " + dll.indexOf(1));
            System.err.println("Contains : " + dll.contains(3));
            Iterator it1 = dll.iterator();
            while(it1.hasNext()){
                System.out.println(it1.next());
            }
            System.err.println(dll.toString());
        }
}
