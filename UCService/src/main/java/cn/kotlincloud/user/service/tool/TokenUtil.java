package cn.kotlincloud.user.service.tool;

import cn.kotlincloud.user.service.entity.token.Tokens;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 
	 * @ClassName:TokenUtil <BR>
     * @Describe：Token工具类，维护缓存<BR>
     * @Author: Jekshow
	 * @Extends：<BR>
     * @Version:1.0 
     * @date:2016-10-8 下午04:40:25
 */

public class TokenUtil {

    private static final int INTERVAL = 7;// Tokens过期时间间隔 天
    private static final String YAN = "testMRf1$789787aadfjkds//*-+'[]jfeu;384785*^*&%^%$%";// 加盐
    private static final int HOUR = 3;// 检查Tokens过期线程执行时间 时
    
    private static Logger logger = Logger.getLogger("visit");

    private static Map<Integer, Tokens> TokensMap = new HashMap<Integer, Tokens>();
    private static TokenUtil TokensUtil = null;
    static ScheduledExecutorService scheduler =Executors.newSingleThreadScheduledExecutor(); 

    static {
        logger.info("\n===============进入TokensUtil静态代码块==================");
        listenTask();
    }
    

    public static TokenUtil getTokensUtil() {
        if (TokensUtil == null) {
            synInit();
        }

        return TokensUtil;
    }

    private static synchronized void synInit() {
        if (TokensUtil == null) {
            TokensUtil = new TokenUtil();
        }
    }

    public TokenUtil() {
    }
    
    

    public static Map<Integer, Tokens> getTokensMap() {
        return TokensMap;
    }

    /**
     * 产生一个Tokens
     */
    public static Tokens generateTokens(String uniq,int id) {
        Tokens Tokens = new Tokens(MD5(System.currentTimeMillis()+YAN+uniq+id), System.currentTimeMillis());
        synchronized (TokensMap) {
            TokensMap.put(id, Tokens);
        }
        return Tokens;
    }


    /**
     * @Title: removeTokens
     * @Description: 去除Tokens
     * @param @param nonce
     * @param @return 参数
     * @return boolean 返回类型
     */
    public static boolean removeTokens(int id) {
        synchronized (TokensMap) {
            TokensMap.remove(id);
            logger.info(TokensMap.get(id) == null ? "\n=========已注销========": "\n++++++++注销失败+++++++++++++++");
        }
        return true;
    }

    /**
     * @Title: volidateTokens
     * @Description: 校验Tokens
     * @param @param signature
     * @param @param nonce
     * @param @return 参数
     * @return boolean 返回类型
     */
    public static boolean volidateTokens(String signature, int id) {
        boolean flag = false;
        Tokens Tokens = (Tokens) TokensMap.get(id);
        if (Tokens != null && Tokens.getSignature().equals(signature)) {
            logger.info("\n=====已在线=======");
            flag = true;
        }

        return flag;
    }
    
    /**
     * 
     * @Title: MD5
     * @Description: 加密
     * @param @param s
     * @param @return 参数
     * @return String 返回类型
     */
    public final static String MD5(String s) {
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            return byte2hex(mdInst.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将字节数组转换成16进制字符串
     * @param b
     * @return
     */
    private static String byte2hex(byte[] b) {
        StringBuilder sbDes = new StringBuilder();
        String tmp = null;
        for (int i = 0; i < b.length; i++) {
            tmp = (Integer.toHexString(b[i] & 0xFF));
            if (tmp.length() == 1) {
                sbDes.append("0");
            }
            sbDes.append(tmp);
        }
        return sbDes.toString();
    }
    
    /**
    * @Title: listenTask 
    * @Description: 定时执行Tokens过期清除任务
    * @return void    返回类型
     */
    public static void listenTask(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //定制每天的HOUR点，从明天开始
        calendar.set(year, month, day+1, HOUR, 0, 0);
       // calendar.set(year, month, day, 17, 11, 40);
        Date date = calendar.getTime();
        
        scheduler.scheduleAtFixedRate( new ListenTokens(), (date.getTime()-System.currentTimeMillis())/1000, 60*60*24, TimeUnit.SECONDS);
    }
    
    

    /**
     * @ClassName: ListenTokens
     * @Description: 监听Tokens过期线程runnable实现
     * @author mrf
     * @date 2015-10-21 下午02:22:24
     * 
     */
    static class ListenTokens implements Runnable {
        public ListenTokens() {
            super();
        }

        public void run() {
            logger.info("\n**************************执行监听Tokens列表****************************");
            try {
                synchronized (TokensMap) {
                    for (int i = 0; i < 5; i++) {
                        if (TokensMap != null && !TokensMap.isEmpty()) {
                            for (Map.Entry<Integer, Tokens> entry : TokensMap.entrySet()) {
                                Tokens Tokens = (Tokens) entry.getValue();
                                logger.info("\n==============已登录用户有："+entry + "=====================");
                            try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
                                int interval = (int) ((System.currentTimeMillis() - Tokens.getTimestamp()) / 1000 / 60 / 60 / 24);
                                if (interval > INTERVAL) {
                                    TokensMap.remove(entry.getKey());
                                    logger.info("\n==============移除Tokens：" + entry+ "=====================");
                                }

                            }
                        }
                    }
                    
                }
            } catch (Exception e) {
                logger.error("Tokens监听线程错误："+e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
     

//    public static void main(String[] args) {
//        System.out.println(generateTokens( "s",1));
//        System.out.println(generateTokens( "q",1));
//        System.out.println(generateTokens( "s3",2));
//        System.out.println(generateTokens( "s4",3));
//        System.out.println(removeTokens(3));
//        System.out.println(getTokensMap());
//    }

}