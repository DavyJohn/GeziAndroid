package com.geziwulian.geziandroid;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 志浩 on 2016/9/13.
 */
public class MapViewActivity extends BaseActivity implements AMapLocationListener,LocationSource  {

    @BindView(R.id.map_main_activity_map)
    MapView mMapView;
    @BindView(R.id.map_main_activity_et_text)
    EditText mEText;
    private AMap aMap;
    private UiSettings mUiSetting;
    private LocationManager manager;
    private Location location;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption = null;
    private Marker marker;
    private PoiSearch poiSearch;
    private PoiSearch.Query query;
    private LocationSource.OnLocationChangedListener mListener;
    //绘制线
    List<LatLng> latLngs = new ArrayList<LatLng>();
    private double lat,lon;
    private boolean isClearnMap = false;
    private String cityCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_main_activity);
        mMapView.onCreate(savedInstanceState);
        mEText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                initSearch(null,charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mEText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH){
                        initSearch(textView,null);
                }
                return true;
            }
        });
    }

    private void initSearch(TextView textView,CharSequence charSequence){
        if (charSequence == null){
            query = new PoiSearch.Query(textView.getText().toString(),"",cityCode);
        }else if (textView == null){
            query = new PoiSearch.Query(charSequence.toString(),"",cityCode);
        }
        query.setPageSize(100);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();

    }

    private void initData(){
        if (aMap == null){
            aMap = mMapView.getMap();
            mUiSetting = aMap.getUiSettings();
        }
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mlocationClient = new AMapLocationClient(this);
        mLocationOption = new AMapLocationClientOption();
        mlocationClient.setLocationListener(this);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
//        mLocationOption.setInterval(2000);

        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    //停止定位
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }


    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        isClearnMap = true;
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                if (isClearnMap = true){
                    aMap.clear();
                }
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                lat = amapLocation.getLatitude();//获取纬度
                lon = amapLocation.getLongitude();//获取经度
                cityCode = amapLocation.getCityCode();
                latLngs .add(new LatLng(lat,lon));
                aMap.addPolyline(new PolylineOptions().addAll(latLngs).width(10).color(ContextCompat.getColor(mContext,R.color.red)));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(lat,lon)).title("我在这").snippet("我的坐标：").draggable(true).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.location_icon)));
                markerOptions.setFlat(true);
                marker = aMap.addMarker(markerOptions);
                marker.showInfoWindow();
                aMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(lat,lon),18,45,0)));
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {

    }
}
