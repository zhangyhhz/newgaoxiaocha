package com.gaoxiaocha.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gaoxiaocha.mapper.ImTokenMapper;
import com.gaoxiaocha.pojo.ImToken;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class RongCloudService {

    @Resource
    private ImTokenMapper tokenMapper;

    /**
     *注册账号时注册Im
     *
     *@author zyh
     *@date 2020/6/14
     */
    public ImToken register(String account, String userName, String userAvl) {

        OkHttpClient client = new OkHttpClient();
        String url = "https://api-cn.ronghub.com/user/getToken.json";

        String appSecret = "HNQljAwFNI";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String randomNum = String.valueOf((Math.random() * 9 + 1) * Math.pow(10, 17)) ;
        String signature = DigestUtils.sha1Hex(appSecret + randomNum + timestamp).toString();

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("userId",account);
        builder.add("name",userName);
        builder.add("portraitUri",userAvl);
        FormBody body = builder.build();
        Request request = new Request.Builder().url(url).header("App-Key","cpj2xarlchkkn")
                .header("Nonce",randomNum)
                .header("Timestamp",timestamp)
                .header("Signature",signature).post(body).build();
        try (Response response = client.newCall(request).execute()){
            String responseBody = response.body().string();
            Map maps = JSON.parseObject(responseBody);
            String token = String.valueOf(maps.get("token")) ;
            String userId = String.valueOf(maps.get("userId"));
            ImToken imToken = new ImToken();
            imToken.setToken(token);
            imToken.setUserAccount(userId);
            tokenMapper.insert(imToken);
            return imToken;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *通过account查找Token
     *
     *@author zyh
     *@date 2020/6/14
     */
    public ImToken getToken(String account){
        QueryWrapper<ImToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account",account);
        ImToken imToken = tokenMapper.selectOne(queryWrapper);
        return imToken;
    }
}
