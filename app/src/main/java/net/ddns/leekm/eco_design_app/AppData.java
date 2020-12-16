package net.ddns.leekm.eco_design_app;

import android.Manifest;
import android.app.Application;

public class AppData extends Application {
    //서버 정보
    public static final String SERVER_IP = "220.66.115.40";
    public static final String SERVER_URL = "http://"+SERVER_IP;
    public static final String SERVER_PORT = "5005";
    public static final String SERVER_FULL_URL = SERVER_URL+":"+SERVER_PORT;
    // 어플리케이션 동작을 위해 사용자의 동의가 필요한 퍼미션을 정의.
    public static String[] REQUIRED_PERMISSIONS  = {
            // 쓰기 권한
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            // 읽기 권한
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    // 로그인한 사용자의 정보를 전역적으로 관리해줄 필드
    private 사용자 user;
    public 사용자 getUser() {
        return user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.user =  new 사용자();
    }
}
