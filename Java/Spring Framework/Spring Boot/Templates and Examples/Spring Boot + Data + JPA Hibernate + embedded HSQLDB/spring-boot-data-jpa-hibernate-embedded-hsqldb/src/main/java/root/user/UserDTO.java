package root.user;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO 
{
    private Integer id;
    private String firstName;
    private String lastName;
    private String role;

    public UserDTO() 
    {
    }

    public UserDTO(User user) 
    {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        role = user.getRole().getRoleName();
    } 
    
    public Integer getId() 
    {
        return id;
    }

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    } 

    public String getRole() 
    {
        return role;
    }

    public void setRole(String role) 
    {
        this.role = role;
    }    
}
