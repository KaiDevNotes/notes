package DAO;

import DAO.Impl.ServerDAOImpl;
import DAO.Impl.LocationDAOImpl;
import DAO.Impl.ContinentDAOImpl;

public class Factory {
	  
    private static ServerDAO serverDAO = null;
    private static LocationDAO locationDAO = null;
    private static ContinentDAO continentDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
          instance = new Factory();
        }
        return instance;
    }	

    public ServerDAO getServerDAO(){
        if (serverDAO == null){
          serverDAO = new ServerDAOImpl();
        }
        return serverDAO;
    }

    public LocationDAO getLocationDAO(){
        if (locationDAO == null){
          locationDAO = new LocationDAOImpl();
        }
        return locationDAO;
    }

    public ContinentDAO getContinentDAO(){
        if (continentDAO == null){
          continentDAO = new ContinentDAOImpl();
        }
        return continentDAO;
    }
}
