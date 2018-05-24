package root.infrastructure.persistence;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import root.domain.User;

@Entity
@Table(name="user")
@Access(AccessType.FIELD)
public class UserDbEntry extends User implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;
    
    @Column(name="login")
    private String login;
    
    @Column(name="password")
    private String password;
    
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private User.Role role;

    @Override
    public String getDomainId() 
    {
        return id.toString();
    }

    @Override
    public String getLogin() 
    {
        return login;
    }

    @Override
    public String getPassword() 
    {
        return password;
    }

    @Override
    public Role getRole() 
    {
        return role;
    }
}
