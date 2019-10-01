package cn.kotlincloud.user.service.services.provider;

import io.jboot.aop.annotation.Bean;
import cn.kotlincloud.user.service.services.UserPwdService;
import cn.kotlincloud.user.service.entity.model.UserPwd;
import io.jboot.service.JbootServiceBase;


@Bean
public class UserPwdServiceProvider extends JbootServiceBase<UserPwd> implements UserPwdService {

}