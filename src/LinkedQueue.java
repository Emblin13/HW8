public class LinkedQueue {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private Object data;
        private Node next;

        private Node(Object d) {
            this.data = d;
            this.next = null;
        }
    }

    public LinkedQueue() {
        this.head = new Node(null); //dummy head node
        this.tail = this.head;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(Object data) {
        Node newNode = new Node(data);
        this.tail.next = newNode;
        this.tail = newNode;
        this.size++;
    }

    public Object dequeue() throws Exception {
        if (this.size == 0) {
            throw new Exception("Queue is empty!");
        }
        Object temp = this.head.next.data; //stores data from the first node
        if (this.size == 1) {
            this.head.next = null;
            this.tail = this.head;
        }else {
            this.head.next = this.head.next.next;
        }
        this.size--;

        return temp;
    }

    public Object peek() throws Exception {
        if (this.size == 0) {
            throw new Exception("Queue is empty!");
        }
        return this.head.next.data;
    }

    @Override
    public String toString() {
        String result = "{";
        for (Node node = this.head.next; node != null; node = node.next) {
            if(node.next != null)
                result += node.data + "->";
            else
                result += node.data;
        }
        return result + "}";
    }

}
