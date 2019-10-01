package cn.kotlincloud.user.front.routes;


import cn.kotlincloud.user.front.controller.front.FrontController;
import com.jfinal.config.Routes;

public class FrontRoutes extends Routes {
    public void config() {
        // 这里配置只对 FrontRoutes 下的路由有效，建议这样配置以提升性能
        setMappingSuperClass(true);
        add("/",FrontController.class);
//        add("/upfile", HelloController.class);

    }
}