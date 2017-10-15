package root.entity;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="role")
@Access(AccessType.FIELD)
public class Role implements Identifiable, Serializable
{    
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    private Integer id;
    
    @Column(name="role_name")
    private String roleName;
        
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
    
    public String getRoleName() 
    {
        return roleName;
    }

    public void setRoleName(String roleName) 
    {
        this.roleName = roleName;
    }           
}
