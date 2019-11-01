package nl.dfbackend.git.resources;


import nl.dfbackend.git.persistence.VehiclePersistence;

import java.util.ArrayList;

import java.sql.*;

public class VehicleResource {
    VehiclePersistence vehiclePersistence = new VehiclePersistence();

    public void donothing() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
        Connection conn = DriverManager.getConnection(url);
        // Do stuff here
        conn.close();
    }

        public boolean postVehicle(String licenseplate, String vehicleName, String vehicleType){
        return true;
    }

    public ArrayList<String> getVehicle(int vehicleId){
        return null;
    }

    public ArrayList<String> getVehicles(){
        return null;
    }
}
