package DAO;

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
          serverDAO = new ServerDAO();
        }
        return serverDAO;
    }

    public LocationDAO getLocationDAO(){
        if (locationDAO == null){
          locationDAO = new LocationDAO();
        }
        return locationDAO;
    }

    public ContinentDAO getContinentDAO(){
        if (continentDAO == null){
          continentDAO = new ContinentDAO();
        }
        return continentDAO;
    }
}
