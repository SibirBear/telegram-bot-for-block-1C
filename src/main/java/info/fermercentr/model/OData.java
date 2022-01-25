package info.fermercentr.model;

public class OData {

    private final String url;
    private final String user;
    private final String pass;

    public OData(final String url, final String user, final String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
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
