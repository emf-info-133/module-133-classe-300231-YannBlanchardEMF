package client.servicerestclient.ctrl;

import org.springframework.web.bind.annotation.GetMapping;

import client.servicerestclient.wrk.ItfWrkCtrl;

public class Ctrl {

    ItfWrkCtrl wrk;

    @GetMapping("/testdb")
    public boolean testDB() {
        return wrk.checkUser("alice123");
    }
}
