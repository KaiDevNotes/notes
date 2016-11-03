package com.kai.dev.jdbcdao.entity.builder;

import com.kai.dev.jdbcdao.entity.Identifiable;
import com.kai.dev.jdbcdao.entity.Server;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ServerBuilder implements EntityBuilder {

    @Override
    public Identifiable build(ResultSet serverResultSet) throws SQLException {
        
        Server server = new Server();
        
        int id = serverResultSet.getInt(Server.FIELD_ID);
        server.setId(id);
        
        String name = serverResultSet.getString(Server.FIELD_NAME);
        server.setName(name);
        
        int locationId = serverResultSet.getInt(Server.FIELD_LOCATION_ID);
        server.setLocationId(locationId);
        
        return server;
    }    
}
