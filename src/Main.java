public class Main {

    public static void main (String[] args) throws Exception {
        CDoublyLinkedList ll = new CDoublyLinkedList();

        //initialize list
        ll.addFirst(3);
        ll.addFirst(1);
        ll.addFirst(8);
        ll.addFirst(13);
        ll.addFirst(12);
        ll.addFirst(13);
        ll.addFirst(0);
        ll.addFirst(6);
        ll.addFirst(1);
        ll.addFirst(9);

        ll.mergeSort();

    }

}
