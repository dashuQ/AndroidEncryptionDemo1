//
// Created by lenovo on 2017/4/25.
//
#include <jni.h>
#include<com_example_androidencryptiondemo_ndk_NdkUtil.h>

JNIEXPORT jstring JNICALL Java_com_example_androidencryptiondemo_ndk_NdkUtil_getRsaPublicKey(JNIEnv* env,jobject thiz){

//    char* cstr="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbO0k14SgZIFUiTDcHzdbEwZ/n5K4CIpvhBY7nl1mvxVVyhoBuZp10CrvghXuJSvXfAbAa+S2G8BdIHsLqYY2amzKyhlMMay+FwguRFT7CKuDd/MMCo5r16univz5YgmzLjdinVsp0+uVfls/1kBp+RodDsGicfXwZ9bo9ZEgtNwIDAQAB";


//    char* cstr="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0/w80ZaukB8baETpDZIMuJbeF3x2HX5jM/xLo4h8aDmex/UU4X1BzvuUrnqvlnkCEJHd42hfQOQHXWUt8JXDL3pquUr9Oo47Rn/z8YqHkjwOjcDtP/uZQdsM10wcSu1Uk14fQtDxSO89XFvBK59JaTT5fM0MZlzZQ0MVodb7/QDKm3np06z38Sj2SLNH+JtT3BLBksiHd4Cd2LyX2sZOcj+tNi4L/+hYFJYgk1MqOYA6sZ9SQdNJ43AiTSPbz7710PB89fSK6kdTTQYW8ogYn7Bpy+gAdawukThrPOWudg2zShmUpj58iRb3N9Q4gp4Sml2KLJgSi89X3D5JhcmbDQIDAQAB";

    char* cstr="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwAhV2dz6UsMaS2gdJvAx8KDnqmz2Wa/hyVB3GEGiDMhcb2miP0jIZ40PW0YGNjHsJY9gg6CA2v5lo8eyyVrCIjpK6GSpTL5kDTJHLp+kJ+dGE87b/G4rNImAQy38PvuRLBhtmGl4HF9XWVDbxwJwK5K58a7HZo81yg+Z7UidbYEQai6OgrlMYEBIWRTySJxN0a95jc7ohooZ1Ore4H5R4bQkqvk7oz4naVSYqH6f2x5cbNuliaS6Ugs8+aNLRrdGWmw9lfEZ9nlfq7Cd8uBslcpN+ZvplKU+oSD416jKyxncna7SJaW+mrrNPE7UldDWHerTenUOyg0QkOwwn5KqMwIDAQAB";

//    char* cstr="我是放在huishouge.so中的公公公钥文本";
    return (*env)->NewStringUTF(env,cstr);
}
JNIEXPORT jstring JNICALL Java_com_example_androidencryptiondemo_ndk_NdkUtil_getRsaPrivateKey(JNIEnv* env,jobject thiz){

//    char* cstr="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK3KUtH+4xftD2E5JPz1GZC2gLBSJY02ApPxgMGNrzoy8cMCKWdM/R8Z8GsSTFC1KHkkl7wMiw2mtP701NhysSmhpqmPoDT4dZ6gCRpHtJiEtjD3pBkXxvofUC+GPMME/UtxU3bX293cDOiT56FTYBXXlD/MMGtT/RO6WOzDMr11AgMBAAECgYEAnhFfstB6DND5JBytn8XuMslL0xNkREPTpGwMB/+uWOjtK8lTjzlfIGKfYUOLVfzviv/VYpyl/QyboylcTmJC73ETTUS6IdWHeqxBt0ddmJH03EfqAvdNmxOVbiYIIkspFT79sKZgkZ+xZ0nC2wBV7mG1NXOUtTFzwD9MkwNMrwECQQDpFeyzynoA5psWAg5umahvvTQy3FCsENsMcG7e5H/tEtj5gDrXH2bde+nigo1msBjXwPDW68lpUeTIifZUn3mZAkEAvuAcgr9naQluCEYLFmm4dWRCkC+ZjRIErowMrDJUZUZr1jGDhLMENC+YSwiih7SzaA00dv5vqLtkmS3QiStkPQJBAMKJQ/eC5HTgA0xJBxaUNJqeBXT1oHcb0lb9d+ucNpD9jA4hUfq+ALKWe0xLvgyYqQNZyyj1vjKi4Rnm26shNvECQGM/qvN9TOr74T/iE8/Spvw7ZrUMWx5UOST9Y3WCMgn7BQV593Xfk5cDDIAvfDtuBT9cZmRdRngJAHT5so4whs0CQBJRCeU04zjHETcaAIECcp5wXPWeeAsaO8Q8HKKSxoQDCKoaxc9NKOvEJ1j1r3ZHQQjE4xQ1d+y9ol0oYF+fA6o=";
//    char* cstr="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK3KUtH+4xftD2E5JPz1GZC2gLBSJY02ApPxgMGNrzoy8cMCKWdM/R8Z8GsSTFC1KHkkl7wMiw2mtP701NhysSmhpqmPoDT4dZ6gCRpHtJiEtjD3pBkXxvofUC+GPMME/UtxU3bX293cDOiT56FTYBXXlD/MMGtT/RO6WOzDMr11AgMBAAECgYEAnhFfstB6DND5JBytn8XuMslL0xNkREPTpGwMB/+uWOjtK8lTjzlfIGKfYUOLVfzviv/VYpyl/QyboylcTmJC73ETTUS6IdWHeqxBt0ddmJH03EfqAvdNmxOVbiYIIkspFT79sKZgkZ+xZ0nC2wBV7mG1NXOUtTFzwD9MkwNMrwECQQDpFeyzynoA5psWAg5umahvvTQy3FCsENsMcG7e5H/tEtj5gDrXH2bde+nigo1msBjXwPDW68lpUeTIifZUn3mZAkEAvuAcgr9naQluCEYLFmm4dWRCkC+ZjRIErowMrDJUZUZr1jGDhLMENC+YSwiih7SzaA00dv5vqLtkmS3QiStkPQJBAMKJQ/eC5HTgA0xJBxaUNJqeBXT1oHcb0lb9d+ucNpD9jA4hUfq+ALKWe0xLvgyYqQNZyyj1vjKi4Rnm26shNvECQGM/qvN9TOr74T/iE8/Spvw7ZrUMWx5UOST9Y3WCMgn7BQV593Xfk5cDDIAvfDtuBT9cZmRdRngJAHT5so4whs0CQBJRCeU04zjHETcaAIECcp5wXPWeeAsaO8Q8HKKSxoQDCKoaxc9NKOvEJ1j1r3ZHQQjE4xQ1d+y9ol0oYF+fA6o=";


//    char* cstr="OpenSSLRSAPrivateCrtKey{modulus=f0d175f1d5ee837341d606aad8fe020eefcfc2729f5f3cea3cbd5eea6342e8cf84f07659820959dff6c8769a31193ed0b2b25ccffc8c75b378e520c20a010c0988d79cae8c892f383b3dfa420ee41623597ef1c0292fa5be534327c658df5f1785eb18002c181c295928e8142d617ee47dd37d7b85af963a9ddf87ac064001e0d3b002bc5e5f0be90bcae68cc5391ffb08a1539eda100108481580311cb62e93937389cb585cce59cdf739628e6dd4439b6082770dabbeafdb4eccb4af7947dba24ae8270316a9f17ab4556cc1fae0aceb37b65ee3c7256c8b86812803368c1272f54fb8d1d5c85d4d0bdd8a772de6fe39e63553d95fefaeab588cf8d6ae6737,publicExponent=10001,privateExponent=7e858df200f3cf1c3efab3860f7b649974653344057784098cdff17e33a873a893afc27e4453a56485c3cc8c363f8d4118a19d21a90e22c8a83bb97a38c7540bae5999781b5df7f1132efcccb8061d9b74e9bd19b889d00aaeb419b19bffbf761c65e7b62f747b573e00bfedd9e9df412cda7092f529cb4400adf019f12eafccea74a89dc494b47366dab2b2d7c8602aeb2c0c5201dd004f85c1625cf8af034c3f5524d0b84ba19c1caba5c1de343cd25d9c1a7f8ee1d16feaf984156bc9847718c2e19db27ec552c47b23769d7b33c5ac99caa6bb4a84d47334aad5c7dc4b4d96f1bdef8616259a38a1d6dca43126543e8497a26fcdff28e6b93698aa0f341,primeP=fdb5d7fb57e72173a202537235b82275eecdeb7d689dd843fac02812461c1423edd84e02bb8652b004ac5662df35d79dfa946ae8293ccb97695b901dfa2b9ae14450cf983a4ed94f507ef06952f8b35f2a4f24412991e069de438765df10481a62db8247f713d5ef5ff7d50f571b6869acae3bc340183b7aa2746af7ff43c415,primeQ=f2fdd4f769112fded88c6de3491d99b05153f2233db4c29b55c5d1b57a335a3bc2dc27845a028b1201dbffafb6e7d86f2fadb279a5d9c5c2586c399f64a833a2988cb202bd26176716988c234ec1cf8eb3d3b2f1e7ff7fc8af18e0efeba0e389e3da5c6b97258c3da44df85056370e3e26baaa492b007482441df7855b67151b,primeExponentP=8d460daf52824c594434415d0e56ab31ba3905d13e48ee9ce3eace2566cec9d43be3bf84bc010ed81eb20c2460206e20f4e0622b469328d6f5fedb6025e6d4066df105b7c178187f96d6286ead99f8df9c0297b085424a910907d7443d92a663b2fa82302a7fec17affba3b33810f5f22a0a9712a1051d51ab56621e18111705,primeExponentQ=bc9c56906c9790a6d343bd902c92b356eee9d00b96bc164a687b9259e12eb71da81526ff4eb6a6a82595549459cf006add81600598bcbf0a298467fdd72dd056a73e9e760dc7e1667d5d8f79608a83c76ac22a2627446535aae40ec56c0a8853e1eefec9ec9a928ece584f245ca78380aea339c66d807eef165c8462991e1c7d,crtCoefficient=4f1b0c6903b42e785b8e6b6899c145eba8becb3a1ed6825956679ba75c0fc1af9183c47b7e52ca421e65364268a68567c43276817302e85f342b84064fbd34283743341fcbb646226766bf0c87da926841d024880fe368f5ce7cd16611627b5fdf82507ff7c1a988556f23643ca2f20fa524478dd0c7a4816923a475ab9a4db8,";

    char* cstr="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDACFXZ3PpSwxpLaB0m8DHwoOeq\n"
            "bPZZr+HJUHcYQaIMyFxvaaI/SMhnjQ9bRgY2Mewlj2CDoIDa/mWjx7LJWsIiOkroZKlMvmQNMkcu\n"
            "n6Qn50YTztv8bis0iYBDLfw++5EsGG2YaXgcX1dZUNvHAnArkrnxrsdmjzXKD5ntSJ1tgRBqLo6C\n"
            "uUxgQEhZFPJInE3Rr3mNzuiGihnU6t7gflHhtCSq+TujPidpVJiofp/bHlxs26WJpLpSCzz5o0tG\n"
            "t0ZabD2V8Rn2eV+rsJ3y4GyVyk35m+mUpT6hIPjXqMrLGdydrtIlpb6aus08TtSV0NYd6tN6dQ7K\n"
            "DRCQ7DCfkqozAgMBAAECggEAdBDVqu9oETvBGdCcxhspZBIs05I2N7EZm+7ZfLy2sFktnV1QZkWc\n"
            "cxIMNROWz4zO+Ui2gfgxyas3TY1230LO0ZdADURCG5N7r4ozAdgVhNTBQ1mlFuaTkJaHApO5wWuV\n"
            "q/w7yYH9ydBKYD6CB7/OgleVsaD8/l0SmsVhOMzJ8hOha85MRxad82dT5QOhHcFOgXfJJfDgTM7v\n"
            "OF620LuOJOWyaSNrk6twzcqhVYRl8mhVNKOn5P4dPY+ZksQul+XCbgVa4gDVW3oQgeX+Wfc7sOpI\n"
            "Kz/bMdN5rNBsY8kId02sVFX9rMh/9gLReXxYo0vvlOtsj5EmovKT4UcqJt8j+QKBgQD3j9nGC/iW\n"
            "Xnqywy4NrDr9xgSzb/G0PWFzfWAbVoLE4P+Fy5djRf/CJuywHLzIymdn3HRYqsUlUuWOxerVgHXd\n"
            "pbAoNTummarSldnlGhhmbkaNjMydd1/Jg9+7gUiaNiuqB6IOobsXy+HdhCajMvougaAHFcrnDuW4\n"
            "01B2t7P/zQKBgQDGk/PaSTJiw+hYl+o6HKVGOt6vnWHnh03WOs8CpVZkt4VwljKuXjgx0e/Bt1aC\n"
            "Lvco6CnZGOwyLOEdfxXfLTMx8PNSdlrOi33jbDsdR582nkoDEq9k0UHkqUWZx9qttpuDpk1GnZ5E\n"
            "0KBJT9kFrh00OUSxwobKBoqc/bTiH7NR/wKBgQDT5D5Dsy9clRV0OHcMoOQFHh4b767z1K26ig9e\n"
            "ltdq6CINRMsY32sfpO1ld9NmEUgcr6at1czUO3XclC6B+2fnBNAYOigKWvDJOhW8cG9+DNk6gs8H\n"
            "4IsOEl/HP8sCo2cQK2RcHbXjklCKp3fiq9lTQQPVzKNUzX12ivgOBQJ96QKBgQCuXXe/8onlo3Q/\n"
            "ZpVrFYAiXMrwlVdEf9BfDiB7iEaGBYeMRraV2XGqfVbzWFMD2M16A/HLCVFM9Y9t4QuJfhz3+0UM\n"
            "4ZP2zRFrQrvdh+hLg04lyKBt/DjJ2cCduZlQ84k2Xi54lSpF7pLwDMbZb/uAwdCJ7e+BMaFBHJ1Z\n"
            "+M/ybQKBgFZI7lXBO52wenOrgGMQVIuFoRs0DWfyDT79oDC06/BIc13T/keRgSRRN/8q0NlfOnRs\n"
            "XZjFQJUHSSOIYaPPLem3ODYYOMlsDu6wM1qLAQPfcOAhg1H0d0LGbXOd6Fq5dHfYT8kEXADfk7pT\n"
            "+C26MmI9SKBbqNvgCa0bqqufC0ny";

//    char* cstr="我是放在huishouge.so中的私私私钥文本";
    return (*env)->NewStringUTF(env,cstr);
}

//jstring Java_com_example_androidencryptiondemo_ndk_NdkUtil_setRsaPublicKey(JNIEnv* env,jobject thiz,jstring rsaPublicKey){
JNIEXPORT jstring JNICALL Java_com_example_androidencryptiondemo_ndk_NdkUtil_setRsaPublicKey(JNIEnv* env,jobject thiz,jstring rsaPublicKey){
//    const char* str;
//    str = env->GetStringUTFChars(rsaPublicKey, false);
//    if(str == NULL) {
//        return NULL;  OutOfMemoryError already thrown
//    }
//    std::cout << str << std::endl;
//    env->ReleaseStringUTFChars(rsaPublicKey, str);
//    char* tmpstr = "return string succeeded";
//    jstring rtstr = env->NewStringUTF(tmpstr);
//    rsaPublicKey="存在huishouge.so中的公钥如下："+rsaPublicKey;
    return rsaPublicKey;
}

/*JNIEXPORT jstring JNICALL Java_com_example_androidencryptiondemo_ndk_NdkUtil_getRsaPublicKey(JNIEnv* env,jobject thiz){
char* cstr="我是放在huishouge.so中的公公公钥文本";
return (*env)->NewStringUTF(env,cstr);
}*/



