package cn.kotlincloud.user.service.services.provider;

import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import cn.kotlincloud.user.service.services.WebnetService;
import cn.kotlincloud.user.service.entity.model.Webnet;
import io.jboot.service.JbootServiceBase;

import java.util.List;


@Bean
public class WebnetServiceProvider extends JbootServiceBase<Webnet> implements WebnetService {

    @Override
    public Page<Webnet> paginate(int pageNumber, int pageSize, String select, String sqlExceptSelect) {
        return DAO.paginate(pageNumber,pageSize,select,sqlExceptSelect);
    }
    @Override
    public List<Webnet> find(String sql, Object... o) {
        return DAO.find(sql,o);
    }
}