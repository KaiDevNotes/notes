package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="CONTINENTS")
public class Continent {
    
    private Integer id;	
    private String name;
    private List<Location> locations = new ArrayList<Location>();
    
    public Continent(){
        name = null;
    }
    
    public Continent(Continent continent){
        name = continent.getName();
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
    
    //---------------------------------------
    @OneToMany (
            mappedBy = "continent",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    
    public List<Location> getLocations(){
        return locations;
    }    
    public void setLocations(List<Location> l){
        locations = l;
    }
    
}
