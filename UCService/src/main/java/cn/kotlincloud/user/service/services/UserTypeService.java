package cn.kotlincloud.user.service.services;

import cn.kotlincloud.user.service.services.base.BaseService;
import com.jfinal.plugin.activerecord.Page;
import cn.kotlincloud.user.service.entity.model.UserType;
import io.jboot.db.model.Columns;

import java.util.List;

public interface UserTypeService  extends BaseService{

    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public UserType findById(Object id);


    /**
     * find all model
     *
     * @return all <UserType
     */
    public List<UserType> findAll();


    /**
     * delete model by primary key
     *
     * @param id
     * @return success
     */
    public boolean deleteById(Object id);


    /**
     * delete model
     *
     * @param model
     * @return
     */
    public boolean delete(UserType model);


    /**
     * save model to database
     *
     * @param model
     * @return id value if save success
     */
    public Object save(UserType model);


    /**
     * save or update model
     *
     * @param model
     * @return id value if save or update success
     */
    public Object saveOrUpdate(UserType model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(UserType model);


    /**
     * page query
     *
     * @param page
     * @param pageSize
     * @return page data
     */
    public Page<UserType> paginate(int page, int pageSize);


    /**
     * page query by columns
     *
     * @param page
     * @param pageSize
     * @param columns
     * @return page data
     */
    public Page<UserType> paginateByColumns(int page, int pageSize, Columns columns);


    /**
     * page query by columns
     *
     * @param page
     * @param pageSize
     * @param columns
     * @param orderBy
     * @return page data
     */
    public Page<UserType> paginateByColumns(int page, int pageSize, Columns columns, String orderBy);


}