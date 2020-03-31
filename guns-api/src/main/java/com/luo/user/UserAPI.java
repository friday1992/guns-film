package com.luo.user;

import com.luo.user.usermodel.UserInfoModel;
import com.luo.user.usermodel.UserModel;

public interface UserAPI {
    int login(String username,String password);
    boolean registry(UserModel userModel);
    boolean checkin(String username);
     UserInfoModel getUserInfoModel(int id);
     boolean updateUserInfoModel(UserInfoModel userInfoModel);

}
