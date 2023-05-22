package GetterSetter;

public class User {
    public User(long id, String username, String password){

    }
    private int id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public int getId(){ return id; }
    public String getUser(){ return this.username; }
    public String getPass(){ return password; }

    public void setId(int id){
        this.id = id;
    }
    public void setUser(String username){
        this.username = username;
    }
    public void setPass(String password){
        this.password = password;
    }
}
