package info.fermercentr.config;

public final class ConfigDB {

    private final String host;
    private final String user;
    private final String psw;

    public ConfigDB(final String phost, final String puser, final String ppsw) {
        this.host = phost;
        this.user = puser;
        this.psw = ppsw;
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
}
