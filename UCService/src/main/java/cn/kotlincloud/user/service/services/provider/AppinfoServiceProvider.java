package cn.kotlincloud.user.service.services.provider;

import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import cn.kotlincloud.user.service.services.AppinfoService;
import cn.kotlincloud.user.service.entity.model.Appinfo;
import io.jboot.service.JbootServiceBase;

import java.util.List;


@Bean
public class AppinfoServiceProvider extends JbootServiceBase<Appinfo> implements AppinfoService {
    @Override
    public Page<Appinfo> paginate(int pageNumber, int pageSize, String select, String sqlExceptSelect) {
        return DAO.paginate(pageNumber,pageSize,select,sqlExceptSelect);
    }
    @Override
    public List<Appinfo> find(String sql, Object... o) {
        return DAO.find(sql,o);
    }
}