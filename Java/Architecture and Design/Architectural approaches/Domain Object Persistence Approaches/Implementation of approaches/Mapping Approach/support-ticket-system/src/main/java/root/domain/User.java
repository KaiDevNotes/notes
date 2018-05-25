package root.domain;

import java.util.Objects;

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
        this.role = builder.role;
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
        CUSTOMER, SUPPORT_ENGINEER;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(id, other.getId())) 
        {
            return false;
        }
        return true;
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
