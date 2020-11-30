package net.ddns.leekm.eco_design_app;

import android.app.Application;

public class AppData extends Application {
    private 사용자 user = new 사용자();
    public 사용자 getUser() {
        return user;
    }

    public void setUser(사용자 user) {
        this.user = user;
    }


}
