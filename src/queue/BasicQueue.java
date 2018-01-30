package queue;

public class BasicQueue<X> implements Queue<X> {

    private X[] data;
    private int front;
    private int end;

    public BasicQueue() {
        // Queue with default size
        this(1000);
    }

    public BasicQueue(int size) {
        this.front = -1;
        this.end = -1;
        this.data = (X[]) new Object[size];
    }

    @Override
    public int size() {
        // Queue is empty
        if (front == -1 && end == -1) {
            return 0;
        } else {
            // Add 1 for inclusive count
            return end - front + 1;
        }
    }

    @Override
    public void enQueue(X item) {
        // Check if queue is full
        if ((end + 1) % data.length == front) {
            throw new IllegalStateException("The queue is full");
        }
        if (size() == 0) {
            // Initial state
            front++;
            end++;
            data[end] = item;
        } else {
            // Add item to the end of the queue
            data[++end] = item;
        }
    }

    @Override
    public X deQueue() {
        X item = null;
        if (size() == 0) {
            throw new IllegalStateException("No items in the queue");
        } else if (front == end) {
            // Last item in queue and reinitialize state
            item = data[front];
            // Clear up data for GC
            data[front] = null;
            front = -1;
            end = -1;
        } else {
            // Get item at the front and increment
            item = data[front];
            data[front] = null;
            front++;
        }
        return item;
    }

    @Override
    public boolean contains(X item) {
        boolean found = false;

        // Check if queue is empty
        if (size() == 0) {
            return false;
        }

        for (int i = front; i < end; i++) {
            if (data[i].equals(item)) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public X access(int position) {
        if (size() == 0 || position > size()) {
            throw new IllegalArgumentException("No items in the queue, or the position is greater than the size of the queue");
        }

        int trueIndex = 0;
        for (int i = front; i < end; i++) {
            if (trueIndex == position) {
                return data[i];
            }
            trueIndex++;
        }

        // Couldn't find the data
        throw new IllegalArgumentException("Could not access the data at position: " + position);
    }

}
