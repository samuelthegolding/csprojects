import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Assignment5 {
    public static void main(String[] args) {
        simpleQueueTest();
        scheduleTasks("taskset1.txt");
        scheduleTasks("taskset2.txt");
        scheduleTasks("taskset3.txt");
        scheduleTasks("taskset4.txt");
        scheduleTasks("taskset5.txt");
    }

    public static void scheduleTasks(String taskFile) {
        Queue<Task> tasksByDeadline = new LinkedList<>();
        Queue<Task> tasksByStart = new LinkedList<>();
        Queue<Task> tasksByDuration = new LinkedList<>();

        readTasks(taskFile, tasksByDeadline, tasksByStart, tasksByDuration);

        schedule("Deadline Priority : " + taskFile, tasksByDeadline);
        schedule("Start Time Priority : " + taskFile, tasksByStart);
        schedule("Duration priority : " + taskFile, tasksByDuration);
    }

    public static void simpleQueueTest() {
        // TODO: Uncomment code here for a simple test of your priority queue code

        System.out.println("Simple Queue Test: (Should be in Order)");
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.enqueue(9);
        queue.enqueue(7);
        queue.enqueue(5);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(10);

        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
        System.out.println("\n --------Scheduling Tests-----------");

    }

    /**
     * Reads the task data from a file, and creates the three different sets of tasks for each
     * @param filename Name of File
     * @param tasksByDeadline Tasks that will be prioritized by Deadline
     * @param tasksByStart Tasks that will be prioritized by Start Time
     * @param tasksByDuration Tasks that will be prioritized by Duration
     */
    public static void readTasks(String filename,
                                 Queue<Task> tasksByDeadline,
                                 Queue<Task> tasksByStart,
                                 Queue<Task> tasksByDuration) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            int ID = 0;

            while (scanner.hasNextLine()) {
                ID++;
                String[] parts = scanner.nextLine().split("\\s+");
                if (parts.length != 3) continue; // Skip non-standard lines
                try {
                    // Parse integers
                    int start = Integer.parseInt(parts[0]);
                    int deadline = Integer.parseInt(parts[1]);
                    int duration = Integer.parseInt(parts[2]);


                    // Create task objects for each priority type
                    tasksByStart.add(new TaskByStart(ID, start, deadline, duration));
                    tasksByDeadline.add(new TaskByDeadline(ID, start, deadline, duration));
                    tasksByDuration.add(new TaskByDuration(ID, start, deadline, duration));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid start, deadline, or duration format in" + filename);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + filename);
        }
    }

    /**
     * Given a set of tasks, schedules them and reports the scheduling results
     * @param label Label of the scheduled tasks
     * @param tasks Tasks that will be scheduled
     */
    public static void schedule(String label, Queue<Task> tasks) {

        System.out.println(label);
        if (tasks.isEmpty()) {
            // Handling Empty Queue
            System.out.println("No tasks in queue");
            return;
        }

        // Initializing Variables and Queues
        int currentTime = 1;
        int lateTasks = 0;
        int totalLateTime = 0;
        PriorityQueue<Task> schedulingQueue = new PriorityQueue<>();
        Queue<Task> removeQueue = new LinkedList<>();

        while (!tasks.isEmpty() || !schedulingQueue.isEmpty()) {
            // Add all tasks that are available to start to the scheduling queue
            for (Task task : tasks) {
                if (currentTime >= task.start) {
                    schedulingQueue.enqueue(task);
                    removeQueue.add(task);
                }
            }

            // Cleaning up the tasks queue
            tasks.removeAll(removeQueue);
            removeQueue.clear();

            // When no tasks can be scheduled for a certain time block, Print an Empty line
            if (schedulingQueue.isEmpty()) {
                System.out.printf("Time %3d: ---\n", currentTime);
                currentTime++;
                continue;
            }

            // If there are tasks available, grab the first one, print it, and "Process" it
            Task currentTask = schedulingQueue.dequeue();
            System.out.printf("Time %3d: %s", currentTime, currentTask);
            currentTask.duration--;

            // Check if the task is completed
            if (currentTask.duration == 0) {
                System.out.print(" **"); // Task completed

                // Add late information if necessary
                if (currentTime > currentTask.deadline) {
                    int lateTime = currentTime - currentTask.deadline;
                    totalLateTime += lateTime;
                    lateTasks++;
                    System.out.print(" Late " + lateTime);
                }

            } else {
                // Reinsert the task with the updated duration
                schedulingQueue.enqueue(currentTask);
            }

            System.out.println();
            currentTime++;
        }

        // Print summary
        System.out.printf("Tasks late %d Total Late %d\n", lateTasks, totalLateTime);
        System.out.println();
    }
}
