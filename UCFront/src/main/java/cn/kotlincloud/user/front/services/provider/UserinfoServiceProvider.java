package cn.kotlincloud.user.front.services.provider;

import io.jboot.aop.annotation.Bean;
import cn.kotlincloud.user.front.services.UserinfoService;
import cn.kotlincloud.user.front.entity.model.Userinfo;
import io.jboot.service.JbootServiceBase;


@Bean
public class UserinfoServiceProvider extends JbootServiceBase<Userinfo> implements UserinfoService {

}