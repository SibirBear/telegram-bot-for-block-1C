package info.fermercentr.config;

public class ConfigMSDB {

    private final String host;
    private final String user;
    private final String psw;
    private final String table;

    public ConfigMSDB(final String pHost, final String pUser, final String pPsw, final String pTable) {
        this.host = pHost;
        this.user = pUser;
        this.psw = pPsw;
        this.table = pTable;
    }

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getPsw() {
        return psw;
    }

    public String getTable() {
        return table;
    }

}
