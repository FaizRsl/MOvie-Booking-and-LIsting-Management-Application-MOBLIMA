package Model.User;

import java.io.Serializable;
/**
 * The Class Admin.
 */
public class Admin implements Serializable{
	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7971847612032322845L;
    /** The username of the admin. */
    private String username;
    /** The password of the admin. */
    private String password;
    /**
     * Instantiates a new admin.
     *
     * @param username the username of new admin
     * @param password the password of new admin
     */
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    /**
     * Gets the username of the admin.
     *
     * @return the username of the admin.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Gets the password of the admin.
     *
     * @return the password of the admin.
     */
    public String getPassword() {
        return password;
    }



}