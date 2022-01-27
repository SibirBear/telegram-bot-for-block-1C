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

    private final String url = Config.getConfigDB().getHost();
    private final String user = Config.getConfigDB().getUser();
    private final String pass = Config.getConfigDB().getPsw();

    public List<String> getData(final String idClient) {
        List<String> result = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        String query = "call select_connection(" + idClient + ")";

        try (Connection con = connect()) {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            int i = 1;
            while (rs.next()) {
                result.add(rs.getString(i));
                i++;
//                result.add(rs.getString(2));
//                result.add(rs.getString(3));
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
        return DriverManager.getConnection(url, user, pass);
    }

}
