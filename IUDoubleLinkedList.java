import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Node (double linked) based implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented,
 * ListIterator is supported.
 * 
 * @author Rhett Edwards
 * @version Spring 2024
 * @param <T> type to store
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {
    /* Instance Variables */
    private DllNode<T> head;    // start of the list
    private DllNode<T> tail;    // end of the list
    private int size;           // number of nodes in the list
    private int changesMade;    // current number of changes made to the list

    @Override
    public void addToFront(T element) {
        ListIterator<T> it = listIterator();    // create list iterator at the beginning
        it.add(element);                        // immediately add new element
    }

    @Override
    public void addToRear(T element) {
        DllNode<T> newNode = new DllNode<T>(element);   // create newNode element
        if (isEmpty()) {                                // if the list is empty
            head = newNode;                             // set head to newNode
            tail = newNode;                             // set tail to newNode
        } else {                                        // else
            newNode.setPreviousNode(tail);              // set newNode's previous node to current tail
            tail.setNextNode(newNode);                  // set tail's next node to newNode
            tail = newNode;                             // reassign tail to newNode
        }
        size++;
        changesMade++;
    }

    @Override
    public void add(T element) {
        addToRear(element); // same as addToRear
    }

    @Override
    public void addAfter(T element, T target) {
        ListIterator<T> it = listIterator();    // create new iterator
        boolean elementFound = false;           // set elementFound to false
        while (it.hasNext()) {                  // while there is something to iterate through
            if (it.next().equals(target)) {     // if iterator's next() is equal to target
                elementFound = true;            // set elementFound to true
                it.add(element);                // call iterator add(element)
            }   
        }
        if (!elementFound) {                    // if target is not found
            throw new NoSuchElementException(); // throw exception
        }
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {            // if index is out of bounds
            throw new IndexOutOfBoundsException();  // throw exception
        }
        ListIterator<T> it = listIterator(index);   // create new iterator starting at the given index
        it.add(element);                            // immediately add new element
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {                        // if list is empty
            throw new NoSuchElementException(); // throw exception
        }
        ListIterator<T> it = listIterator();    // create new list iterator starting at beginning
        T returnElement = it.next();            // set returnElement equal to iterators next()
        it.remove();                            // call iterator remove()
        return returnElement;                   // return element collected by iterators next()
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {                        // if list is empty
            throw new NoSuchElementException(); // throw exception
        }
        T returnElement = tail.getElement();
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail.getPreviousNode().setNextNode(null);
            tail = tail.getPreviousNode();
        }

        size--;
        changesMade++;
        return returnElement;
    }

    @Override
    public T remove(T element) {
        ListIterator<T> it = listIterator();    // create new list iterator at the beginning
        boolean elementFound = false;           // set elementFound equal to false
        T returnElement = null;                 // set returnElement equal to null
        while(it.hasNext()) {                       // while there is something to iterate through
            returnElement = it.next();              // set returnElement equal to the iterator next()
            if (returnElement.equals(element)) {    // if returnElement is equal to what we want to remove
                elementFound = true;                // set elementFound to true
                it.remove();                        // call iterator remove()
                break;                              // prevent while loop from assessing anything else and changing return value
            }
        }
        if (!elementFound) {                        // if the element is not found
            throw new NoSuchElementException();     // throw exception
        }
        return returnElement;                       
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {           // if index is out of bounds
            throw new IndexOutOfBoundsException();  // throw exception
        }
        ListIterator<T> it = listIterator(index);   // create new iterator at the given index
        T returnElement = it.next();                // set returnElement equal to return of iterator's next() driving over desired index
        it.remove();                                // call iterators remove
        return returnElement;
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {           // if index is out of bounds
            throw new IndexOutOfBoundsException();  // throw exception
        }
        ListIterator<T> it = listIterator(index);   // create new iterator at the given index
        it.next();                                  // drive over this index to set the last returnedNode != null and point at correct node
        it.set(element);                            // call iterator's set()
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {           // if index is < 0 or >= size
            throw new IndexOutOfBoundsException();  // throw exception
        }
        ListIterator<T> it = listIterator(index);   // create new list iterator at the desired index
        return it.next();                           // call iterator next() and return
    }

    @Override
    public int indexOf(T element) {
        ListIterator<T> it = listIterator();    // create iterator at the beginning
        int returnIndex = -1;                   // set returnIndex equal to -1
        while (it.hasNext()) {                      // while there is something to iterate through
            if (it.next().equals(element)) {        // if iterator's next() equals the desired element
                returnIndex = it.previousIndex();   // re-assign returnIndex to iterators previousIndex() since you stepped over desired element
            }                                       // else if nothing ever found returnIndex is still -1
        }
        return returnIndex;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.getElement();
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.getElement();
    }

    @Override
    public boolean contains(T target) {
        return indexOf(target) != -1;
    }

    @Override
    public boolean isEmpty() {
        boolean returnValue;
        if (head != null) {
            returnValue = false;
        } else {
            returnValue = true;
        }
        return returnValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
	public String toString() {
		StringBuilder string = new StringBuilder();				// start string builder
		string.append("[");									// first line starts with "["
        DllNode<T> currentNode = head;                          // set currentNode equal to head
		while (currentNode != null) {							// loop through nodes
			string.append(currentNode.getElement().toString());	// append each element
			string.append(", ");							// add comma and space after each element
            currentNode = currentNode.getNextNode();            // cycle to the next node
		}
		if (!isEmpty()) {										// if the list is not empty
			string.delete(string.length()-2, string.length());	// delete the last , and space
		}
		string.append("]");									// close the list with "]"
		return string.toString();
	}

    @Override
    public Iterator<T> iterator() {
        return new DllIterator(0);
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DllIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        return new DllIterator(startingIndex);
    }

    /** Acts as a basic Iterator and ListIterator for double linked lists */
    private class DllIterator implements ListIterator<T> {

        /* Instance Variables */
        private DllNode<T> nextNode;            // node in front of iterator
        private DllNode<T> lastReturnedNode;    // last node returned by iterator
        private int nextIndex;                  // index in front of iterator
        private int iterChangesMade;            // changes made from iterator's perspective
        

        /**
         * Initialize iterator before the given startingIndex.
         * @param startingIndex
         */
        public DllIterator(int startingIndex) { // start at the end if index is in second half
            if (startingIndex < 0 || startingIndex > size) {
                throw new IndexOutOfBoundsException();
            }

            /*
             * Need to correct implementation of starting at either end of the list
             */
            
            // if (startingIndex <= size / 2) {
                nextNode = head;
                for (int i = 0; i < startingIndex; i++) {
                    nextNode = nextNode.getNextNode();
                }
            // } else {
            //     nextNode = tail;
            //     for (int i = size; i > startingIndex; i--) {
            //         nextNode = nextNode.getPreviousNode();
            //     }
            // }
            nextIndex = startingIndex;
            iterChangesMade = changesMade;
            
        }


        @Override
        public boolean hasNext() {
            if (iterChangesMade != changesMade) {
                throw new ConcurrentModificationException();
            }
            return nextNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T returnValue = nextNode.getElement();
            lastReturnedNode = nextNode;
            nextNode = nextNode.getNextNode();
            nextIndex++;
            return returnValue;
        }

        @Override
        public boolean hasPrevious() {
            if (iterChangesMade != changesMade) {
                throw new ConcurrentModificationException();
            }
            return nextNode != head;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextNode != null) {
                nextNode = nextNode.getPreviousNode();
            } else {
                nextNode = tail;
            }
            nextIndex--;
            lastReturnedNode = nextNode;
            return nextNode.getElement();
        }

        @Override
        public int nextIndex() {
            if (iterChangesMade != changesMade) {
                throw new ConcurrentModificationException();
            }
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            if (iterChangesMade != changesMade) {
                throw new ConcurrentModificationException();
            }
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            if (iterChangesMade != changesMade) {               // if changes made to list elsewhere
                throw new ConcurrentModificationException();    // throw exception
            }
            if (lastReturnedNode == null) {         // if there was not a lastReturnedNode
                throw new IllegalStateException();  // throw exception
            }

            if (lastReturnedNode == head) {                                                         // if lastReturnedNode is head
                head = lastReturnedNode.getNextNode();                                              // set head equal to next node in the list
            } else {                                                                                // otherwise
                lastReturnedNode.getPreviousNode().setNextNode(lastReturnedNode.getNextNode());     // set the next node of the previous node to the next node of the lastReturnedNode
            }
            if (lastReturnedNode == tail) {                                                         // if lastReturnedNode is tail
                tail = lastReturnedNode.getPreviousNode();                                          // set tail equal to the previous node from tail
            } else {                                                                                // otherwise
                lastReturnedNode.getNextNode().setPreviousNode(lastReturnedNode.getPreviousNode()); // set the previous node of the next node to the previous node of lastReturnedNode
            }
            if (lastReturnedNode != nextNode) { // if last move was next
                nextIndex--;
            } else {                            // if last move was previous
                nextNode = nextNode.getNextNode();
            }
            lastReturnedNode = null;
            size--;
            changesMade++;
            iterChangesMade++;
        }

        @Override
        public void set(T e) {
            if (iterChangesMade != changesMade) {               // if any changes have been made outside this iterator
                throw new ConcurrentModificationException();    // throw exception
            }
            if (lastReturnedNode == null) {         // if last returned node is null
                throw new IllegalStateException();  // throw exception
            }
            lastReturnedNode.setElement(e); // set new element e for lastReturnedNode
            changesMade++;
            iterChangesMade++;
        }

        @Override
        public void add(T e) {
            if (iterChangesMade != changesMade) {               // if any changes have been made outside this iterator
                throw new ConcurrentModificationException();    // throw exception
            }
            DllNode<T> addedNode = new DllNode<T>(e);   // create new node to add into the list
            if (head == null) {     // if this is the first element
                head = addedNode;   // assign head and tail to addedNode
                tail = addedNode;
            } else if (nextIndex == 0) {            // else if it is at the beginning of a list            
                addedNode.setNextNode(head);        // set addedNode's next node to current head
                head.setPreviousNode(addedNode);    // set current head's previousNode to addedNode
                head = addedNode;                   // re-assign head to the addedNode
            } else if (nextIndex == size) {         // else if node is at the end
                addedNode.setPreviousNode(tail);    // set addedNode's previous node to the current tail
                tail.setNextNode(addedNode);        // set the current tail's nextNode to addedNode
                tail = addedNode;                   // re-assign tail to the addedNode
            } else {                                                    // otherwise    ORDER IS IMPORTANT!!
                addedNode.setNextNode(nextNode);                        // set addedNode's next node to nextNode
                addedNode.setPreviousNode(nextNode.getPreviousNode());  // set addedNode's previous node to nextNode's previous node
                nextNode.getPreviousNode().setNextNode(addedNode);      // set nextNode's previous node's next node to added node 
                nextNode.setPreviousNode(addedNode);                    // set nextNode's previous node to addedNode
            }
            size++;
            nextIndex++;
            changesMade++;
            iterChangesMade++;
        }

    }
}
