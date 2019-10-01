package cn.kotlincloud.user.service.services;

import com.jfinal.plugin.activerecord.Page;
import cn.kotlincloud.user.service.entity.model.UserPwd;
import io.jboot.db.model.Columns;

import java.util.List;

public interface UserPwdService  {

    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public UserPwd findById(Object id);


    /**
     * find all model
     *
     * @return all <UserPwd
     */
    public List<UserPwd> findAll();


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
    public boolean delete(UserPwd model);


    /**
     * save model to database
     *
     * @param model
     * @return id value if save success
     */
    public Object save(UserPwd model);


    /**
     * save or update model
     *
     * @param model
     * @return id value if save or update success
     */
    public Object saveOrUpdate(UserPwd model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(UserPwd model);


    /**
     * page query
     *
     * @param page
     * @param pageSize
     * @return page data
     */
    public Page<UserPwd> paginate(int page, int pageSize);


    /**
     * page query by columns
     *
     * @param page
     * @param pageSize
     * @param columns
     * @return page data
     */
    public Page<UserPwd> paginateByColumns(int page, int pageSize, Columns columns);


    /**
     * page query by columns
     *
     * @param page
     * @param pageSize
     * @param columns
     * @param orderBy
     * @return page data
     */
    public Page<UserPwd> paginateByColumns(int page, int pageSize, Columns columns, String orderBy);


}