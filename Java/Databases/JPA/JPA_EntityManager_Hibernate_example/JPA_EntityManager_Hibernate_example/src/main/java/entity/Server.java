package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="SERVERS")
public class Server implements Identifiable {
    
    private Integer id;	
    private String name;
    private Location location;
    
    public Server(){}
    
    public Server(String name){
        this.name = name;
    }
    
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="ID")
    @Override
    public Integer getId() {
        return id;
    }
    @Override
    public void setId(Integer id){
        this.id = id;		
    }
    
    @Column(name="NAME")
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    //--------------------------------    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID", referencedColumnName = "ID") 
    
    public Location getLocation(){
        return location;
    }    
    public void setLocation(Location location){
        this.location = location;
    }
    
}
