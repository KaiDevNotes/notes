package kai.dev.springdata.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="CONTINENTS")
public class Continent implements Identifiable {
    
    private Integer id;	
    private String name;
    private List<Location> locations = new ArrayList<Location>();
    
    public Continent(){}
    
    public Continent(String name){
        this.name = name;
    }
    
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    public void setLocations(List<Location> locations){
        this.locations = locations;
    }
    
}
