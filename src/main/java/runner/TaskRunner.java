package runner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskRunner implements Comparator<Task> {

    private boolean isWorkNow = false;

    private List<Task> taskList = new ArrayList<>();

    public void add(Task task) {
        checkState();
        taskList.add(task);
    }

    private void checkState() {
        if (isWorkNow) throw new IllegalStateException();
    }

    public void start() {
        checkState();
        isWorkNow = true;
        taskList.sort(this);
        taskList.forEach(Task::run);
        taskList.clear();
        isWorkNow = false;
    }

    @Override
    public int compare(Task o1, Task o2) {
        return Integer.compare(o2.getPriority(), o1.getPriority());
    }
}
