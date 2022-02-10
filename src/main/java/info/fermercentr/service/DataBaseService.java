package info.fermercentr.service;

import com.mysql.jdbc.Driver;
import info.fermercentr.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseService {

    private final Logger log = LogManager.getLogger(this.getClass());

    private final String URL = Config.getConfigDB().getHost();
    private final String USER = Config.getConfigDB().getUser();
    private final String PASS = Config.getConfigDB().getPsw();
    private final String PROCEDURE = Config.getConfigDB().getProcedure();
    private final int CONST_INDEX_ONE = 1;
    private final int CONST_INDEX_TWO = 2;
    private final int CONST_INDEX_THREE = 3;
    private final int CONST_INDEX_FOUR = 4;

    public List<String> getData(final String idClient) {
        List<String> result = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        String query = PROCEDURE + "(" + idClient + ")";

        log.info("[DataBase] - Trying to connect DB...");

        try (Connection con = connect()) {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                result.add(rs.getString(CONST_INDEX_ONE));
                result.add(rs.getString(CONST_INDEX_TWO));
                result.add(rs.getString(CONST_INDEX_THREE));
                result.add(rs.getString(CONST_INDEX_FOUR));
            }
            log.info("[DataBase] - Connect to DB successful!");

        } catch (SQLException e) {
            log.error("[DataBase] - Connect to DB failed! " + e.getMessage());
            e.printStackTrace();
        }

        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    log.error("[DataBase] - Error while close statement! " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    log.error("[DataBase] - Error while close Resultset SQL! " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }

        return result;
    }

    private Connection connect() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
