package io.nakong;

import com.google.gson.Gson;
import io.nakong.modules.sys.entity.SysUserEntity;

public class TestGson {
    public static void main(String [] arg) {

        Gson gson = new Gson();
        SysUserEntity user = new SysUserEntity();
        user.setEmail("qqq@qq.com");
        System.out.println(gson.toJson(user));

    }
}
