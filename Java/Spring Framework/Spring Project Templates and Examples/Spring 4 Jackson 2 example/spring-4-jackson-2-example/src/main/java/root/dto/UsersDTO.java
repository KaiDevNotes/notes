package root.dto;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import root.model.User;


public class UsersDTO 
{    
    @JsonView(Views.Users.class)
    private int count;
    
    @JsonView(Views.Users.class)
    private final List<User> users;
    
    public UsersDTO()
    {
        users = new ArrayList<>();
        count = 0;
    }
    
    public UsersDTO(List<User> users)
    {
        this.users = new ArrayList<>(users);
        count = users.size();
    }
    
    public void add(User user)
    {
        users.add(user);
        count++;
    }
    
    public void add(List<User> users)
    {
        this.users.addAll(users);
        count = count + users.size();
    }
    
    public List<User> getUsers()
    {
        return Collections.unmodifiableList(users);
    }
}
