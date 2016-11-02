package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="LOCATIONS")
public class Location {
    
    private Integer id;	
    private String name;
    private Continent continent;
    private List<Server> servers = new ArrayList<Server>();
    
    public Location(){
        name = null;
    }
    
    public Location(Location location){
        name = location.getName();
    }
    
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="ID")
    public Integer getId() {
        return id;
    }
    public void setId(Integer i){
        id = i;		
    }
    
    @Column(name="NAME")
    public String getName(){
        return name;
    }
    public void setName(String n){
        name = n;
    }
    
    //--------------------------------    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTINENT_ID", referencedColumnName = "ID") 
    
    public Continent getContinent(){
        return continent;
    }    
    public void setContinent(Continent c){
        continent = c;
    }
    
    //---------------------------------------
    @OneToMany (
            mappedBy = "location",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    
    public List<Server> getServers(){
        return servers;
    }    
    public void setServers(List<Server> s){
        servers = s;
    }
    
}
