package stack;

import java.util.ArrayList;
import java.util.List;

public class ListStack<X> implements Stack<X> {

    private List<X> data;
    private int stackPointer;

    public ListStack() {
        this.data = new ArrayList<>();
        stackPointer = 0;
    }

    @Override
    public void push(X newItem) {
        data.add(newItem);
        stackPointer++;
    }

    @Override
    public X pop() {
        if (stackPointer == 0) {
            throw new IllegalStateException("No more items on the stack");
        }
        return data.get(--stackPointer);
    }

    @Override
    public boolean contains(X item) {
        return data.stream().anyMatch(temp -> temp.equals(item));
    }

    @Override
    public X access(X item) {
        while (stackPointer > 0) {
            X tempItem = pop();
            if (item.equals(tempItem)) {
                return tempItem;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return stackPointer;
    }
}
