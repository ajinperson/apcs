
public class GArrayList implements APList {

	/* The GArrayList class is a naive implementation
	 * of the APList interface that uses arrays.  The underlying
	 * use of arrays makes it very expensive to add or remove
	 * elements from the list.  But accessing elements is very
	 * fast.  
	 */
	
	private Object[] data;
	
	/* Our default constructor initializes our private
	 * instance variable.  
	 */
	public GArrayList() {
		data = new Object[0];
	}
	
	/* To get an element, we simply look at the specified
	 * location in our data.  
	 */
	public Object get(int index) {
		return data[index];
	}
	
	/* Resizing our underlying array is painful. We have to copy
	 * over elements from our old array to a new one, and then we
	 * update our instance variable.  
	 */
	
	public void add(Object o) {
		Object[] newList = new Object[data.length + 1];
		for(int i=0; i<data.length; i++) {
			newList[i] = data[i];
		}
		newList[data.length] = o;
		data = newList;
	}
	
	public Object remove(int index) {
		Object o = data[index];
		Object[] newList = new Object[data.length-1];
		for(int i=0; i<index; i++) {
			newList[i] = data[i];
		}
		for(int i=index+1; i<data.length; i++) {
			newList[i-1] = data[i];
		}
		data = newList;
		return o;
	}
	
}
