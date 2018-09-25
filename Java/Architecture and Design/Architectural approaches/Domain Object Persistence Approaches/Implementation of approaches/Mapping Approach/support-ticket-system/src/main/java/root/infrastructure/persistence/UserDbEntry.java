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
public class UserDbEntry implements Serializable
{
	private static final long serialVersionUID = 1L;

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

    public UUID getId() 
    {
        return id;
    }

    public void setId(final UUID id) 
    {
        this.id = id;
    }

    public String getLogin() 
    {
        return login;
    }

    public void setLogin(final String login) 
    {
        this.login = login;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(final String password) 
    {
        this.password = password;
    }

    public User.Role getRole() 
    {
        return role;
    }

    public void setRole(final User.Role role) 
    {
        this.role = role;
    }
}
