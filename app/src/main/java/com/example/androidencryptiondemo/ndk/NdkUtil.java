package com.example.androidencryptiondemo.ndk;

/**
 * Created by lenovo on 2017/4/25.
 */

public class NdkUtil {

    static {
        System.loadLibrary("huishouge");
    }

    //native的方法AS会报红，不用管是这样的
    public native String getRsaPublicKey();

    public native String getRsaPrivateKey();

    public native String setRsaPublicKey(String rsaPublicKey);


    //写完ndk类文件后要同步下，在..\build..\debug目录下要有NdkUtil这个类
    //javah -d jni -classpath D:\Android\sdk\platforms\android-14\android.jar;..\..\build\intermediates\classes\debug com.example.androidencryptiondemo.ndk.NdkUtil
    //执行完在main目录下会自动生成jni文件，里面还有自动生成的头文件

    //完整命令
    //javah -d jni -classpath D:\Android\sdk\platforms\android-14\android.jar;G:\AndroidStudioProjects20170322\AndroidEncryptionDemo\app\build\intermediates\classes\debug com.example.androidencryptiondemo.ndk.NdkUtil
}
