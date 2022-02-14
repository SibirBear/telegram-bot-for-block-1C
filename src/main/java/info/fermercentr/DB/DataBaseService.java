package info.fermercentr.DB;

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

import static info.fermercentr.DB.DataBaseConstants.CONST_INDEX_FOUR;
import static info.fermercentr.DB.DataBaseConstants.CONST_INDEX_ONE;
import static info.fermercentr.DB.DataBaseConstants.CONST_INDEX_THREE;
import static info.fermercentr.DB.DataBaseConstants.CONST_INDEX_TWO;

public class DataBaseService {

    private final Logger log = LogManager.getLogger(this.getClass());

    private final String URL = Config.getConfigDB().getHost();
    private final String USER = Config.getConfigDB().getUser();
    private final String PASS = Config.getConfigDB().getPsw();
    private final String PROCEDURE = Config.getConfigDB().getProcedure();

    public List<String> getData(final String idClient) {
        List<String> result = new ArrayList<>();

        String query = PROCEDURE + "(" + idClient + ")";

        log.info("[DataBaseMySQL] - Trying to connect DB...");

        try (Connection con = connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                result.add(rs.getString(CONST_INDEX_ONE));
                result.add(rs.getString(CONST_INDEX_TWO));
                result.add(rs.getString(CONST_INDEX_THREE));
                result.add(rs.getString(CONST_INDEX_FOUR));
            }
            log.info("[DataBaseMySQL] - Connect to DB successful!");

        } catch (SQLException e) {
            log.error("[DataBaseMySQL] - Connect to DB failed! " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    private Connection connect() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
