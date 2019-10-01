package cn.kotlincloud.user.service.services.provider;

import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import cn.kotlincloud.user.service.services.UserTypeService;
import cn.kotlincloud.user.service.entity.model.UserType;
import io.jboot.service.JbootServiceBase;

import java.util.Date;
import java.util.List;


@Bean
public class UserTypeServiceProvider extends JbootServiceBase<UserType> implements UserTypeService {

    @Override
    public List<UserType> find(String sql, Object... o) {
        return DAO.find( sql,o );
    }

    @Override
    public Page paginate(int pageNumber, int pageSize, String select, String sqlExceptSelect) {
        return DAO.paginate( pageNumber,pageSize,select,sqlExceptSelect );
    }
}