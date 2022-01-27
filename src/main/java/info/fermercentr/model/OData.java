package info.fermercentr.model;

public final class OData {

    private final String url;
    private final String user;
    private final String pass;

    public OData(final String purl, final String puser, final String ppass) {
        this.url = purl;
        this.user = puser;
        this.pass = ppass;
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
}
