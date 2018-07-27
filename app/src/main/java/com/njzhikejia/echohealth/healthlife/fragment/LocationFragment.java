package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.util.LogTime;
import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.entity.LocationData;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 16222 on 2018/7/25.
 */

public class LocationFragment extends BaseFragment {

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private MyLocationListener myListener = new MyLocationListener();
    private Context mContext;
    private static final String TAG = "LocationFragment";
    private static final int KEY_LOCATION = 23;
    private double latitude;
    private double longitude;
    private LocationHandler mHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
        mContext = getActivity();
        mLocationClient = new LocationClient(getContext().getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();

        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true

        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_location, null);
        initView(view);
        mHandler = new LocationHandler(this);
        return view;
    }

    private void initView(View view) {
        mMapView =  view.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(16).build()));
//        mLocationClient.start();
        queryLocation();
    }

    static class LocationHandler extends Handler{

        private WeakReference<LocationFragment> weakReference;

        public LocationHandler(LocationFragment fragment) {
            weakReference = new WeakReference<LocationFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case KEY_LOCATION:
                    LocationFragment fragment = weakReference.get();
                    if (fragment != null) {
                        Logger.d(TAG, "latitude = "+String.valueOf(fragment.latitude) + "longitude = "+String.valueOf(fragment.longitude));
                        LatLng ll = new LatLng(fragment.latitude, fragment.longitude);
                        MyLocationData locData = new MyLocationData.Builder()
                                .latitude(fragment.latitude).longitude(fragment.longitude).build();
                        fragment.mBaiduMap.setMyLocationData(locData);
                        MapStatus.Builder builder = new MapStatus.Builder();
                        //设置缩放中心点；缩放比例；
                        builder.target(ll).zoom(18.0f);
                        fragment.mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                    }

                    break;
            }

        }
    }

    private void queryLocation() {
        OKHttpClientManager.getInstance().getAsync(CommonRequest.getLocation(PreferenceUtil.getUID(mContext)), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "queryLocation failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseContent = response.body().string();
                Logger.d(TAG, "onResponse code = "+response.code() + "responseContent = "+responseContent);
                Gson gson = new Gson();
                LocationData locationData = gson.fromJson(responseContent, LocationData.class);
                Logger.d(TAG, "address = "+locationData.getData().getLocations().get(0).getAddress());
                latitude = locationData.getData().getLocations().get(0).getLatitude();
                longitude = locationData.getData().getLocations().get(0).getLongitude();
                mHandler.sendEmptyMessage(KEY_LOCATION);
            }
        });
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            if (location == null) {
                return;
            }
//            MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius()).direction(100)
//                    .latitude(location.getLatitude()).longitude(location.getLongitude()).build();
//            mBaiduMap.setMyLocationData(locData);
//                        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
//                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
//                mBaiduMap.animateMapStatus(u);


        }
    }

    private void showCurrentPosition() {

    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
}
