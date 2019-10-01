package cn.kotlincloud.user.front.services.provider;

import io.jboot.aop.annotation.Bean;
import cn.kotlincloud.user.front.services.WebnetService;
import cn.kotlincloud.user.front.entity.model.Webnet;
import io.jboot.service.JbootServiceBase;


@Bean
public class WebnetServiceProvider extends JbootServiceBase<Webnet> implements WebnetService {

}