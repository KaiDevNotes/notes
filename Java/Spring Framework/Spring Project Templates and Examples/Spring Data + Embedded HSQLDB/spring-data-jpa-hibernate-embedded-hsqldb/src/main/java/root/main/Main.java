package root.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import root.config.JpaConfig;
import root.dao.UserDAO;
import root.entity.User;

public class Main 
{
    public static void main(String[] args)
    {            
        ApplicationContext ctx = 
                new AnnotationConfigApplicationContext(JpaConfig.class); 
        
        UserDAO userDAO = ctx.getBean(UserDAO.class);        
        User user = userDAO.getById(5);
                
        System.out.println();
        System.out.println("id: " + user.getId());
        System.out.println("login: " + user.getLogin());
        System.out.println("password: " + user.getPassword());
        System.out.println("first name: " + user.getFirstName());
        System.out.println("last name: " + user.getLastName());
        System.out.println("role: " + user.getRole().getRoleName());
        System.out.println();
    }    
}
