package root.user;

public class UserDTO 
{
    private Integer id;
    private String firstName;
    private String lastName;

    public UserDTO() 
    {
    }

    public UserDTO(User user) 
    {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
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
}
