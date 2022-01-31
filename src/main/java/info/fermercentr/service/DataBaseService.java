package info.fermercentr.service;

import info.fermercentr.config.Config;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class DataBaseService {

    private final String URL = Config.getConfigDB().getHost();
    private final String USER = Config.getConfigDB().getUser();
    private final String PASS = Config.getConfigDB().getPsw();
    private final String PROCEDURE = Config.getConfigDB().getProcedure();
    private final int CONST_INDEX_ONE = 1;
    private final int CONST_INDEX_TWO = 2;
    private final int CONST_INDEX_THREE = 3;

    public List<String> getData(final String idClient) {
        List<String> result = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        String query = PROCEDURE + "(" + idClient + ")";

        try (Connection con = connect()) {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                result.add(rs.getString(CONST_INDEX_ONE));
                result.add(rs.getString(CONST_INDEX_TWO));
                result.add(rs.getString(CONST_INDEX_THREE));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return result;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
