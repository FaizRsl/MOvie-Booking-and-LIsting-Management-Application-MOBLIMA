package Model.User;

import java.io.Serializable;
/**
 * The Class User.
 */
public class User implements Serializable{
	/** The Constant serialVersionUID */
    private static final long serialVersionUID = -7971847612032322845L;
    /** The username of the user. */
    private String username;
    /** The password of the user. */
    private String password;
    /**
     * Instantiates a new user.
     *
     * @param username the username of the user.
     * @param password the password of the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    /**
     * Gets the username of the user.
     *
     * @return the username of the user.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Gets the password of the user.
     *
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }



}
