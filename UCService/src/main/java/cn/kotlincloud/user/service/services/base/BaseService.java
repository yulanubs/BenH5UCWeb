package cn.kotlincloud.user.service.services.base;

import cn.kotlincloud.user.service.entity.model.Userinfo;
import cn.kotlincloud.user.service.entity.model.Webnet;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * Created by yulan on 2019/9/22.
 */
public interface BaseService <T>  {

    public List<T> find(String sql, Object... o);
    /**
     * page query
     *
     * @param sqlExceptSelect
     * @param pageSize
     * @return page data
     */

    public Page<T> paginate(int pageNumber, int pageSize, String select, String sqlExceptSelect);

}
