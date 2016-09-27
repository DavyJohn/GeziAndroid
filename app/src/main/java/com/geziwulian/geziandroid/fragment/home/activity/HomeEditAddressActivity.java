package com.geziwulian.geziandroid.fragment.home.activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.utils.Constant;
import com.geziwulian.geziandroid.utils.JsonFileReader;
import com.geziwulian.geziandroid.utils.MyAddresseeProvider;
import com.geziwulian.geziandroid.utils.MyContentProvider;
import com.geziwulian.geziandroid.utils.ProvinceBean;
import com.geziwulian.geziandroid.utils.SqliteTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 志浩 on 2016/9/25.
 * 修改地址
 */

public class HomeEditAddressActivity  extends BaseActivity{

    @BindView(R.id.home_edit_address_name_et)
    EditText mEditName;
    @BindView(R.id.home_edit_address_phone_et)
    EditText mEditPhone;
    @BindView(R.id.home_edit_address_detail_et)
    EditText mEditDetail;
    @BindView(R.id.home_add_address_info_location_text)
    TextView mTextLocation;
    @BindView(R.id.home_edit_address_toolbar)
    Toolbar mToolbar;

    @OnClick(R.id.home_add_address_info_location_text) void clickLocation(){
        hintKbTwo();
        pvOptions.show();
    }
    private Cursor cursor;
    private String name,phone,address;
    private int key;
    private OptionsPickerView pvOptions;
    //  省份
    ArrayList<ProvinceBean> provinceBeanList = new ArrayList<>();
    //  城市
    ArrayList<String> cities;
    ArrayList<List<String>> cityList = new ArrayList<>();
    //  区/县
    ArrayList<String> district;
    ArrayList<List<String>> districts;
    ArrayList<List<List<String>>> districtList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_edit_address_layout);
        key = getIntent().getIntExtra("key",-1);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save,menu);
        return super.onCreateOptionsMenu(menu);

    }

    private void initView(){
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.back_icon);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (Constant.HOME_EDIT == 0){
            cursor = getContentResolver().query(MyContentProvider.URI,null,"_id=?",new String[]{String.valueOf(key)},null);
            while (cursor.moveToNext()){
                mEditDetail.setText(cursor.getString(cursor.getColumnIndex("userAAddress")));
                mEditName.setText(cursor.getString(cursor.getColumnIndex("userName")));
                mEditPhone.setText(cursor.getString(cursor.getColumnIndex("userPhone")));
            }
        }else if (Constant.HOME_EDIT == 1){
            cursor = getContentResolver().query(MyAddresseeProvider.URI,null,"key_id=?",new String[]{String.valueOf(key)},null);
            while (cursor.moveToNext()){
                mEditDetail.setText(cursor.getString(cursor.getColumnIndex("userAddresseeInfo")));
                mEditName.setText(cursor.getString(cursor.getColumnIndex("userAddresseeName")));
                mEditPhone.setText(cursor.getString(cursor.getColumnIndex("userAddresseePhone")));
            }
        }
        pvOptions = new OptionsPickerView(mContext);
        //  获取json数据
        String province_data_json = JsonFileReader.getJson(this, "province_data.json");
        //  解析json数据
        parseJson(province_data_json);

        //  设置三级联动效果
        pvOptions.setPicker(provinceBeanList, cityList, districtList, true);

        //  设置选择的三级单位
//        pvOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择城市");
        //  设置是否循环滚动
        pvOptions.setCyclic(false, false, false);
        // 设置默认选中的三级项目
        pvOptions.setSelectOptions(0, 0, 0);

        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String city = provinceBeanList.get(options1).getPickerViewText();
                String address;
                //  如果是直辖市或者特别行政区只设置市和区/县
                if ("北京市".equals(city) || "上海市".equals(city) || "天津市".equals(city) || "重庆市".equals(city) || "澳门".equals(city) || "香港".equals(city)) {
                    address = provinceBeanList.get(options1).getPickerViewText()
                            + " " + districtList.get(options1).get(option2).get(options3);
                } else {
                    address = provinceBeanList.get(options1).getPickerViewText()
                            + " " + cityList.get(options1).get(option2)
                            + " " + districtList.get(options1).get(option2).get(options3);
                }
                mTextLocation.setText(address);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save){
            //TODO 修改保存
            name = mEditName.getText().toString();
            phone = mEditPhone.getText().toString();
            address = mEditDetail.getText().toString();
            if (Constant.HOME_EDIT == 0){
                SqliteTool.getInstance().EditData(mContext,key,name,phone,address);
            }else if (Constant.HOME_EDIT == 1){
                SqliteTool.getInstance().EditAddresseeData(mContext,key,name,phone,address);
            }
            Constant.HOME_EDIT  = -1;
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void parseJson(String str) {
        try {
            //  获取json中的数组
            JSONArray jsonArray = new JSONArray(str);
            //  遍历数据组
            for (int i = 0; i < jsonArray.length(); i++) {
                //  获取省份的对象
                JSONObject provinceObject = jsonArray.optJSONObject(i);
                //  获取省份名称放入集合
                String provinceName = provinceObject.getString("name");
                provinceBeanList.add(new ProvinceBean(provinceName));
                //  获取城市数组
                JSONArray cityArray = provinceObject.optJSONArray("city");
                cities = new ArrayList<>();//   声明存放城市的集合
                districts = new ArrayList<>();//声明存放区县集合的集合
                //  遍历城市数组
                for (int j = 0; j < cityArray.length(); j++) {
                    //  获取城市对象
                    JSONObject cityObject = cityArray.optJSONObject(j);
                    //  将城市放入集合
                    String cityName = cityObject.optString("name");
                    cities.add(cityName);
                    district = new ArrayList<>();// 声明存放区县的集合
                    //  获取区县的数组
                    JSONArray areaArray = cityObject.optJSONArray("area");
                    //  遍历区县数组，获取到区县名称并放入集合
                    for (int k = 0; k < areaArray.length(); k++) {
                        String areaName = areaArray.getString(k);
                        district.add(areaName);
                    }
                    //  将区县的集合放入集合
                    districts.add(district);
                }
                //  将存放区县集合的集合放入集合
                districtList.add(districts);
                //  将存放城市的集合放入集合
                cityList.add(cities);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()&&getCurrentFocus()!=null){
            if (getCurrentFocus().getWindowToken()!=null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        pvOptions.dismiss();
    }
}
