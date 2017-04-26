package com.example.androidencryptiondemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidencryptiondemo.R;
import com.example.androidencryptiondemo.ndk.NdkUtil;
import com.example.androidencryptiondemo.util.Base64Decoder;
import com.example.androidencryptiondemo.util.Base64Encoder;
import com.example.androidencryptiondemo.util.LogUtils;
import com.example.androidencryptiondemo.util.RSAJAUtils;
import com.example.androidencryptiondemo.util.ToastUtils;
import com.example.androidencryptiondemo.view.LoadingDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class RSAEncryptionPasswordActivity extends AppCompatActivity {

    @BindView(R.id.btn_generate_public_private_keys)
    Button btnGeneratePublicPrivateKeys;
    @BindView(R.id.et_original)
    EditText etOriginal;
    @BindView(R.id.et_public_key)
    EditText etPublicKey;
    @BindView(R.id.et_private_key)
    EditText etPrivateKey;
    @BindView(R.id.btn_encryption)
    Button btnEncryption;
    @BindView(R.id.btn_rsa_encry_password_login)
    Button btn_rsa_encry_password_login;
    @BindView(R.id.tv_encrypted_ciphertext)
    TextView tvEncryptedCiphertext;
    @BindView(R.id.btn_decrypted)
    Button btnDecrypted;
    @BindView(R.id.tv_decrypted_out_original)
    TextView tvDecryptedOutOriginal;
    private Context TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsaencryption_password);
        ButterKnife.bind(this);


        initData(this);

    }

    public LoadingDialog loadingDialog;

    private void initData(Context context) {
        TAG = context;

//        etOriginal.setText("123456");//初始密码默认123456
        etOriginal.setText("000000");//初始密码默认123456

        etPublicKey.setText(new NdkUtil().getRsaPublicKey());//c中去取公钥
        etPrivateKey.setText(new NdkUtil().getRsaPrivateKey());//c中去取私钥

    }

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    private String encryStr;
    private String decryStr;

    @OnClick({R.id.btn_encryption, R.id.btn_decrypted, R.id.btn_rsa_encry_password_login, R.id.btn_generate_public_private_keys})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_generate_public_private_keys: {//生成秘钥对,公钥/私钥

                //生成秘钥对
                KeyPair keyPair = RSAJAUtils.generateRSAKeyPair(RSAJAUtils.DEFAULT_KEY_SIZE);

                // 公钥
                publicKey = (RSAPublicKey) keyPair.getPublic();
                publicKey.getAlgorithm();//加密方式："RSA"
                byte[] bytesPublicKey = publicKey.getEncoded();
                String publicKeyStr = Base64Encoder.encode(bytesPublicKey);//
//
                etPublicKey.setText(publicKeyStr);//公钥

                // 私钥
                privateKey = (RSAPrivateKey) keyPair.getPrivate();
                byte[] bytesPrivateKey = privateKey.getEncoded();
                String privateKeyStr = Base64Encoder.encode(bytesPrivateKey);//

                etPrivateKey.setText(privateKeyStr);//私钥
/*//MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA78MU1W64uiuxjiqE7+PekoSyJzDROqib
                2u6WG/YTbg7K9Rs6HXJndDXhPK6wkjVK+SyH5LrUeenDTcCZ3ibT6SwLN3M0bFZ0m8OtA33T4oNu
                3QYRbookbNJHkn/AHP0U24HulZt3ABGYPDJlce49uQaZW5stvN7WfOf9rO2wTBr7WWqBtyoizllj
                7lmLSWsVD1pEQ/4OMbMfqgn9ChQyNBX1CVGYCsqUAK9gpqNn7agu1SZXBXzmHcZjnl0Ekka4QZOW
                z740faEOfSISd87hkVkLP8AR+GlXf9FnYXzK4fz/Zk104BDlEwVHX0ZjqaNl2aBi3Bn+fpNVkNRa
                JRipQQIDAQAB*/
/*//MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDvwxTVbri6K7GOKoTv496ShLIn
                        MNE6qJva7pYb9hNuDsr1Gzodcmd0NeE8rrCSNUr5LIfkutR56cNNwJneJtPpLAs3czRsVnSbw60D
                fdPig27dBhFuiiRs0keSf8Ac/RTbge6Vm3cAEZg8MmVx7j25Bplbmy283tZ85/2s7bBMGvtZaoG3
                KiLOWWPuWYtJaxUPWkRD/g4xsx+qCf0KFDI0FfUJUZgKypQAr2Cmo2ftqC7VJlcFfOYdxmOeXQSS
                RrhBk5bPvjR9oQ59IhJ3zuGRWQs/wBH4aVd/0WdhfMrh/P9mTXTgEOUTBUdfRmOpo2XZoGLcGf5+
                        k1WQ1FolGKlBAgMBAAECggEAfEIhUQACEtJEQga9QMpa9pp47iC5vunVZGCUQUeGCFr4SpFizBIX
                lb26zsVGU2/1PDAc+ajuJ4TEKE6yL9T1rHmcEo/NUnx4EKYXy6BxqIovcbMMY7qKaC+O0lkNCrkL
                DzxGlxisUsfLmx8PPj2XlYXbAwPob2MZzEA7U10YKkgsoD+VEKrDwWdernTFJ5hXZbb/WRJ0dKE1
                IU8VvLh1aan7TCQoLfscZVbfJvlYdGqmK7NUcnalCPg7B9vp8R0/fJkY8bYpxcvcNQ4/F6GSFHjt
                fkxDQCRkX1VFPefobgvXlSd/K/IGbgKUH6saIDDVvtogAlnI/EU1J8Yex0E0RQKBgQD/v+yn/jcx
                07/3Win1o9frQyQH9WH/z9GCUfJP8ErEkcNcr5K6fq+lNsmJMtFE27abacBtowujIDfAA8gFi6z7
                yop798sLmnLwyrgL1pl+hpWsxlZ0AEzQDRVNzN2mAdM2Jb6y2Uk3CMXMBZH428lqYFjuPp4AKOWW
                UuKlThmWCwKBgQDv/ybBkF7B4qkKXu6vVpbPsqxGVLs0DW/4kfQnNFDyEZsiFXpdfV4rRWRwlCi6
                csum5JeO0cfj4b42tMQ9nT6ZiAcJHCr/vBpJImAbGgJikR7mlCveUYj+HY9VE+DMPSWyPcDTK5My
                GTlp1QiligCO26H0k+Iv/TiUGW6Zx0/JYwKBgCEpj9RQwHNzk6bNSDeT4UGeWSO3C9gR6IbPfW4k
                KG3IwN5Z3drU5o2zxvpFXqFhJBKO6ebNh4rFeLUrSFp/vBSCNObSQL/mbRUQFEFkuE6RwQLATSGJ
                kGxB5gtKj0shXXPd/RZP2pCKUKud5UmxpjJm/+EUuYQnyW1unfj1YZf1AoGABz5SMyFiWsO2imOX
                HJ+maZpg7LZ99/I34O62ycAgeU3iusKfMNcp0nCDuYofOJFiaCUaFetq9wvHK+uEyL9INmYOoE3D
                Vnic6nwpHmhIjiUHI7pxIIKhedn2i4DQFhn6W0jV2wnDpY1xWCpf2m8x2R3z42fbOcuQu5eQ6P9E
                        /zUCgYBrrsCyyBzDDEmMw4xts2nuYtvBQre30xAOZrIh1QZ15feCm/D1ifRK23SvQKZa7+7nihAl
                6G0h6C2l4l9x3+Nnbxe2bPSiOcPW+Sdezzmu1IDdyYNSIOUvfpepb4Ue7FA9ztPKHZbuaczPeeVt
                UNJ3d4IIFZmzSLEyfQwpIz89yw==*/
            }
            break;
            case R.id.btn_encryption: {//公钥加密
                String original = etOriginal.getText().toString().trim();//原文
                if (TextUtils.isEmpty(original)) {
                    ToastUtils.show(this, R.string.p_i_original);
                    return;
                }
                String publicKey = etPublicKey.getText().toString().trim();//key公钥


                if (TextUtils.isEmpty(publicKey)) {
                    ToastUtils.show(this, R.string.p_i_public_key);
                    return;
                }
                byte[] publicKeybytes = Base64Decoder.decodeToBytes(publicKey);


                //公钥加密
                long start = System.currentTimeMillis();
                try {
//                    String cstr="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbO0k14SgZIFUiTDcHzdbEwZ/n5K4CIpvhBY7nl1mvxVVyhoBuZp10CrvghXuJSvXfAbAa+S2G8BdIHsLqYY2amzKyhlMMay+FwguRFT7CKuDd/MMCo5r16univz5YgmzLjdinVsp0+uVfls/1kBp+RodDsGicfXwZ9bo9ZEgtNwIDAQAB";
                    String cstr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwAhV2dz6UsMaS2gdJvAx8KDnqmz2Wa/h\n" +
                            "yVB3GEGiDMhcb2miP0jIZ40PW0YGNjHsJY9gg6CA2v5lo8eyyVrCIjpK6GSpTL5kDTJHLp+kJ+dG\n" +
                            "E87b/G4rNImAQy38PvuRLBhtmGl4HF9XWVDbxwJwK5K58a7HZo81yg+Z7UidbYEQai6OgrlMYEBI\n" +
                            "WRTySJxN0a95jc7ohooZ1Ore4H5R4bQkqvk7oz4naVSYqH6f2x5cbNuliaS6Ugs8+aNLRrdGWmw9\n" +
                            "lfEZ9nlfq7Cd8uBslcpN+ZvplKU+oSD416jKyxncna7SJaW+mrrNPE7UldDWHerTenUOyg0QkOww\n" +
                            "n5KqMwIDAQAB";

//                    //方式1
                    byte[] encryptBytes = RSAJAUtils.encryptByPublicKeyForSpilt(original.getBytes(), publicKeybytes);
//                    byte[] encryptBytes = RSAJAUtils.encryptByPublicKeyForSpilt(original.getBytes(),  Base64Decoder.decodeToBytes(cstr));
                    encryStr = Base64Encoder.encode(encryptBytes);

//                    //方式2
//                    encryStr = Base64.encode(RSAUtils0.encrypt(cstr, original.getBytes()));
////                    encryStr = Base64.encode(RSAUtils0.encrypt(publicKey, original.getBytes()));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                long end = System.currentTimeMillis();
                String timeConsuming = "公钥加密耗时 cost time---->" + (end - start) + "\n";
                LogUtils.d(timeConsuming);
//                String encryStr = new String(encryptBytes);//
                LogUtils.d("加密后数据 --1-->" + encryStr);
//                LogUtils.d("加密后数据长度 --1-->" + encryStr.length());

//                String encryStr = Base64Encoder.encode(encryptBytes);
//                LogUtils.d("加密后json数据 --1-->" + encryStr);
//                LogUtils.d("加密后json数据长度 --1-->" + encryStr.length());

                tvEncryptedCiphertext.setText(timeConsuming + getString(R.string.encrypted_ciphertext) + encryStr);

            }
            break;
            case R.id.btn_rsa_encry_password_login: {//RSA加密密码后调起登录
//                String encryStr = new String(encryptBytes);
                if (TextUtils.isEmpty(encryStr)) {
                    ToastUtils.show(this, "请加密后再试");
                    return;
                }
                netlogin("tmh5", encryStr);
            }
            break;
            case R.id.btn_decrypted: {//私钥解密
                String privateKey = etPrivateKey.getText().toString().trim();//key私钥
                if (TextUtils.isEmpty(privateKey)) {
                    ToastUtils.show(this, R.string.p_i_private_key);
                    return;
                }
                byte[] privateKeybytes = Base64Decoder.decodeToBytes(privateKey);

                //私钥解密
                long start = System.currentTimeMillis();
                try {
//                    String cstr="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK3KUtH+4xftD2E5JPz1GZC2gLBSJY02ApPxgMGNrzoy8cMCKWdM/R8Z8GsSTFC1KHkkl7wMiw2mtP701NhysSmhpqmPoDT4dZ6gCRpHtJiEtjD3pBkXxvofUC+GPMME/UtxU3bX293cDOiT56FTYBXXlD/MMGtT/RO6WOzDMr11AgMBAAECgYEAnhFfstB6DND5JBytn8XuMslL0xNkREPTpGwMB/+uWOjtK8lTjzlfIGKfYUOLVfzviv/VYpyl/QyboylcTmJC73ETTUS6IdWHeqxBt0ddmJH03EfqAvdNmxOVbiYIIkspFT79sKZgkZ+xZ0nC2wBV7mG1NXOUtTFzwD9MkwNMrwECQQDpFeyzynoA5psWAg5umahvvTQy3FCsENsMcG7e5H/tEtj5gDrXH2bde+nigo1msBjXwPDW68lpUeTIifZUn3mZAkEAvuAcgr9naQluCEYLFmm4dWRCkC+ZjRIErowMrDJUZUZr1jGDhLMENC+YSwiih7SzaA00dv5vqLtkmS3QiStkPQJBAMKJQ/eC5HTgA0xJBxaUNJqeBXT1oHcb0lb9d+ucNpD9jA4hUfq+ALKWe0xLvgyYqQNZyyj1vjKi4Rnm26shNvECQGM/qvN9TOr74T/iE8/Spvw7ZrUMWx5UOST9Y3WCMgn7BQV593Xfk5cDDIAvfDtuBT9cZmRdRngJAHT5so4whs0CQBJRCeU04zjHETcaAIECcp5wXPWeeAsaO8Q8HKKSxoQDCKoaxc9NKOvEJ1j1r3ZHQQjE4xQ1d+y9ol0oYF+fA6o=";
                    String cstr = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDACFXZ3PpSwxpLaB0m8DHwoOeq\n" +
                            "bPZZr+HJUHcYQaIMyFxvaaI/SMhnjQ9bRgY2Mewlj2CDoIDa/mWjx7LJWsIiOkroZKlMvmQNMkcu\n" +
                            "n6Qn50YTztv8bis0iYBDLfw++5EsGG2YaXgcX1dZUNvHAnArkrnxrsdmjzXKD5ntSJ1tgRBqLo6C\n" +
                            "uUxgQEhZFPJInE3Rr3mNzuiGihnU6t7gflHhtCSq+TujPidpVJiofp/bHlxs26WJpLpSCzz5o0tG\n" +
                            "t0ZabD2V8Rn2eV+rsJ3y4GyVyk35m+mUpT6hIPjXqMrLGdydrtIlpb6aus08TtSV0NYd6tN6dQ7K\n" +
                            "DRCQ7DCfkqozAgMBAAECggEAdBDVqu9oETvBGdCcxhspZBIs05I2N7EZm+7ZfLy2sFktnV1QZkWc\n" +
                            "cxIMNROWz4zO+Ui2gfgxyas3TY1230LO0ZdADURCG5N7r4ozAdgVhNTBQ1mlFuaTkJaHApO5wWuV\n" +
                            "q/w7yYH9ydBKYD6CB7/OgleVsaD8/l0SmsVhOMzJ8hOha85MRxad82dT5QOhHcFOgXfJJfDgTM7v\n" +
                            "OF620LuOJOWyaSNrk6twzcqhVYRl8mhVNKOn5P4dPY+ZksQul+XCbgVa4gDVW3oQgeX+Wfc7sOpI\n" +
                            "Kz/bMdN5rNBsY8kId02sVFX9rMh/9gLReXxYo0vvlOtsj5EmovKT4UcqJt8j+QKBgQD3j9nGC/iW\n" +
                            "Xnqywy4NrDr9xgSzb/G0PWFzfWAbVoLE4P+Fy5djRf/CJuywHLzIymdn3HRYqsUlUuWOxerVgHXd\n" +
                            "pbAoNTummarSldnlGhhmbkaNjMydd1/Jg9+7gUiaNiuqB6IOobsXy+HdhCajMvougaAHFcrnDuW4\n" +
                            "01B2t7P/zQKBgQDGk/PaSTJiw+hYl+o6HKVGOt6vnWHnh03WOs8CpVZkt4VwljKuXjgx0e/Bt1aC\n" +
                            "Lvco6CnZGOwyLOEdfxXfLTMx8PNSdlrOi33jbDsdR582nkoDEq9k0UHkqUWZx9qttpuDpk1GnZ5E\n" +
                            "0KBJT9kFrh00OUSxwobKBoqc/bTiH7NR/wKBgQDT5D5Dsy9clRV0OHcMoOQFHh4b767z1K26ig9e\n" +
                            "ltdq6CINRMsY32sfpO1ld9NmEUgcr6at1czUO3XclC6B+2fnBNAYOigKWvDJOhW8cG9+DNk6gs8H\n" +
                            "4IsOEl/HP8sCo2cQK2RcHbXjklCKp3fiq9lTQQPVzKNUzX12ivgOBQJ96QKBgQCuXXe/8onlo3Q/\n" +
                            "ZpVrFYAiXMrwlVdEf9BfDiB7iEaGBYeMRraV2XGqfVbzWFMD2M16A/HLCVFM9Y9t4QuJfhz3+0UM\n" +
                            "4ZP2zRFrQrvdh+hLg04lyKBt/DjJ2cCduZlQ84k2Xi54lSpF7pLwDMbZb/uAwdCJ7e+BMaFBHJ1Z\n" +
                            "+M/ybQKBgFZI7lXBO52wenOrgGMQVIuFoRs0DWfyDT79oDC06/BIc13T/keRgSRRN/8q0NlfOnRs\n" +
                            "XZjFQJUHSSOIYaPPLem3ODYYOMlsDu6wM1qLAQPfcOAhg1H0d0LGbXOd6Fq5dHfYT8kEXADfk7pT\n" +
                            "+C26MmI9SKBbqNvgCa0bqqufC0ny";

                    //方式1
//                    byte[] decryptBytes = RSAJAUtils.decryptByPrivateKeyForSpilt(Base64Decoder.decodeToBytes(encryStr), Base64Decoder.decodeToBytes(cstr));
                    byte[] decryptBytes = RSAJAUtils.decryptByPrivateKeyForSpilt(Base64Decoder.decodeToBytes(encryStr), privateKeybytes);
                    decryStr = new String(decryptBytes);


//                    //方式2
//                    byte[] decryptBytes0 = Base64.decode(encryStr);
//                      byte[] decryptBytes1 =  RSAUtils0.decrypt(cstr,decryptBytes0);
////                    byte[] decryptBytes1 =  RSAUtils0.decrypt(privateKey,decryptBytes0);
//                    decryStr=new String(decryptBytes1);
////                    decryStr = Base64.encode(RSAUtils0.decrypt(new NdkUtil().getRsaPrivateKey(), Base64.decode(encryStr)));

                } catch (Exception e) {
                    e.printStackTrace();
                }
//                byte[] decryptBytes = RSAJAUtils.decryptByPrivateKeyForSpilt(Base64Decoder.decodeToBytes(encryStr), privateKey.getEncoded());
//                String decryStr = new String(decryptBytes);
                long end = System.currentTimeMillis();
                String timeConsuming = "私钥解密耗时 cost time---->" + (end - start) + "\n";
                LogUtils.d(timeConsuming);
                LogUtils.d("解密后json数据 --1-->" + decryStr);

                tvDecryptedOutOriginal.setText(timeConsuming + getString(R.string.decrypted_out_original) + decryStr);
            }
            break;
        }
    }


    /**
     * 显示加载中对话框
     */
    public void showLoadingDialog() {
        if (null != loadingDialog && !loadingDialog.isShowing()) {
            loadingDialog.show();
            LogUtils.d("showLoadingDialog");
        } else if (null == loadingDialog) {
            loadingDialog = new LoadingDialog(this);
            loadingDialog.show();
        }
    }

    private void netlogin(String username, String password) {
        showLoadingDialog();
        if (null != loadingDialog) {//外部不可取消
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        OkHttpUtils
                .post()
                .url("http://27.17.49.251:8989/BFC_QT/v1/user/login")
                .addParams("username", username)
                .addParams("password", password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dismissLoadingDialog();//必须调用，关对话框
                        ToastUtils.show(TAG, "onError");
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        dismissLoadingDialog();//必须调用，关对话框
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.optInt("code");
                            String message = jsonObject.optString("message");//成功提示字段
                            switch (code) {
                                case 100://100请求成功
                                    ToastUtils.show(TAG, "登录成就");
                                    LogUtils.d("jsonObject.toString().trim()==>" + jsonObject.toString().trim());
                                    break;
                                case 400://400请求失败
                                    String content = jsonObject.optString("content");//成功提示字段
                                    ToastUtils.show(TAG, "content==>" + content);
                                    break;
                                default: {
                                    ToastUtils.show(TAG, "default");
                                }
                                break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ToastUtils.show(TAG, "解析服务器数据失败");
                        }
                    }
                });
    }

    /**
     * 关闭加载中对话框
     */
    public void dismissLoadingDialog() {
        if (null != loadingDialog && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

}
