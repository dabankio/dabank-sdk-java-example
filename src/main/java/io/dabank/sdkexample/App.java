package io.dabank.sdkexample;

import io.dabank.sdk.DabankClient;
import io.dabank.sdk.models.*;

import java.util.Date;

/**
 * Hello world!
 */
public class App {

    private static final String APP_KEY = "my_app_key_xxaabbcc_some_random_string";
    private static final String APP_PRIVATE_KEY_PKCS8_PEM = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC6bOBIlNEzW21w\n" +
            "OAM6PRUjEtaq0hmWoXWXPNKbEQpen0XMmaW/ihQ4CLSF5/YWDgm926V9qHeM+n7G\n" +
            "/s/zgWNb4w+pGrDWRb165qnDIbhdNm0bnoEb94VrObV+vhxLqrpsVItbZ2qONTSu\n" +
            "8u9ZNoMGuQIGvCDY7G9AB0+EVE3G3b+OZtdX6rbJPj76GTrPARKpB7PQC8Ba90lP\n" +
            "D7JjLGFVQeVCfsyLKETKDXmQjXUPUaqpbmIR0Tcdj8lm9jHPiOagXiIsKZ4wu0iZ\n" +
            "MWwnSDyqAgX1yIcHkY6Jjj1mDb+PSHM/4ysz2YvvnjFphbEF55YEE4DOGhYO1kYb\n" +
            "kFp5FvDRAgMBAAECggEBAIFiwUL+rjcztD/XUD4iWMIv9/c0vHez9bYSi6T97bGA\n" +
            "X2RCGyxVu/Sr7gal2EC0W4R+plwRCXA6BrU78r3xRjFm5N6b3LHKU1iyMI5yn89p\n" +
            "z84Ec10irGGf+txNO7w0cRBwmQvGOckbGkZ16zKCx+tpigN8nFuRtSC+Mi0IibaD\n" +
            "zdyldOPW3TG9ODEkLVZK/wZidDGK5fvUlwFYslwFemHuwuYsl3tpjEgkAMZF3Vh6\n" +
            "v/9ljNUFzVlM7uDVEu5LUYkxanvYUxYAOjJurphDjPicXQXzgUDQOjjTVMwihQu4\n" +
            "cGU1Ve5NTFHeRcfxV8Wo98Yl4S6tdCztbZmyb0/ZO0ECgYEA8qyM1s9NNR+1UZAP\n" +
            "5fbi5ajMkuWQia/Yx5za6aKh51TgFWY7tR1/u3HP7OZVbNUUDZvVSAkWyXTjCXk/\n" +
            "Y1vbnXg9uZFFy2c07R2XGbSpGvCQdZoEsuuiAHDm4Vyi2sMbJPEST9ZQuxJdhs9l\n" +
            "LdufI4RXx2nou90y2OJywmKrECkCgYEAxKmYexkSHhniHNo+j35LiwvlRbacbDpq\n" +
            "kUOf2gVnOYWuJJgACeo6TzIRc+kQ0omzEPoX2R7ZZ274/RvWcx7aOmb7oUTBsEXo\n" +
            "LMPKifh0VsfiPjw1yx0JQ73RD6orU9dXNRhrp6kuows+P41LP8XSEPd7QD8Jif94\n" +
            "eBLZXO7s0GkCgYEA4KjdugAwef3g3qGAFmV5qSOcQGJGbTFef223PJRTTtZEnYsH\n" +
            "u6STm/TBiY+zhg29gtvBl+7SKa8MgLi+9j0UE7Q9cI4ND0F8vgem2yVlaItNMZJk\n" +
            "xz9duwrKs9gO7HezqX+EdGT0WDuoWiWmhc3MhhT/Lp3d2A3wBNmfZFX0CAECgYAb\n" +
            "HLJrzhIEWlck2SWkzKmHetVZ3goEla0ZZ7RC71nCwV43p0jiFQuC3YkwbKIoFFm+\n" +
            "bmwZP7qDGvZyloLjDg7R3F62Ze856PlQKpHioYEQcbAMGmrkHJWpZCLNQQ2GGnro\n" +
            "U/yLebR3P9aOFmGYYiJKuoOgYNBk2PZ3v0l+GWZGeQKBgQC2vvt/fHs1JDRJTU0Z\n" +
            "7RpUVyWltGkWSy3/JsLN8y/7y1r0fyTsxvkSD0MT9ItJwyglGgh1YuMOiM+B3G+n\n" +
            "v60mlW9ThH2NG7vEpglhf7l53mimdD56eQelacjCwV7PqogktH/BWpBdV1okUFzI\n" +
            "Hij7zs0C9LUH3kkaLyp7vzsnxw==\n" +
            "-----END PRIVATE KEY-----\n";

    public static void main(String[] args) throws Exception {
        //说明：代码仅作为示例参考使用，由于私钥、key非实际数据，除hello time 接口外的api都会返回401错误

        //构造client
        DabankClient client = createClient();

        //hello world
        String hello = client.hello();
        System.out.println("got response using dabank client:" + hello);

        //服务器时间
        String time = client.time();
        System.out.println("got time from dabank server:" + time);

        //申请地址
        ReqAddress req = new ReqAddress();
        req.setSymbol("BTC");
        req.setUserId("my_user_id");
        req.setRequestTime(new Date().getTime() / 1000);
        RespResult<RespAddress> resp = client.address(req);
        System.out.println(resp.getData().getAddress());

        //发起转账
        ReqTransfer reqTransfer = new ReqTransfer();
        reqTransfer.setFrom("from_addr");
        reqTransfer.setTo("to_addr");
        reqTransfer.setUniqueId("my_unique_transaction_id");
        reqTransfer.setSymbol("BTC");
        reqTransfer.setCoins(1.23);
        reqTransfer.setRequestTime(new Date().getTime() / 1000);
        RespResult<RespTransfer> transferResp = client.transfer(reqTransfer);
        System.out.println("transfer tx id: " + transferResp.getData().getTransferId());
    }


    static DabankClient createClient() throws Exception {
        return DabankClient.builder()
                .host("https://api.dabank.io") //server host
                .privateKey(APP_PRIVATE_KEY_PKCS8_PEM) //pkcs8格式的rsa私钥
                .appId(APP_KEY) //appId (key)
                .jsonProvider(new DabankJsonProvider()) //json provider
//                .httpProvider($your_http_provider) //http provider
                .build();
    }
}
