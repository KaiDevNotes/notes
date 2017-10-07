package kai.dev.project.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="USER")
public class User implements Identifiable, Serializable {

    private Integer id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    
    public User(){}

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="ID")
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

    @Column(name="LOGIN")
    public String getLogin() 
    {
        return login;
    }
    public void setLogin(String login) 
    {
        this.login = login;
    }

    @Column(name="PASSWORD")
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    @Column(name="FIRST_NAME")
    public String getFirstName() 
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    @Column(name="LAST_NAME")
    public String getLastName() 
    {
        return lastName;
    }  
    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    } 
}
