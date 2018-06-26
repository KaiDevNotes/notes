package root.domain;

import java.util.Objects;

public abstract class User implements DomainObject 
{
    public abstract String getLogin();
    public abstract String getPassword();
    public abstract Role getRole();
    
    public enum Role
    {
        CUSTOMER, SUPPORT_ENGINEER;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(getId().toString());
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
        if (!Objects.equals(getId().toString(), other.getId().toString())) 
        {
            return false;
        }
        return true;
    }
}
