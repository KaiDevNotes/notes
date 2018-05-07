package root.application;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import root.domain.User;

public class UserForm 
{
    private static final Integer NEW_FORM_ID = -1; 
    private static final String EMPTY = ""; 
    
    private Integer id;
    private String login;
    private String password;
    private String email;

    public UserForm() 
    {
        id = NEW_FORM_ID;
        login = EMPTY;
        password = EMPTY;
        email = EMPTY;
    }
    
    public UserForm(User user)
    {
        id = user.getId();
        login = isNotBlank(user.getLogin()) ? user.getLogin() : EMPTY;
        password = isNotBlank(user.getPassword()) ? user.getPassword() : EMPTY;
        email = isNotBlank(user.getEmail()) ? user.getEmail() : EMPTY;
    }

    public Integer getId() 
    {
        return id;
    }

    public void setId(Integer id) 
    {
        this.id = id;
    }
    
    public String getLogin() 
    {
        return login;
    }

    public void setLogin(String login) 
    {
        this.login = login;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }    

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }
    
    public boolean hasErrors()
    {
        return isBlank(login) || isBlank(password) || isBlank(email);
    }
    
    public User convertToDomainObject()
    {
        Integer normalizedId = NEW_FORM_ID.equals(id) ? null : id;
        return new User(normalizedId, login, password, email);
    }
}
