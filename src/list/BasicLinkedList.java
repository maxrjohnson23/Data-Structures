package list;

public class BasicLinkedList<X> {

    private Node first;
    private Node last;
    private int nodeCount;

    public BasicLinkedList() {
        this.first = null;
        this.last = null;
    }

    public void add(X item) {
        if (first == null) {
            //Initialize LinkedList for the first time
            first = new Node(item);
            last = first;
        } else {
            // Create node and update the last node
            Node newLastNode = new Node(item);
            last.setNextNode(newLastNode);
            last = newLastNode;
        }
        nodeCount++;
    }

    // Remove first item in the list
    public X remove() {
        if (first == null) {
            return null;
        }
        X nodeItem = first.getNodeItem();

        // Update first pointer and remove the previous one
        first = first.getNextNode();
        nodeCount--;
        return nodeItem;
    }

    public void insert(X item, int position) {
        if (size() < position) {
            throw new IllegalStateException("The list is smaller than the position");
        }
        Node currentNode = first;
        // Iterate to the position
        for (int i = 1; i < position && currentNode != null; i++) {
            currentNode = currentNode.getNextNode();
        }
        // Splice the element into the list
        Node newNode = new Node(item);
        Node nextNode = currentNode.getNextNode();
        currentNode.setNextNode(newNode);
        newNode.setNextNode(nextNode);
        nodeCount++;
    }

    public X removeAt(int position) {
        if (size() < position) {
            throw new IllegalStateException("The list is smaller than the position");
        }

        Node currentNode = first;
        Node prevNode = first;
        // Iterate to position
        for (int i = 1; i < position && currentNode != null; i++) {
            prevNode = currentNode;
            currentNode = currentNode.getNextNode();
        }

        X nodeItem = currentNode.getNodeItem();
        prevNode.setNextNode(currentNode.getNextNode());
        nodeCount--;
        return nodeItem;

    }

    // Gets item at position
    public X get(int position) {
        if (size() < position) {
            throw new IllegalStateException("The list is smaller than the position");
        }

        Node currentNode = first;
        // Iterate to position
        for (int i = 1; i < position && currentNode != null; i++) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode.getNodeItem();
    }

    // Find position of item
    public int find(X item) {
        if (first == null) {
            throw new IllegalStateException("The list is empty");
        }
        Node currentNode = first;
        for (int i = 1; i < size() && currentNode != null; i++) {
            if (currentNode.getNodeItem().equals(item)) {
                return i;
            }
            currentNode = currentNode.getNextNode();
        }
        // Not found in list
        return -1;
    }

    public String toString() {
        StringBuilder contents = new StringBuilder();
        Node currentNode = first;

        while (currentNode != null) {
            contents.append(currentNode.getNodeItem());
            currentNode = currentNode.getNextNode();

            // Return comma-separated string
            if (currentNode != null) {
                contents.append(", ");
            }
        }
        return contents.toString();
    }

    public int size() {
        return nodeCount;
    }

    // Underlying data structure for the LinkedList
    private class Node {
        // Reference to the next node in the list
        private Node nextNode;
        private X nodeItem;

        public Node(X nodeItem) {
            this.nextNode = null;
            this.nodeItem = nodeItem;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        public X getNodeItem() {
            return nodeItem;
        }

    }
}
