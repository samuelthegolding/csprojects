public class TaskByDeadline extends Task {
    public TaskByDeadline(int ID, int start, int deadline, int duration) {
        super(ID, start, deadline, duration);
    }

    @Override // Comparing by Deadline
    public int compareTo(Task other) {
        return Integer.compare(this.deadline, other.deadline);
    }
}