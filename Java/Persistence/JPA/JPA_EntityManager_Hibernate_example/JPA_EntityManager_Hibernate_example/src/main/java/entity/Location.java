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
public class Location implements Identifiable {
    
    private Integer id;	
    private String name;
    private Continent continent;
    private List<Server> servers = new ArrayList<Server>();
    
    public Location(){}
    
    public Location(String name){
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
    @JoinColumn(name = "CONTINENT_ID", referencedColumnName = "ID") 
    
    public Continent getContinent(){
        return continent;
    }    
    public void setContinent(Continent continent){
        this.continent = continent;
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
    public void setServers(List<Server> servers){
        this.servers = servers;
    }
    
}
