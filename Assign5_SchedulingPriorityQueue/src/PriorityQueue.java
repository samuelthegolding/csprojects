public class PriorityQueue<E extends Comparable<E>> {
    // Queue Node implementation
    private static class QueueNode<E> {
        E value;
        int npl; // Null Path Length
        QueueNode<E> left;
        QueueNode<E> right;

        // Constructor of the node
        QueueNode(E value) {
            this.value = value;
            this.left = this.right = null;
            this.npl = 0;
        }
    }

    // Creating the Queue
    private QueueNode<E> root;
    public PriorityQueue() {
        root = null;
    }

    /**
     * Merge two leftist heaps
     * @param h1 One of two trees being merged
     * @param h2 One of two trees being merged
     * @return Merged tree
     */
    private QueueNode<E> merge(QueueNode<E> h1, QueueNode<E> h2) {
        // If either side is empty, return the other
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        // Compare and switch based on comparison
        E val1 = h1.value;
        E val2 = h2.value;

        if (val1.compareTo(val2) > 0) {
            QueueNode<E> temp = h1;
            h1 = h2;
            h2 = temp;
        }

        // Recursively merge
        h1.right = merge(h1.right, h2);

        // Maintain leftist heap property
        if (h1.left == null || (h1.right != null && h1.left.npl > h1.right.npl)) {
            QueueNode<E> temp = h1.left;
            h1.left = h1.right;
            h1.right = temp;
        }

        // Finding new null path length
        h1.npl = Math.min(
                (h1.right == null) ? 0 : h1.right.npl + 1,
                (h1.left == null) ? 0 : h1.left.npl + 1);
        return h1;
    }

    // Insert a value
    public void enqueue(E value) {
        root = merge(root, new QueueNode<>(value));
    }

    // Remove and return the minimum element
    public E dequeue() {
        if (isEmpty()) throw new IllegalStateException("Priority queue is empty, therefore it is unable to dequeue.");
        E minValue = root.value;
        root = merge(root.left, root.right);
        return minValue;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return root == null;
    }

    // Method to print the Priority Queue
    public void printQueue() {
        printQueue(root, 0, "");
    }

    // Recursive method to print the Queue
    private void printQueue(QueueNode<E> node, int level, String path) {
        if (node == null) return;
        printQueue(node.right, level + 1, path + "R");
        for (int i = 0; i < level; i++) {
            System.out.print("   ");
        }
        System.out.println(node.value + "(npl: " + node.npl + ")" + path);
        printQueue(node.left, level + 1, path + "L");
    }
}
