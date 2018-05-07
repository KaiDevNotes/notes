package root;

public class Location
{
    private String id;
    private String country;
    private String city;
    private String iso2Code;

    public String getId() 
    {
        return id;
    }

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getCountry() 
    {
        return country;
    }

    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCity() 
    {
        return city;
    }

    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getIso2Code() 
    {
        return iso2Code;
    }

    public void setIso2Code(String iso2Code) 
    {
        this.iso2Code = iso2Code;
    }      
}
