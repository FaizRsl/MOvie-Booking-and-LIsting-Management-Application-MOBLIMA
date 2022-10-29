import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID = -7971847612032322845L;

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }



}
