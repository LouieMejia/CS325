package GetterSetter;

import java.time.LocalDateTime;

public class Tasks {
    private int id;
    private String name;
    private String description;
    private int listId;
    private LocalDateTime dueDate;
    private LocalDateTime completeDate;
    private LocalDateTime dateCreated;
    private TaskStatus status;

    public Tasks(int taskId, String description, LocalDateTime dueDate) {
        this.id = taskId;
        this.description = description;
        this.dueDate = dueDate;
    }

    public Tasks() {

    }

    public Tasks(int id, String name, String description, LocalDateTime dueDate, TaskStatus status, boolean completed, LocalDateTime completedDate, int listId) {
    }

    //Setters
    public void setId(int id){
        this.id = this.id;
    }
    public void setName(String name){
        this.name = this.name;
    }
    public void setDescription(String description){
        this.description = this.description;
    }
    public void setListId(int list_id){
        this.listId = listId;
    }
    public void setDueDate(LocalDateTime due_date){
        this.dueDate = dueDate;
    }
    public void setCompleteDate(LocalDateTime now){
        this.completeDate = completeDate;
    }
    public void setDateCreated(LocalDateTime created_at){
        this.dateCreated = dateCreated;
    }
    public void setStatus(TaskStatus completed){
        this.status = status;
    }
    //Getters
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public int getListId(){
        return listId;
    }
    public LocalDateTime getDueDate(){
        return dueDate;
    }
    public LocalDateTime getCompleteDate(){
        return completeDate;
    }
    public LocalDateTime getDateCreated(){
        return dateCreated;
    }
    public TaskStatus getStatus(){
        return status;
    }

    public String toString() {
        return "Task #" + id + " - Description: " + description + " - Due Date: " + dueDate;
    }

    public void setCompleteDate(boolean b) {
    }

    public boolean isCompleted() {
        return completeDate != null;
    }

    public void setCompleted(boolean completed) {
    }


}
