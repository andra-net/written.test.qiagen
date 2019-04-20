package problem2;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicStack {

    private int[] data;
    private int currentReference;
    private Deque<Integer> minReferences;

    public BasicStack(int capacity) {

        data = new int[capacity];
        currentReference = 0;
        minReferences = new ArrayDeque<>();
        minReferences.add(currentReference);
    }

    public void push(int item) {

        if (currentReference > data.length) {

            throw new IllegalStateException("Stack already full");
        }

        if (item < data[minReferences.peekLast()]) {

            minReferences.add(currentReference);
        }

        data[currentReference++] = item;
    }

    public int pop() {

        if (currentReference == 0) {

            throw new IllegalStateException("Stack is empty.");
        }

        int element = data[--currentReference];

        if (minReferences.peekLast() == currentReference) {
            minReferences.removeLast();
        }

        return element;
    }

    public int min() {

        return data[minReferences.peekLast()];
    }
}
