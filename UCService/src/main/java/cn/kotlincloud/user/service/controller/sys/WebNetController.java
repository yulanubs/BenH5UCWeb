package cn.kotlincloud.user.service.controller.sys;

import cn.kotlincloud.user.common.util.sys.ValueUtils;
import cn.kotlincloud.user.service.controller.base.BaseController;
import cn.kotlincloud.user.service.entity.model.Webnet;
import cn.kotlincloud.user.service.services.provider.WebnetServiceProvider;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Page;

import java.util.Date;
import java.util.List;

import static cn.kotlincloud.user.service.config.BaiDuConfig.ENDPOINT;


/**
 * Created by yulan on 2019/8/28.
 */
public class WebNetController extends BaseController {
    @Inject
    private WebnetServiceProvider wnsp;

    public  void  index(){
        renderText("欢迎使用站点API");
    }

    /**
     * 新增一个网站信息
     */
    public  void  save(){
        String title = get("title");
        String subtitle = get("subtitle");
        String keyword = get("keyword");
        String domainName = get("domainName");
        String AuthorizationCode = get("AuthorizationCode");
        String iconUrl = get("iconUrl");
        String logoUrl = get("logoUrl");
        String cdnUrl = get("cdnUrl");
        String icpNumber = get("icpNumber");
        String beianNumber = get("beianNumber");
        String statistical = get("statistical");
        String CopyrightInfo = get("CopyrightInfo");
        String description = get("description");
        Integer userId = getInt("userId");
        Webnet webnet=new Webnet();
        webnet.setTitle(title);
        webnet.setSubtitle(subtitle);
        webnet.setKeyword(keyword);
        webnet.setDomainName(domainName);
        webnet.setAuthorizationCode(AuthorizationCode);
        webnet.setIconUrl(iconUrl);
        webnet.setLogoUrl(logoUrl);
        webnet.setCdnUrl(cdnUrl);
        webnet.setIcpNumber(icpNumber);
        webnet.setBeianNumber(beianNumber);
        webnet.setStatistical(statistical);
        webnet.setCopyrightInformation(CopyrightInfo);
        webnet.setCreateUserId(userId);
        webnet.setDescription(description);
        webnet.setCreteTime(new Date());
        webnet.setUpdateUserId(userId);
        webnet.setUpdateTime(new Date());
        webnet.setCode(String.valueOf(new Date().getTime()));
        Object save = wnsp.save(webnet);
        renderJson(isProduceCode(save));
    }


    /**
     * 查询所有网站
     */
    public void  findAll(){
        List<Webnet> webnetList = wnsp.findAll();
        for (Webnet w:webnetList
             ) {
            w.setLogoUrl(ENDPOINT+"/"+w.getLogoUrl());
            w.setIconUrl(ENDPOINT+"/"+w.getIconUrl());

        }
        renderJson(isProduceCode(webnetList));
    }

    /**
     * 根据网站id查询网站信息
     */


    public  void  findById(){

        Integer id = getInt("id");
        if (ValueUtils.isEmpty(id)||id==0){
            renderText("必要参数不能为空！");
            return;
        }

        Webnet webnet = wnsp.findById(id);
        renderJson(isProduceCode(webnet));
    }

    /**
     * 根据id删除一条网站信息
     */

    public void delById(){
        Integer id = getInt("id");
        if (ValueUtils.isEmpty(id)||id==0){
            renderText("必要参数不能为空！");
            return;
        }
        boolean deleteById = wnsp.deleteById(id);
        renderJson(isProduceCode(deleteById));
    }

    /**
     * 根据id修改网站信息
     */

    public  void  updateById(){
        Integer id = getInt("id");
        if (ValueUtils.isEmpty(id)||id==0){
            renderText("必要参数不能为空！");
            return;
        }

        String title = get("title");
        String subtitle = get("subtitle");
        String keyword = get("keyword");
        String domainName = get("domainName");
        String AuthorizationCode = get("AuthorizationCode");
        String iconUrl = get("iconUrl");
        String logoUrl = get("logoUrl");
        String cdnUrl = get("cdnUrl");
        String icpNumber = get("icpNumber");
        String beianNumber = get("beianNumber");
        String statistical = get("statistical");
        String CopyrightInfo = get("CopyrightInfo");
        String description = get("`description`");
        Integer userId = getInt("userId");

        Webnet webnet = wnsp.findById(id);

        if (ValueUtils.isNotEmpty(webnet)){
            if (ValueUtils.isStrNotEmpty(title)){
                webnet.setTitle(title);
            }

            if (ValueUtils.isStrNotEmpty(subtitle)){
                webnet.setSubtitle(subtitle);
            }

            if (ValueUtils.isStrNotEmpty(keyword)){
                webnet.setKeyword(keyword);
            }
            if (ValueUtils.isStrNotEmpty(description)){
                webnet.setKeyword(description);
            }

            if (ValueUtils.isStrNotEmpty(domainName)){
                webnet.setDomainName(domainName);
            }

            if (ValueUtils.isStrNotEmpty(AuthorizationCode)){
                webnet.setAuthorizationCode(AuthorizationCode);
            }

            if (ValueUtils.isStrNotEmpty(iconUrl)){
                webnet.setIconUrl(iconUrl);
            }

            if (ValueUtils.isStrNotEmpty(logoUrl)){
                webnet.setLogoUrl(logoUrl);
            }

            if (ValueUtils.isStrNotEmpty(cdnUrl)){
                webnet.setCdnUrl(cdnUrl);
            }

            if (ValueUtils.isStrNotEmpty(icpNumber)){
                webnet.setIcpNumber(icpNumber);
            }

            if (ValueUtils.isStrNotEmpty(beianNumber)){
                webnet.setBeianNumber(beianNumber);
            }

            if (ValueUtils.isStrNotEmpty(statistical)){
                webnet.setStatistical(statistical);
            }

            if (ValueUtils.isStrNotEmpty(CopyrightInfo)){
                webnet.setCopyrightInformation(CopyrightInfo);
            }

            if (ValueUtils.isNotEmpty(userId)&&userId!=0){
                webnet.setUpdateUserId(userId);
            }
            webnet.setUpdateTime(new Date());


            boolean update = wnsp.update(webnet);
            renderJson(isProduceCode(update));

        }else {

            renderJson(isProduceCode(webnet));
        }


    }

    /**
     * 分页查询
     */

    public  void  pageAll(){
        Integer pageNumber = getInt("pageNumber");
        Integer pageSize = getInt("pageSize");
        Page<Webnet> servicePage = wnsp.paginate(pageNumber, pageSize);
        renderJson(isProduceCode(servicePage));
    }

    /**
     * 根据授权码查询网站信息
     */

    public  void  findByACode(){
        String authorizationCode = get("AuthorizationCode");

        if (ValueUtils.isStrEmpty(authorizationCode)){
            renderText("必要参数不能为空！");
            return;
        }

        String swl= "SELECT * FROM webnet WHERE Authorization_code = ?";
        List<Webnet> webnetList = wnsp.find(swl, authorizationCode);
        renderJson(isProduceCode(webnetList));
    }
}
