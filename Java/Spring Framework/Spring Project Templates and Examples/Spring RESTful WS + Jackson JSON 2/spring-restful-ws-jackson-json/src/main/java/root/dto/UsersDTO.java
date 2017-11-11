package root.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import root.model.User;

public class UsersDTO 
{    
    private int count;
    
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
    
    public int getCount()
    {
        return count;
    }
    
    public List<User> getUsers()
    {
        return Collections.unmodifiableList(users);
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
}
