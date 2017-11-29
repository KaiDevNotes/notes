package root.user;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO 
{
    public Collection<User> getAll()
    {
        return IntStream.range(1, 11)
                        .mapToObj(i -> newUser(i))
                        .collect(Collectors.toList());
    }
    
    public User getById(int i)
    {
        return newUser(i);
    }
    
    private User newUser(int i)
    {
        return new User(i, "first-name-" + i, "last-name-" + i, 
                        "login-" + i, "password-" + i);
    }
}
