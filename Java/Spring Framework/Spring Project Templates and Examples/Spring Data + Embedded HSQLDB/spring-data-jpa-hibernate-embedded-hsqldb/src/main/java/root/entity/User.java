package root.entity;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="user")
@Access(AccessType.FIELD)
public class User implements Identifiable, Serializable 
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
    
    @Column(name="first_name")
    private String firstName;    
    
    @Column(name="last_name")
    private String lastName;    
    
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
    
    public User(){}

    @Override
    public Integer getId() 
    {
        return id;
    }
    
    @Override
    public void setId(Integer id) 
    {
        this.id = id;
    } 

    public String getLogin() 
    {
        return login;
    }
    
    public void setLogin(String login) 
    {
        this.login = login;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
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
    
    public Role getRole() 
    {
        return role;
    }

    public void setRole(Role role) 
    {
        this.role = role;
    }
}
