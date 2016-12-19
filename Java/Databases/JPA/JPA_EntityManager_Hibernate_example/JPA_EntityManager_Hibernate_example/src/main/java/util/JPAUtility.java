package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Чтобы закрыть соедиение с БД и остановить поток выполнения программы, необх.
 * вызывать метод close() из данного класса.
 * 
 * Например, в web-проекте на основе Spring можно зарегистрировать данный класс, 
 * как компонент в контексте Spring со scope="request", т.е. чтобы новый экземпляр
 * JPAUtility создавался для каждого запроса.
*/
public class JPAUtility {
    
    private static EntityManagerFactory entityManagerFactory = null;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-persistence-unit");
    }

    public static EntityManager getEntityManager()
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
    	return entityManager;
    }
    
    public static void close(){
        entityManagerFactory.close();
    }
}
