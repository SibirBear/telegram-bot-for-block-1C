package info.fermercentr.config;

public class ConfigDB {

    private final String host;
    private final String port;
    private final String user;
    private final String psw;

    public ConfigDB(final String host, final String port, final String user, final String psw) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.psw = psw;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPsw() {
        return psw;
    }
}
