package GetterSetter;
public enum TaskStatus {
    NOT_STARTED("not_started"),
    IN_PROGRESS("in_progress"),
    ON_HOLD("on_hold"),
    CANCELLED("cancelled"),
    COMPLETED("completed"),
    NOT_COMPLETED("not_completed");

    private String status;

    private TaskStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
