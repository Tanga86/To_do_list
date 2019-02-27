package enit;

import java.util.Date;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="list")


public class Task {

    public Task(String task) {
        this.task = task;
    }

    public Task() {
    }



    @Id//Указываем кажждый наш столбец
    @GeneratedValue (strategy = GenerationType.IDENTITY)//auto_increment ,то же самое
    @Column(name ="taskid")
    private int taskid;
    @Column(name ="task")
    private String task;
    @Column(name ="assignedDate")
    Date assignedDate;

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }
}
