package com.example.androidencryptiondemo.ui;

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

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by lenovo on 2017/4/25.
 * 参考：http://blog.csdn.net/shb2058/article/details/53467501
 */

public class RSAEncryptionActivity extends AppCompatActivity {

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
    @BindView(R.id.tv_encrypted_ciphertext)
    TextView tvEncryptedCiphertext;
    @BindView(R.id.btn_decrypted)
    Button btnDecrypted;
    @BindView(R.id.tv_decrypted_out_original)
    TextView tvDecryptedOutOriginal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa_encryption);
        ButterKnife.bind(this);

        etOriginal.setText("a");

        etPublicKey.setText(new NdkUtil().getRsaPublicKey());
        etPrivateKey.setText(new NdkUtil().getRsaPrivateKey());
        etPrivateKey.setText(new NdkUtil().setRsaPublicKey("123"));

    }

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
    private byte[] encryptBytes = new byte[0];

    @OnClick({R.id.btn_encryption, R.id.btn_decrypted, R.id.btn_generate_public_private_keys})
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

                etPublicKey.setText(publicKeyStr);//公钥

                // 私钥
                privateKey = (RSAPrivateKey) keyPair.getPrivate();
                byte[] bytesPrivateKey = privateKey.getEncoded();
                String privateKeyStr = Base64Encoder.encode(bytesPrivateKey);//

                etPrivateKey.setText(privateKeyStr);//私钥

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
//                byte[] encryptBytes = new byte[0];
                try {
                    encryptBytes = RSAJAUtils.encryptByPublicKeyForSpilt(original.getBytes(), publicKeybytes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                long end = System.currentTimeMillis();
                String timeConsuming = "公钥加密耗时 cost time---->" + (end - start) + "\n";
                LogUtils.d(timeConsuming);
                String encryStr = new String(encryptBytes);
                LogUtils.d("加密后数据 --1-->" + encryStr);
                LogUtils.d("加密后数据长度 --1-->" + encryStr.length());

//                String encryStr = Base64Encoder.encode(encryptBytes);
//                LogUtils.d("加密后json数据 --1-->" + encryStr);
//                LogUtils.d("加密后json数据长度 --1-->" + encryStr.length());

                tvEncryptedCiphertext.setText(timeConsuming + getString(R.string.encrypted_ciphertext) + encryStr);

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
                byte[] decryptBytes = new byte[0];
                try {
                    decryptBytes = RSAJAUtils.decryptByPrivateKeyForSpilt(encryptBytes, privateKeybytes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                byte[] decryptBytes = RSAJAUtils.decryptByPrivateKeyForSpilt(Base64Decoder.decodeToBytes(encryStr), privateKey.getEncoded());
                String decryStr = new String(decryptBytes);
                long end = System.currentTimeMillis();
                String timeConsuming = "私钥解密耗时 cost time---->" + (end - start) + "\n";
                LogUtils.d(timeConsuming);
                LogUtils.d("解密后json数据 --1-->" + decryStr);

                tvDecryptedOutOriginal.setText(timeConsuming + getString(R.string.decrypted_out_original) + decryStr);
            }
            break;
        }
    }
}
