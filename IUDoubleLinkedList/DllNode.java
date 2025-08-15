/**
 * A node element to store a generic type<T> to be used with a double linked array list.
 * 
 * @author Rhett Edwards
 * @version Spring 2024
 */
public class DllNode<T> {

    /* Instance Variables */
    private T element;
    private DllNode<T> nextNode;
    private DllNode<T> previousNode;

    /**
     * Initialize a new Node<T> with a given element
     * @param _element element to place in the node
     */
    public DllNode(T _element) {

        this.element = _element;
        this.nextNode = null;
        this.previousNode = null;
    }

    /**
     *  get the element of the node
     * @return element contained in the node
     */
    public T getElement() {
        return element;
    }

    /**
     * set the element of the node
     * @param element that will overwrite the current node's element
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * get the next node in the sequence
     * @return reference to the next node
     */
    public DllNode<T> getNextNode() {
        return nextNode;
    }

    /**
     * set the next node in the sequence
     * @param nextNode that will be assigned as the node following this node
     */
    public void setNextNode(DllNode<T> nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * get the previous node in the sequence
     * @return reference to the previous node
     */
    public DllNode<T> getPreviousNode() {
        return previousNode;
    }

    /**
     * set the previous node in the sequence
     * @param previousNode that will be assigned as the node preceding this node
     */
    public void setPreviousNode(DllNode<T> previousNode) {
        this.previousNode = previousNode;
    }
}
