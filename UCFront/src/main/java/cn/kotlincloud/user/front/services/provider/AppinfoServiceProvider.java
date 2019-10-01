package cn.kotlincloud.user.front.services.provider;

import io.jboot.aop.annotation.Bean;
import cn.kotlincloud.user.front.services.AppinfoService;
import cn.kotlincloud.user.front.entity.model.Appinfo;
import io.jboot.service.JbootServiceBase;


@Bean
public class AppinfoServiceProvider extends JbootServiceBase<Appinfo> implements AppinfoService {

}