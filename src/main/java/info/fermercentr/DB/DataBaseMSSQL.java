package info.fermercentr.DB;

import info.fermercentr.config.Config;
import info.fermercentr.model.Order;
import info.fermercentr.store.SessionData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static info.fermercentr.DB.DataBaseConstants.CONST_INDEX_FOUR;
import static info.fermercentr.DB.DataBaseConstants.CONST_INDEX_ONE;
import static info.fermercentr.DB.DataBaseConstants.CONST_INDEX_THREE;
import static info.fermercentr.DB.DataBaseConstants.CONST_INDEX_TWO;

public class DataBaseMSSQL {

    private static final Logger log = LogManager.getLogger(DataBaseMSSQL.class);

    private static final String HOSTMS = Config.getConfigDBMS().getHost();
    private static final String USERMS = Config.getConfigDBMS().getUser();
    private static final String PSWMS = Config.getConfigDBMS().getPsw();
    private static final String TABLEMS = Config.getConfigDBMS().getTable();
    private static final String INSERT_STRING = "INSERT INTO " + TABLEMS
            + "(TSF_ENTITY_ID, ADD_BLOCK, EVENT_DATE, DATETIME_STAMP) "
            + "VALUES (?,?,?,?)";

    public static boolean insertDB (final long idClient, final SessionData sd) {

        Order sdOrder = sd.getOrder(idClient);
        String id = sdOrder.getIdClient();
        boolean isBlock = sdOrder.isActionBlock();
        String date = sdOrder.getDate();
        String time = sdOrder.getTime();
        LocalDateTime dateEvent = null;

        if (date != null && time != null) {
            dateEvent = LocalDateTime.of(
                    LocalDate.parse(date), LocalTime.parse(time));
        }

        log.info("[DataBaseMSSQL] - Trying to connect DB...");

        try (Connection con = connect(); PreparedStatement stmt = con.prepareStatement(INSERT_STRING)) {
            log.info("[DataBaseMSSQL] - Connect to DB successful!");

            stmt.setString(CONST_INDEX_ONE, id);
            stmt.setBoolean(CONST_INDEX_TWO, isBlock);
            if (dateEvent != null) {
                stmt.setTimestamp(CONST_INDEX_THREE, Timestamp.valueOf(dateEvent));
            } else {
                stmt.setNull(CONST_INDEX_THREE, Types.TIMESTAMP);
            }
            stmt.setTimestamp(CONST_INDEX_FOUR, Timestamp.valueOf(LocalDateTime.now().withNano(0)));
            stmt.executeUpdate();

            log.info("[DataBaseMSSQL] - The data is transferred to DB.");
            return true;

        } catch (SQLException e) {
            log.error("[DataBaseMSSQL] - Connect to DB failed! " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private static Connection connect() throws SQLException {
        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        return DriverManager.getConnection(HOSTMS, USERMS, PSWMS);

    }

}
