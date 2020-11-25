package com.example.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;

public class info1 extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info1);

        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if(mapFragment == null){
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.map,mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

    }
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        //지도 유형
        naverMap.setMapType( NaverMap.MapType.Navi);

        //심벌 크기
        naverMap.setSymbolScale(1.5f);

        //각 지점 위도, 경도 설정
        LatLng latLng1 = new LatLng(35.19499500908695,128.0588906507621);

        //지도 중심부
        CameraUpdate cameraUpdate1 = CameraUpdate.scrollTo(latLng1);
        naverMap.moveCamera(cameraUpdate1);

        //지도 사이즈
        CameraUpdate cameraUpdate2 = CameraUpdate.zoomTo(15);
        naverMap.moveCamera(cameraUpdate2);

        // 매장 1
        Marker marker1 = new Marker();
        marker1.setPosition(latLng1);
        marker1.setMap(naverMap);

        marker1.setSubCaptionText("에코건축자재");
        marker1.setCaptionColor(Color.RED);
        marker1.setSubCaptionHaloColor(Color.YELLOW);
        marker1.setSubCaptionTextSize(10);

        InfoWindow infoWindow1 = new InfoWindow();
        infoWindow1.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "에코건축자재";
            }
        });
        infoWindow1.open(marker1);

    }
}
