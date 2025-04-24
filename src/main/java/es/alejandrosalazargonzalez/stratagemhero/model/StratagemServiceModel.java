package es.alejandrosalazargonzalez.stratagemhero.model;

import java.sql.*;
import java.util.*;

import es.alejandrosalazargonzalez.stratagemhero.model.conexion.Conexion;

public class StratagemServiceModel extends Conexion {

    public StratagemServiceModel() throws SQLException {
        super("jdbc:sqlite:src/main/resources/stratagemas.db");
    }

    /**
     * saca todas las stratagemas de la base de datos
     * @return  List<Stratagem> 
     */
    public List<Stratagem> obtenerStratagemas() {
        List<Stratagem> stratagems = new ArrayList<>();
        String query = "SELECT * FROM stratagemas";

        try (Connection conexion = getConnection();
                Statement stmt = conexion.createStatement();
                ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String secuenciaStr = resultSet.getString("secuencia");
                List<String> secuencia = Arrays.asList(secuenciaStr.split(","));
                stratagems.add(new Stratagem(nombre, secuencia));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stratagems;
    }
}