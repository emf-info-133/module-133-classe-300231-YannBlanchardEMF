package client.servicerestclient.wrk;

public class Wrk implements ItfWrkCtrl {
    private WrkDB wrkDB;

    public Wrk() {
        wrkDB = new WrkDB();
    }

    @Override
    public boolean checkUser(String login) {
        return wrkDB.readUser(login);
    }
}
