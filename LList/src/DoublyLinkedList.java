
public class DoublyLinkedList<T> extends LinkedList<T> {

	/* Our goal here is to make a class that allows us to
	 * find the prior element of the list, without having
	 * to iterate through the list multiple times.  This is
	 * helpful in situations where we want to iterate over
	 * a list in reverse order.  In practice, linked lists
	 * are almost always doubly linked.  
	 */
	
	/* We don't need to override the default constructor. It
	 * already does everything we need.  
	*/
	
	/* The add method needs to work with DoublyLinkedNodes
	 * instead of singly linked ones, so we need to override it.
	 */
	public void add(T o) {
		DoublyLinkedNode<T> n = new DoublyLinkedNode<T>(o);
		n.setPrevious((DoublyLinkedNode<T>)getLast());
		super.addN(n);
	}
	
	/* We also need to override the remove method, since 
	 * we need to change the reverse pointers around. 
	 */
	public T remove(int index) {
		DoublyLinkedNode<T> n = (DoublyLinkedNode<T>) removeN(index);
		DoublyLinkedNode<T> prev = n.previous();
		DoublyLinkedNode<T> next = n.next();
		if(next != null) next.setPrevious(prev);
		return n.getInfo();
	}
	
	/* To achieve our goal of supporting reverse iteration,
	 * we add getR and getNthFromEnd methods that gets the nth element 
	 * from the end of the list.
	 */
	protected DoublyLinkedNode<T> getR(int index) {
		DoublyLinkedNode<T> n = (DoublyLinkedNode<T>) getLast();
		for(int i=0; i<index; i++) {
			if(n.previous() == null) throw new IndexOutOfBoundsException();
			else n = n.previous();
		}
		return n;
	}
	
	public T getNthFromEnd(int index) {
		return getR(index).getInfo();
	}
	
	/* For efficiency, we also override the getN method.  This 
	 * unncessary from a functionality perspective (the LinkedList's
	 * method works perfectly well), but now that we have a getR method
	 * we can decide whether to access elements from either the beginning
	 * or the end of the list.  Even though we don't override the get method
	 * our sped up getN method will be used when get invokes getN in the 
	 * code inheritted from the LinkedList class.  This method also demonstrtes
	 * how to use super to call a super class's methods.  
	 */
	protected Node<T> getN(int index) {
		int size = size();
		if(index > size/2) {
			int rIndex = size - 1- index;
			return getR(rIndex);
		} else {
			return super.getN(index);
		}
	}
	
}
