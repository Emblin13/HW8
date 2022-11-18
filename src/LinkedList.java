//Brendan Kendall
//9-29-2022

public class LinkedList {
	
	private ListNode head;
	private int size;
	
	//inner class for ListNode
	private class ListNode {
		private Object data;
		private ListNode next;
		private ListNode(Object d) {
			this.data = d;
			this.next = null;
		}
	}
	
	public LinkedList() {
		this.head = new ListNode(null); //with a dummy head node
		this.size = 0;
	}
	
	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// Add Object e to start of this LinkedList
	public void addFirst(Object e)
	{
		ListNode node = new ListNode(e);
		node.next = head.next;
		head.next = node;
		size++;
	}
	
	// Remove(cut out) the first data node(the node succeeding the dummy node)
	//       in this list, then returns the data in the node removed.
	public Object removeFirst() throws Exception {
		if (this.size == 0) {
			throw new Exception("LinkedList is empty!");
		}
		Object temp = this.head.next.data; //stores data from the first node
		if (this.size == 1) {
			this.head.next = null;
		}else {
			this.head.next = this.head.next.next;
		}
		this.size--;

		return temp;
	}
	
	// Returns true if this list contains the specified element o.
	public boolean contains(Object o) {
		ListNode prev = this.head, cur = this.head.next;
		int i = 0;
		while(i < size) {
			if(cur.data == null && o == null) { //Safely checks data without causing a NullPointerException
				return true;
			}
			if(cur.data.equals(o)) {
				return true;
			}
			prev = cur;
			cur = cur.next;
			i++;
		}
		return false; //Linked list does not contain data equal to Object o
	}
	
	// Removes the first occurrence of the specified element o from this list and returns true, if it is present. 
	// If this list does not contain the element o, it is unchanged and the method returns false.
	public boolean remove(Object o) {
		ListNode prev = this.head, cur = this.head.next;
		int i = 0;
		while(i < size) {
			if(cur.data == null && o == null) { //Safely removes null data without causing a NullPointerException
				prev.next = cur.next;
				this.size--;
				return true;
			}
			if(cur.data.equals(o)) {
				prev.next = cur.next;
				this.size--;
				return true;
			}
			prev = cur;
			cur = cur.next;
			i++;
		}
		return false; //no data was removed
	}

	// Removes all copies of o from this linked list.
	public boolean removeAllCopies(Object o) { //passed test
		boolean dataWasRemoved = this.remove(o);
		while(this.remove(o)){
			//empty body
		}
		return dataWasRemoved;
	}
	
	// Insert data elements from linkedlist A and B alternately into 
	//    a new linkedlist C, then returns C.
	// Note: after this method is called, both list A and B are UNCHANGED.
	public static LinkedList interleave(LinkedList A, LinkedList B) {
		LinkedList C = new LinkedList();
		ListNode curA = A.head.next, curB = B.head.next;

		while(curA != null || curB != null) { //While loops continues until both A and B have been fully walked through
			//Checks if list A still has a new element to add. If it does, it adds that element to C,
			//	then walks list A forwards. Same logic goes for the next if-statement
			if(curA != null) {
				C.add(curA.data);
				curA = curA.next;
			}
			if(curB != null) {
				C.add(curB.data);
				curB = curB.next;
			}
		}
		return C; //change this as you need.
	}
	
	// Inserts the specified element at the specified position in this list. 
	// Shifts the element currently at that position (if any) and any subsequent
	//     elements to the right (adds one to their indices).
	public void add(int index, Object o) {
		if(index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException("Index passed in is not valid!");
		}
		ListNode cur = this.head;
		int i = 0;
		while(i < index) { //walks up the linked list until it reaches the index
			cur = cur.next;
			i ++;
		}
		ListNode nn = new ListNode(o);
		nn.next = cur.next; //points nn at the node which is currently at the index
		cur.next = nn; //points the node left of the index at nn
		this.size ++;

	}

	// Returns the element at the specified index in this list.
	public Object get(int index) throws IndexOutOfBoundsException{
		if(index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Provided index is out of bounds!");
		}
		ListNode cur = this.head.next;

		int i = 0;
		while(i < index) { //walks up the linked list until it reaches the index
			cur = cur.next;
			i ++;
		}
		return cur.data;
	}
	
	// Removes (cuts out) the list node at the specified index in this list. 
	// Returns the data element in the node that is removed.
	public Object remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Provided index is out of bounds!");
		}
		ListNode prev = this.head, cur = this.head.next;

		int i = 0;
		while(i < index) { //walks up the linked list until it reaches the index
			prev = cur;
			cur = cur.next;
			i ++;
		}

		Object dataRemoved = cur.data; //Stores the value of the current node that will be removed
		prev.next = cur.next;
		this.size--;

		return dataRemoved;
	}
	
	//Add the object e to the end of this list.
	// it returns true, after e is successfully added.
	public boolean add(Object e) {
		ListNode cur = this.head;
		ListNode nn = new ListNode(e);

		int i = 0;
		while(i < size) {
			cur = cur.next;
			i++;
		}
		cur.next = nn;
		this.size++;

		return true; //change this as you need.
	}
	
        //Please DO NOT Change the following toString() method!!!
        //You must include the following toString() method in your source code.
	@Override
	public String toString() {
		String result = "{";
	    for (ListNode node = this.head.next; node != null; node = node.next) {
	    		if(node.next != null)
	    			result += node.data + "->"; 
	    		else
	    			result += node.data;
	    }
	    return result + "}";
	  }
}
