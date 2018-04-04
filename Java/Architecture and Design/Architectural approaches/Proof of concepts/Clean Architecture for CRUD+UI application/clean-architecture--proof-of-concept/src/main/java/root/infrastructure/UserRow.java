package root.infrastructure;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import root.domain.User;

@Entity
@Table(name="user")
@Access(AccessType.FIELD)
public class UserRow implements Serializable
{
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    private Integer id;
    
    @Column(name="login")
    private String login;
    
    @Column(name="password")
    private String password;
    
    @Column(name="email")
    private String email;
    
    public UserRow()
    {
    }
    
    public UserRow(User user)
    {
        id = user.getId();
        login = user.getLogin();
        password = user.getPassword();
        email = user.getEmail();
    }
    
    public User toDomainObject()
    {
        return new User(id, login, password, email);
    }
}
