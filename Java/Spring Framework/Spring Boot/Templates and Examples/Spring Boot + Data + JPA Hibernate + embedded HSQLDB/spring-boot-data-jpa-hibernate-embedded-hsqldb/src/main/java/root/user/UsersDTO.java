package root.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UsersDTO 
{
    private Integer count;
    private List<UserDTO> users;

    public UsersDTO() 
    {
        count = 0;
        users = new ArrayList<>();
    }

    public UsersDTO(Collection<User> usersList) 
    {        
        this();
        usersList.forEach(user -> {
            users.add(new UserDTO(user));
            count++;
        });
    }    

    public Integer getCount() 
    {
        return count;
    }

    public List<UserDTO> getUsers() 
    {
        return Collections.unmodifiableList(users);
    }   
}
