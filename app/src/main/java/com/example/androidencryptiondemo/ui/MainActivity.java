package com.example.androidencryptiondemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidencryptiondemo.R;
import com.example.androidencryptiondemo.adapter.MyRecyclerViewAdapter;
import com.example.androidencryptiondemo.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.CallBack {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.rv)
    RecyclerView rv;

    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        List<String> data = new ArrayList<String>();
        //http://blog.csdn.net/shb2058/article/details/53467501
        //加密算法如下：
        data.add(getString(R.string.rsa_encryption));
        data.add(getString(R.string.aes_encryption));
        data.add(getString(R.string.des_encryption));
        data.add(getString(R.string.md5_encryption));
        data.add(getString(R.string.base64_encryption));
        data.add(getString(R.string.yh_encryption));
        data.add(getString(R.string.rsa_encry_password));

//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(data, this);
        rv.setAdapter(myRecyclerViewAdapter);


    }

    @Override
    public void rVOnItemClick(int position) {
        if (null != myRecyclerViewAdapter) {
            String str = myRecyclerViewAdapter.getItem(position);
            if (!TextUtils.isEmpty(str)) {
                if (str.equals(getString(R.string.rsa_encryption))) {
                    startActivity(new Intent(MainActivity.this,RSAEncryptionActivity.class));
                } else if (str.equals(getString(R.string.aes_encryption))) {
                } else if (str.equals(getString(R.string.des_encryption))) {
                } else if (str.equals(getString(R.string.md5_encryption))) {
                } else if (str.equals(getString(R.string.base64_encryption))) {
                } else if (str.equals(getString(R.string.yh_encryption))) {
                } else if (str.equals(getString(R.string.rsa_encry_password))) {
                    startActivity(new Intent(MainActivity.this,RSAEncryptionPasswordActivity.class));
                }
            }
        }
    }
}

