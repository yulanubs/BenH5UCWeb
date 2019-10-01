package cn.kotlincloud.user.service.services;

import cn.kotlincloud.user.service.services.base.BaseService;
import com.jfinal.plugin.activerecord.Page;
import cn.kotlincloud.user.service.entity.model.Appinfo;
import io.jboot.db.model.Columns;

import java.util.List;

public interface AppinfoService  extends BaseService {

    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public Appinfo findById(Object id);


    /**
     * find all model
     *
     * @return all <Appinfo
     */
    public List<Appinfo> findAll();


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
    public boolean delete(Appinfo model);


    /**
     * save model to database
     *
     * @param model
     * @return id value if save success
     */
    public Object save(Appinfo model);


    /**
     * save or update model
     *
     * @param model
     * @return id value if save or update success
     */
    public Object saveOrUpdate(Appinfo model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(Appinfo model);


    /**
     * page query
     *
     * @param page
     * @param pageSize
     * @return page data
     */
    public Page<Appinfo> paginate(int page, int pageSize);


    /**
     * page query by columns
     *
     * @param page
     * @param pageSize
     * @param columns
     * @return page data
     */
    public Page<Appinfo> paginateByColumns(int page, int pageSize, Columns columns);


    /**
     * page query by columns
     *
     * @param page
     * @param pageSize
     * @param columns
     * @param orderBy
     * @return page data
     */
    public Page<Appinfo> paginateByColumns(int page, int pageSize, Columns columns, String orderBy);


}