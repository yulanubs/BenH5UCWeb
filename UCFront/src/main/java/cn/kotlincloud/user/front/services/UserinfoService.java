package cn.kotlincloud.user.front.services;

import com.jfinal.plugin.activerecord.Page;
import cn.kotlincloud.user.front.entity.model.Userinfo;
import io.jboot.db.model.Columns;

import java.util.List;

public interface UserinfoService  {

    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public Userinfo findById(Object id);


    /**
     * find all model
     *
     * @return all <Userinfo
     */
    public List<Userinfo> findAll();


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
    public boolean delete(Userinfo model);


    /**
     * save model to database
     *
     * @param model
     * @return id value if save success
     */
    public Object save(Userinfo model);


    /**
     * save or update model
     *
     * @param model
     * @return id value if save or update success
     */
    public Object saveOrUpdate(Userinfo model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(Userinfo model);


    /**
     * page query
     *
     * @param page
     * @param pageSize
     * @return page data
     */
    public Page<Userinfo> paginate(int page, int pageSize);


    /**
     * page query by columns
     *
     * @param page
     * @param pageSize
     * @param columns
     * @return page data
     */
    public Page<Userinfo> paginateByColumns(int page, int pageSize, Columns columns);


    /**
     * page query by columns
     *
     * @param page
     * @param pageSize
     * @param columns
     * @param orderBy
     * @return page data
     */
    public Page<Userinfo> paginateByColumns(int page, int pageSize, Columns columns, String orderBy);


}