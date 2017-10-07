package root.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import root.dto.Views;


public class User 
{
    @JsonView(Views.Users.class)
    private Integer id;
    
    @JsonView(Views.Users.class)
    private String firstName;
    
    @JsonView(Views.Users.class)
    private String lastName;
    
    @JsonIgnore
    private String login;
    
    @JsonIgnore
    private String password;
    

    public User() {}

    public User(Integer id, String firstName, 
                String lastName, String login, String password) 
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }       
}
