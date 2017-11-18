package com.bigpush.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;
import com.bigpush.R;
import com.bigpush.util.CallServer;
import com.bigpush.util.Constant;
import com.bigpush.util.SystemUtils;
import com.bigpush.util.UserUtils;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.Response;
import org.json.JSONException;
import org.json.JSONObject;
import pub.devrel.easypermissions.EasyPermissions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends BaseActivity {
    private int DEVICEREGWHAT = Constant.NET_WHAT++;
    private int INITUSERWHAT = Constant.NET_WHAT++;
    private EditText et_code;
    private TextView tv_reg;

    private Boolean relogin=false;//用来记录是不是重新登录

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_code= (EditText) findViewById(R.id.et_code);
        tv_reg= (TextView) findViewById(R.id.tv_reg);
        getPermission();

        relogin=getIntent().getBooleanExtra("relogin",false);
    }

    private void gotoMainAtivity(){
        if (TextUtils.isEmpty(UserUtils.getUserCode(this))) {
            getKey();
        }else{
            if(!relogin){
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
            finish();
        }
    }

    /**
     * 动态获取权限
     */
    private void getPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
//            android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.CALL_PHONE, android.Manifest.permission.READ_LOGS, android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.SET_DEBUG_APP, android.Manifest.permission.SYSTEM_ALERT_WINDOW, android.Manifest.permission.GET_ACCOUNTS, android.Manifest.permission.WRITE_APN_SETTINGS, Manifest.permission.WRITE_SETTINGS
//            String[] mPermissionList = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.CALL_PHONE, android.Manifest.permission.READ_LOGS, android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.SET_DEBUG_APP, android.Manifest.permission.SYSTEM_ALERT_WINDOW, android.Manifest.permission.GET_ACCOUNTS, android.Manifest.permission.WRITE_APN_SETTINGS, Manifest.permission.WRITE_SETTINGS};
//            ActivityCompat.requestPermissions(this, mPermissionList, 123);
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_PHONE_STATE)&&EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                gotoMainAtivity();
            }else{
//                String[] mPermissionList = new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,  android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.READ_EXTERNAL_STORAGE};
                String[] mPermissionList = new String[]{android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.READ_EXTERNAL_STORAGE};
//                String[] mPermissionList = new String[]{android.Manifest.permission.READ_PHONE_STATE};
                ActivityCompat.requestPermissions(this, mPermissionList, 123);

//                EasyPermissions.requestPermissions(
//                        this,
//                        "请授权",
//                        123,
//                        Manifest.permission.READ_PHONE_STATE);
            }

        }else{
            gotoMainAtivity();
        }
    }

    /**
     * 到服务器获取key
     */
    private void getKey() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.deviceReg, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("mobileModel", SystemUtils.getSystemModel());
        param.put("mobileSysType", "android");
        param.put("mobileCode", SystemUtils.getImei());
        param.put("appVer", SystemUtils.getVersionCode());
        param.put("mobileSysVer", SystemUtils.getSystemVersion());
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(DEVICEREGWHAT, jsonObjectRequest, this);
    }

    @Override
    public void onclick(View view) {
        super.onclick(view);
        switch (view.getId()){
            case R.id.tv_reg:
                getKey();
                break;
            case R.id.tv_commit:
                if(UserUtils.getKey(LoginActivity.this)!=null&&!TextUtils.isEmpty(UserUtils.getKey(LoginActivity.this))){
                    if(TextUtils.isEmpty(et_code.getText())){
                        SystemUtils.showText("请输入邀请码");
                    }else{
                        initUser();
                    }
                }else{
                    tv_reg.setVisibility(View.VISIBLE);
                    SystemUtils.showText("用户手机硬件注册失败");
                }
                break;
        }
    }


    /**
     * 到服务器初始化游客信息
     */
    private void initUser() {
        if (TextUtils.isEmpty(UserUtils.getUserCode(this))) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.userInit, RequestMethod.POST);
            Map<String, Object> param = new HashMap<>();
            param.put("numCode", et_code.getText().toString().trim());
            UserUtils.saveRec(this,et_code.getText().toString().trim());
            param.put("key", UserUtils.getKey(this));
            param.put("registrationID", JPushInterface.getRegistrationID(LoginActivity.this));
            Log.d("uzinfo","Id:   "+JPushInterface.getRegistrationID(LoginActivity.this));
            jsonObjectRequest.add(param);
            CallServer.getInstance().add(INITUSERWHAT, jsonObjectRequest, this);
        }
    }

    @Override
    public void onSucceed(int what, Response response) {

        Object object = response.get();

        JSONObject jsonObject = (JSONObject) object;
        try {
            if (jsonObject != null &&1==jsonObject.getInt("status")&& jsonObject.has("Data")) {
                if (DEVICEREGWHAT == what) {
                    UserUtils.saveKey(LoginActivity.this, jsonObject.getJSONObject("Data").getString("key"));
                } else if (INITUSERWHAT == what) {
                    UserUtils.saveUserCode(LoginActivity.this, jsonObject.getJSONObject("Data").getString("userCode"));
//                    if(!relogin){
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
//                    }
                    finish();
                }
            }else{
//                Toast.makeText(LoginActivity.this, jsonObject.getString("errorMsg"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailed(int what, Response response) {
        if (DEVICEREGWHAT == what) {
            Toast.makeText(LoginActivity.this, "设备注册失败！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List perms) {
        super.onPermissionsGranted(requestCode, perms);
//        SystemUtils.showText("onPermissionsGranted");
        gotoMainAtivity();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List perms) {
        super.onPermissionsDenied(requestCode, perms);
//        SystemUtils.showText("onPermissionsDenied");
        finish();
    }
}
