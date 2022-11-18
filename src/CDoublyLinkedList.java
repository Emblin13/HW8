//CSCD 300
//10/10/2022

public class CDoublyLinkedList {

	private class Node {
		private Object data;   //Assume data implemented Comparable
		private Node next, prev;
		private Node(Object data, Node pref, Node next)
		{
			this.data = data;
			this.prev = pref;
			this.next = next;
		}
	}

	private Node head;
	private int size;

	public CDoublyLinkedList() {
		this.head = new Node(null, null, null );
		this.head.next = this.head;
		this.head.prev=this.head;
		this.size = 0;
	}

	public boolean isEmpty() {
		return this.head == this.head.next;
	}

	public void addFirst(Object data) {
		Node nn = new Node(data, this.head, this.head.next);
		this.head.next.prev = nn;
		this.head.next = nn;
		this.size ++;
	}

	public void addLast(Object data) {
		Node cur = this.head.prev; //end of list
		Node nn = new Node(data, cur, cur.next);
		cur.next = nn;
		this.head.prev = nn;
		this.size++;
	}

	public void removeFirst() throws Exception {
		if(this.size == 0) {
			throw new Exception("List is empty!");
		}
		else if(this.size == 1) {
			this.head.next = this.head;
			this.head.prev = this.head;
		} else {
			this.head.next = this.head.next.next;
		}
		this.size--;
	}

	// Returns the element at the specified index in this list.
	public Object get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Provided index is out of bounds!");
		}
		Node cur = this.head.next;

		int i = 0;
		while(i < index) { //walks up the linked list until it reaches the index
			cur = cur.next;
			i ++;
		}
		return cur.data;
	}

	public void insertionSort() { //Does not work for lists containing null data
		//sortedCur starts at the end of the sorted region, and unsortedCur starts at the beginning of unsorted region
		Node sortedCur = this.head.next, unsortedCur = this.head.next.next;
		while (unsortedCur != this.head) {
			while ( sortedCur != this.head && ((Comparable) sortedCur.data).compareTo(unsortedCur.data) > 0 ) {
				//Continue walking sortedCur back until sortedCur is either the head node or is smaller than unsortedCur
				sortedCur = sortedCur.prev;
			}
			//cut unsortedCur and insert it in front of sortedCur
			unsortedCur.prev.next = unsortedCur.next;
			unsortedCur.next.prev = unsortedCur.prev;
			Node nn = new Node(unsortedCur.data, sortedCur, sortedCur.next);
			sortedCur.next.prev = nn;
			sortedCur.next = nn;

			//move unsortedCur to the next First Unsorted Data, and move sortedCur to the left of FUD
			unsortedCur = unsortedCur.next;
			sortedCur = unsortedCur.prev;
		}
	}

	public void mergeSort() throws Exception {
		LinkedQueue q = new LinkedQueue();
		CDoublyLinkedList sublist1 = new CDoublyLinkedList();
		CDoublyLinkedList sublist2 = new CDoublyLinkedList();
		CDoublyLinkedList tempList;

		//Split ll into 1-element linked lists and add them to the queue
		for(Node cur = this.head.next; cur != this.head; cur = cur.next) {
			CDoublyLinkedList newList = new CDoublyLinkedList();
			newList.addFirst(cur.data);
			q.enqueue(newList);
		}
		System.out.println(q);

		while(q.size() > 1) {
			sublist1.addLast(q.dequeue());
			sublist2.addLast(q.dequeue());
			tempList = merge(sublist1, sublist2);
			System.out.println("Queue: " + q);
			q.enqueue(tempList);
		}
		tempList = (CDoublyLinkedList) q.dequeue();
		System.out.println(tempList);
		this.head = tempList.head;
	}

	public CDoublyLinkedList merge(CDoublyLinkedList sublist1, CDoublyLinkedList sublist2) throws Exception {
		CDoublyLinkedList tempList = new CDoublyLinkedList();
		Object first1, first2;

		while (!sublist1.isEmpty() && !sublist2.isEmpty()) {
			first1 = sublist1.get(0);
			first2 = sublist2.get(0);

			if (((Comparable)((CDoublyLinkedList)first1).get(0)).compareTo(((CDoublyLinkedList)first2).get(0)) < 0) {
				sublist1.removeFirst();
				//tempList.addLast(first1);
				for(Node cur = ((CDoublyLinkedList) first1).head.next; cur != ((CDoublyLinkedList) first1).head; cur = cur.next) {
					tempList.addLast(cur.data);
				}
			} else {
				sublist2.removeFirst();
				//tempList.addLast(first2);
				for(Node cur = ((CDoublyLinkedList) first2).head.next; cur != ((CDoublyLinkedList) first2).head; cur = cur.next) {
					tempList.addLast(cur.data);
				}
			}
			//System.out.println("Templist: " + tempList);
		}
		while (!sublist1.isEmpty()) {
			first1 = sublist1.get(0);
			sublist1.removeFirst();
			//tempList.addLast(first1);
			for(Node cur = ((CDoublyLinkedList) first1).head.next; cur != ((CDoublyLinkedList) first1).head; cur = cur.next) {
				tempList.addLast(cur.data);
			}
		}
		while (!sublist2.isEmpty()) {
			first2 = sublist2.get(0);
			sublist2.removeFirst();
			//tempList.addLast(first2);
			for(Node cur = ((CDoublyLinkedList) first2).head.next; cur != ((CDoublyLinkedList) first2).head; cur = cur.next) {
				tempList.addLast(cur.data);
			}
		}

		return tempList;
	}

	@Override
	public String toString() {
		String result = "{";
	    for (Node node = this.head.next; node != this.head; node = node.next) {
	    		if(node.next != this.head)
	    			result += node.data + "->"; 
	    		else
	    			result += node.data;
	    }
	    return result + "}";
	  }
}
			