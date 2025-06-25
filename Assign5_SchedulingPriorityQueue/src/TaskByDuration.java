public class TaskByDuration extends Task {
    public TaskByDuration(int ID, int start, int deadline, int duration) {
        super(ID, start, deadline, duration);
    }

    @Override // Comparing by Duration
    public int compareTo(Task other) {
        return Integer.compare(this.duration, other.duration);
    }
}