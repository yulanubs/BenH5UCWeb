package cn.kotlincloud.user.service.config;

/**
 * Created by yulan on 2019/8/18.
 */
public class SerciceConstant {
    /*允许上传文件大小10M**/
    public  static int MaxPostSize =1000 * 1024 * 1024;
    public static String SERVICE_UPLOAD_PATH = "/upload/";
    //BenHH5AI开发平台网站授权码
    public static String WebNet_Authorization_Code = "5006395701";
    public static long id = 122012;
    /**Redis集群自定义集合名称*/
    public static String RedisBenH5UserServiceName = "BenH5UserServiceData";
    /**Redis集群密码*/
    public static String RedisPwd = "Ubs547629134307";
    /**Redis集群链接超时时间*/
    public static Integer RedisRimeout = 30000;
    /**Redis最大重定向次数*/
    public static Integer Redirections = 6;

    //Redis缓存key值
    public  static  final String find_New_Service_Redis_Ceche_Key="/api/service/findNewService";
    public  static  final String find_New_Case_Redis_Ceche_Key="/api/case/findNewCase";
    public  static  final String find_New_Case_Redis_Ceche_Key_Front="/Front/getNewCases";
    public  static  final String find_Product_Type_All_Redis_Ceche_Key="/api/product/findTypeAll";



    //提示语
    public  static  final  String error_msg="亲，必要参数不能为空！";
    //数据key
    public  static  final String User_Login_info="User_Login_info";
}
