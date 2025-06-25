public abstract class Task implements Comparable<Task> {
    private final int ID;
    public final int start;
    public final int deadline;
    public int duration;

    public Task(int ID, int start, int deadline, int duration) {
        this.ID = ID;
        this.start = start;
        this.deadline = deadline;
        this.duration = duration;
    }

    public String toString() {
        return "Task " + ID;
    }

    @Override
    abstract public int compareTo(Task task);

}
