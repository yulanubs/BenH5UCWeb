package cn.kotlincloud.user.service.services.provider;

import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import cn.kotlincloud.user.service.services.UserinfoService;
import cn.kotlincloud.user.service.entity.model.Userinfo;
import io.jboot.components.cache.annotation.Cacheable;
import io.jboot.service.JbootServiceBase;
import javafx.beans.NamedArg;

import java.util.List;


@Bean
public class UserinfoServiceProvider extends JbootServiceBase<Userinfo> implements UserinfoService {
    @Override
    public List<Userinfo> find(String sql, Object... o) {
        return DAO.find(sql, o);
    }

    @Override
    public Page<Userinfo> paginate(int pageNumber, int pageSize, String select, String sqlExceptSelect) {
        return DAO.paginate(pageNumber,pageSize,select,sqlExceptSelect);
    }

}