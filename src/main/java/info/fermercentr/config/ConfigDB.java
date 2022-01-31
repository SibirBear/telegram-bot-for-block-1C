package info.fermercentr.config;

public final class ConfigDB {

    private final String host;
    private final String user;
    private final String psw;
    private final String procedure;

    public ConfigDB(final String pHost, final String pUser, final String pPsw, final String pProcedure) {
        this.host = pHost;
        this.user = pUser;
        this.psw = pPsw;
        this.procedure = pProcedure;
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

    public String getProcedure() {
        return procedure;
    }
}
