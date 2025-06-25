import java.util.NoSuchElementException;

public class Queue<E> {

    private static class QueueItem<E> {
        private final E value;
        private QueueItem<E> next;

        QueueItem(E value) {
            this.value = value;
            this.next = null;
        }
    }

    private QueueItem<E> head;
    private QueueItem<E> tail;

    public void enqueue(E value) {
        QueueItem<E> newItem = new QueueItem<>(value);
        if (head == null) {
            head = newItem;
        }
        if (tail != null) {
            tail.next = newItem;
        }
        tail = newItem;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("There are no elements in the queue");
        }
        E value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
