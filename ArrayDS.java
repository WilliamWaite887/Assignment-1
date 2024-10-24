public class ArrayDS<T extends Comparable<? super T>> implements SequenceInterface<T>, ReorderInterface, Comparable<ArrayDS<T>> {


	private T[] data;
	private int size;
	private static final int capacity = 10;

	 public ArrayDS() {
		data = (T[]) new Comparable[capacity];
        size = 0;
    }
	// Copy Constructor
public ArrayDS(ArrayDS<T> other) {
    data = (T[]) new Comparable[other.data.length];
    size = other.size;
    for (int i = 0; i < other.size; i++) {
        data[i] = other.data[i];
    }
}

	@Override
	public int compareTo(ArrayDS<T> other) {
		return Integer.compare(this.size(), other.size());
	}
    	/** Logically reverse the data in the ReorderInterface object so that the item
	 * that was logically first will now be logically last and vice
	 * versa.  The physical implementation of this can be done in
	 * many different ways.
	 */
	@Override public void reverse(){
		int lower = 0;
		int upper = size-1;

		while (lower < upper){
			T temporary = data[lower];
			data[lower] = data[upper];
			data[upper] = temporary;
			lower++;
			upper--;
		}
    }

	/** Remove the logically last item and put it at the
	 * front.  As with reverse(), this can be done physically in
	 * different ways depending on the underlying implementation.
	 */
	@Override public void rotateRight(){
		T last = data[size-1];
		for (int i=size-1; i>0; i--){ // shifting right
			data[i] = data[i-1];
		}
		data[0] = last;
    }

	/** Remove the logically first item and put it at the
	 * end.  As above, this can be done in different ways.
	 */
	@Override public void rotateLeft(){
		T first = data[0];
		for (int i= 0; i < size; i++){
			data[i] = data[i+1];
		}
		data[size-1] = first;
    }

	/** (EXTRA CREDIT) Shuffle the items according to the given permutation. The permutation is defined using two parallel arrays, one containing
	 * the original positions and the other containing the new positions. For example, if oldPositions[0] contains 5 then newPositions[0] contains the
	 * new position for the item at position 5.
	 * 
	 * @param oldPositions the int[] array of old positions
	 * @param newPositions the int[] array of new positions
	 * @throws IndexOutOfBoundsException if any of the old or new positions is < 0 or > size-1
	 * @throws IllegalArgumentException if oldPositions.length != newPositions.length or if there are duplicate values in either oldPositions or newPositions
	 */
	@Override public void shuffle(int[] oldPositions, int[] newPositions){

    }
    	/** Add a new item to the tail (logical end) of the SequenceInterface<T>
	 * @param item the item to be added.
	 */
	@Override public void append(T item){
		if (size == data.length){
			T [] newArray = (T[]) new Comparable[data.length * 2];
			System.arraycopy(data, 0, newArray, 0, data.length);
        	data = newArray;
		}
		data[size++] = item;

    }

	/** Add a new item to the head (logical beginning) of the SequenceInterface<T>
	 * @param item the item to be added.
	 */
	@Override public void prefix(T item){
		if (size == data.length){
			T [] newArray = (T[]) new Comparable[data.length * 2];
			System.arraycopy(data, 0, newArray, 0, data.length);
			data = newArray;
		}
		for (int i=size; i>0; i--){
			data[i] = data[i-1];
		}
		data[0] = item;
		size++;
    }

	/** Add a new item at a given position in the SequenceInterface<T>. Inserting at position size() is 
	 * the same as appending. All existing items in the SequenceInterface<T> should maintain their relative order.
	 * @param item the T item to be added.
	 * @param position the int position of the added item
	 * @throws IndexOutOfBoundsException if position < 0
	                                     or position > size()
	 */
	@Override public void insert(T item, int position){
		if (position > size || position < 0){
		throw new IndexOutOfBoundsException("out of bounds");
	}
	for (int i = size; i > position; i--){
		data[i] = data[i-1];

	}
	data[position] = item;
	size++;
    }

	/** Return the item at a given logical position in the SequenceInterface<T>
	 * @param position the int logical position
	 * @return the T item at position
	 * @throws IndexOutOfBoundsException if position < 0
	                                     or position > size()-1
	 */
	@Override public T itemAt(int position){
		if (position > size-1 || position < 0){
			throw new IndexOutOfBoundsException("out of bounds");
		}
		return data[position]; // returns item at the position

    }

	/**
	 * @return true if the SequenceInterface<T> is empty, and false otherwise
	 */
	@Override public boolean isEmpty(){
		if (size == 0){
			return true;
		}
	return false;

    }

	/**
	 * @return the number of items currently in the SequenceInterface<T>
	 */
	@Override public int size(){
		return size;
		
    }

	/**
	 * @return the logical first item in the SequenceInterface<T> or null if the SequenceInterface<T> is empty
	 */
	@Override public T first(){
		if (isEmpty()){
			return null;
		}
		return data[0];
    }

	/**
	 * @return the logical last item in the SequenceInterface<T> or null if the SequenceInterface<T> is empty,
	 */
	@Override public T last(){
        if (isEmpty()){
			return null;
		}
		return data[size-1];
    }
	/** Find any predecessor (i.e., item right before) of a given item in the SequenceInterface<T>
	 * @param item the T item
	 * @return a T item that is right before item in the SequenceInterface<T>. If item occurs multiple times in the 
	 *         SequenceInterface<T>, any non-null predecessor is returned. If item doesn't exist or occurs once at the head
	 *         of the SequenceInterface<T>, null is returned.
	 */
	@Override public T predecessor(T item){
		for (int i=size-1; i>= size; i--){
			if (data[i].equals(item)) {
				if (i>0){
				return data[i-1];
			} else {
				return null;
			}
		}
	}
	return null;
}

	/** Return the number of occurrences in the SequenceInterface<T> of a given item
	 * @param item the T item
	 * @return the number of occurrences in the SequenceInterface<T> of item
	 */
	@Override public int getFrequencyOf(T item){
		int count = 0;
		for (int i = 0; i < size; i++){
			if (data[i].equals(item)){
				count++;
			}
		}
        return count;

    }

	/** Reset the SequenceInterface<T> to an empty Sequence.
	 */
	@Override public void clear(){
		data = (T[]) new Comparable[capacity]; 
			size = 0;

    }

	/** Return the logical position in the SequenceInterface<T> of the last occurrence of a given item
	 * @param item an item
	 * @return the int logical position in the SequenceInterface<T> of the last occurrence of item
	 *         or -1 if item doesn't exist in the SequenceInterface<T>
	 */
	@Override public int lastOccurrenceOf(T item){
        for (int i = size-1; i >= 0; i--){
			if (data[i] == item){
				return i;
			}
		}
		
		return -1;

    }


	/** Delete the first item of the SequenceInterface<T>
	* @return the deleted item
	* @throws EmptySequenceException if the SequenceInterface<T> is empty
	*/
	@Override public T deleteHead(){
		if (isEmpty()){
			throw new EmptySequenceException("empty sequence");
		}
		T item = data[0];
		for (int i=0; i <size-1; i++){
			data[i] = data[i +1];
		}
		data[--size] = null;
        return item;
    }

	/** Delete the last item of the SequenceInterface<T>
	 * @return the deleted item
	 * @throws EmptySequenceException if the Sequence is empty
	 */
	@Override public T deleteTail(){
		if (isEmpty()){
			throw new EmptySequenceException("empty sequence");
		}
		T item = data[size-1];
		data[--size] = null;
        return item;
    }

	/** Delete a given number of items from the end of the SequenceInterface<T> 
	 * @param numItems the int number of items to delete
	 * @return true if the operation is successful and false if the SequenceInterface<T> has less than
	 *         numItems. If the SequenceInterface<T> is not long enough, no change will happen.
	 */
	@Override public boolean trim(int numItems){
		if (numItems > size){
			return false;
		}
		for (int i = 0; i < numItems; i++){
			data[size--]  = null;
		}
		
        return true;
    }

	/** Delete a given number items from a given position in the SequenceInterface<T>
	 * @param start the int starting position of the cut
	 * @param numItems the int number of items to cut
	 * @return true if the operation is successful and false if start+numItems > the size of the SequenceInterface<T>.
	 *  	   If the SequenceInterface<T> is not long enough, no change will happen.
	 */
	@Override public boolean cut(int start, int numItems){
		if (start + numItems > size || start < 0){
			return false;
		}
		for (int i = start; i < size - numItems; i++){
			data[i] = data[i + numItems];
		}
		size -= numItems;
		for (int i = size; i < size + numItems; i++){
			data[i] = null;
		}
		
        return true;
    }

	/** (EXTRA CREDIT) Return a slice of the SequenceInterface<T>, containing all items that are less than or equal a given item
	 * 
	 * @param item the T item
	 * @return a SequenceInterface<T> containing all items that are less than or equal to item, maintaining their relative order. 
	 *         No change should happen to the SequenceInterface<T> on which the method is called.
	 */
	@Override public SequenceInterface<T> slice(T item){
        return null;
    }

	/** (EXTRA CREDIT) Return a slice of the SequenceInterface<T>, containing a given number of items starting from a given position in the SequenceInterface<T>
	 * @param start the int start position
	 * @param numItems the int number of items
	 * @return a SequenceInterface<T> containing numItems items of the SequenceInterface<T>, starting from start and with their relative order. If the SequenceInterface<T>
	 *         is not long enough, no change should happen and the method should return null.
	 */
	@Override public SequenceInterface<T> slice(int start, int numItems){
        return null;
    }

	@Override public String toString(){
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < size; i++){
			string.append(data[i]);
		}
		return string.toString();
	}

    
}
