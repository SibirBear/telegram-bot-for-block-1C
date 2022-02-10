package info.fermercentr.model;

public final class OData {

    private final String url;
    private final String user;
    private final String pass;
    private final String name;

    public OData(final String pUrl, final String pUser, final String pPass, final String pName) {
        this.url = pUrl;
        this.user = pUser;
        this.pass = pPass;
        this.name = pName;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }
}
