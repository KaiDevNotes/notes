package root;

public class Server
{
    private String id;
    private String name;
    private String ip;
    private String port;
    private Location location;

    public String geId() 
    {
        return id;
    }

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getIp() 
    {
        return ip;
    }

    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getPort() 
    {
        return port;
    }

    public void setPort(String port) 
    {
        this.port = port;
    }

    public Location getLocation() 
    {
        return location;
    }

    public void setLocation(Location location) 
    {
        this.location = location;
    }
}