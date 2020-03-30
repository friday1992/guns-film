package com.stylefeng.guns.rest.user;


import com.alibaba.dubbo.config.annotation.Service;
import com.luo.user.UserAPI;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = UserAPI.class)
public class UserAPIImpl  implements UserAPI {
    @Override
    public boolean login(String username, String password) {
        System.out.println("this is user "+username+":"+password);
        return false;
    }
}
