package queue;

import java.util.ArrayList;
import java.util.List;

public class ListQueue<X> implements Queue<X> {

    private List<X> data;

    public ListQueue() {
        this.data = new ArrayList<>();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void enQueue(X item) {
        data.add(item);
    }

    @Override
    public X deQueue() {
        if (size() == 0) {
            throw new IllegalStateException("No items in the queue");
        }
        return data.remove(0);

    }

    @Override
    public boolean contains(X item) {
        return data.contains(item);
    }

    @Override
    public X access(int position) {
        if (size() == 0 || position > size()) {
            throw new IllegalArgumentException("No items in the queue, or the position is greater than the size of the queue");
        }

        return data.get(position);

    }
}
