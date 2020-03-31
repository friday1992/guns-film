package com.stylefeng.guns.rest.modular.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luo.user.UserAPI;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.rest.common.exception.BizExceptionEnum;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthRequest;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthResponse;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@RestController
public class AuthController {
    @Reference(interfaceClass = UserAPI.class)
    private UserAPI userAPI;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//    @Resource(name = "simpleValidator")
//    private IReqValidator reqValidator;

    @RequestMapping(value = "${jwt.auth-path}")
    public ResponseEntity<?> createAuthenticationToken(AuthRequest authRequest) {
        userAPI.login(authRequest.getCredenceName(),authRequest.getPassword());
//        boolean validate = reqValidator.validate(authRequest);
        boolean validate = true;
        int userId = userAPI.login(authRequest.getUserName(),authRequest.getPassword());
        if(userId == 0){
            validate = false;
        }
        if (validate) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(userId+"", randomKey);
            return ResponseEntity.ok(new AuthResponse(token, randomKey));
        } else {
            throw new GunsException(BizExceptionEnum.AUTH_REQUEST_ERROR);
        }
    }
}
