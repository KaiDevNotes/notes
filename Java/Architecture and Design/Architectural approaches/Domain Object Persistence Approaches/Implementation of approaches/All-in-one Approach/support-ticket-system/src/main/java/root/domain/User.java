package root.domain;

import java.io.Serializable;
import java.util.Objects;
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

@Entity
@Table(name="user")
@Access(AccessType.FIELD)
public class User implements DomainObject, Serializable 
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
    
    public User()
    {
    }
    
    public User(final String login, final String password, final Role role)
    {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public UUID getId() 
    {
        return id;
    }

    public String getLogin() 
    {
        return login;
    }

    public String getPassword() 
    {
        return password;
    }

    public Role getRole() 
    {
        return role;
    }
    
    public enum Role
    {
        CUSTOMER, SUPPORT_ENGINEER;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(id);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(id, other.getId())) 
        {
            return false;
        }
        return true;
    }
}
