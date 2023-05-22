package GetterSetter;

import java.time.LocalDateTime;

public class ToDoList {
    private int id;
    private String name;
    private int userId;
    private LocalDateTime dateCreated;

    public ToDoList(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public ToDoList(int id, String name, int userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getUserId(){
        return userId;
    }
    public LocalDateTime getDateCreated(){
        return dateCreated;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public void LocalDateTime(LocalDateTime dateCreated){
        this.dateCreated = dateCreated;
    }
}
