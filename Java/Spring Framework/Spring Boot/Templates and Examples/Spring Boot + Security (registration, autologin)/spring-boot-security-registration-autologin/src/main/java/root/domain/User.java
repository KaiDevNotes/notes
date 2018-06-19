package root.domain;

public class User
{
    private final String id;
    private final String login;
    private final String password;
    private final Role role;
    
    private User(Builder builder)
    {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        if (builder.role == null)
        {
            this.role = Role.USER;
        }
        else 
        {
            this.role = builder.role;
        }
    }

    public String getId() 
    {
        return id;
    }

    public String getLogin() 
    {
        return login;
    }

    public String getPassword() 
    {
        return password;
    } 

    public Role getRole() 
    {
        return role;
    }
    
    public enum Role
    {
        USER, ADMIN;
    } 
    
    public static class Builder
    {
        private String id;
        private String login;
        private String password;
        private Role role;
        
        public Builder(String login, String password, Role role)
        {
            this.login = login;
            this.password = password;
            this.role = role;
        }
        
        public User build()
        {
            return new User(this);
        }
        
        public Builder id(String id)
        {
            this.id = id;
            return this;
        }
    }
}
