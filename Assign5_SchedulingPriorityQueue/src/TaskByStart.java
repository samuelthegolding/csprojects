public class TaskByStart extends Task {
    public TaskByStart(int ID, int start, int deadline, int duration) {
        super(ID, start, deadline, duration);
    }

    @Override // Comparing by Start Time, then Deadline
    public int compareTo(Task other) {
        if (this.start != other.start)
            return Integer.compare(this.start, other.start);
        return Integer.compare(this.deadline, other.deadline);
    }
}