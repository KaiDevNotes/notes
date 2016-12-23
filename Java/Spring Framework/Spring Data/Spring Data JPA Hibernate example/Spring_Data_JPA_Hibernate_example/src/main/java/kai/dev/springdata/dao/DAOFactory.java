package kai.dev.springdata.dao;

import kai.dev.springdata.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public enum DAOFactory {
    
    INSTANCE;
    
    private final ApplicationContext appContext;   
    
    DAOFactory(){
        appContext = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    public ContinentDAO getContinentDAO(){
        ContinentDAO continentDAO = appContext.getBean(ContinentDAO.class);
        return continentDAO;
    }

    public LocationDAO getLocationDAO(){
        LocationDAO locationDAO = appContext.getBean(LocationDAO.class);
        return locationDAO;
    }

    public ServerDAO getServerDAO()
    {
        ServerDAO serverDAO = appContext.getBean(ServerDAO.class);
        return serverDAO;
    }
}
