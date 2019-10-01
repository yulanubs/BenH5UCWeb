package cn.kotlincloud.user.service.tool;




import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import org.apache.commons.codec.binary.Base64;
/**
 * Created by yulan on 2019/9/25.
 * 微信相关工具类
 */
public class WxUtil {
    /**
     * 解密并且获取用户手机号码
     * @param encrypdata
     * @param ivdata
     * @param sessionkey
     * @return
     * @throws Exception
     */
    public static String deciphering(String encrypdata, String ivdata, String sessionkey) {


        String str = "";
        try {
            byte[] encrypData = Base64.decodeBase64( encrypdata );
            byte[] ivData = Base64.decodeBase64( ivdata );
            byte[] sessionKey = Base64.decodeBase64( sessionkey );
            str = decrypt( sessionKey, ivData, encrypData );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  str;
    }

    /**
     * 解密数据
     * @param key
     * @param iv
     * @param encData
     * @return
     * @throws Exception
     */

    public static String decrypt(byte[] key, byte[] iv, byte[] encData) throws Exception {
        AlgorithmParameterSpec ivSpec = new IvParameterSpec( iv );
        Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" );
        SecretKeySpec keySpec = new SecretKeySpec( key, "AES" );
        cipher.init( Cipher.DECRYPT_MODE, keySpec, ivSpec );
        //解析解密后的字符串  
       try {
           byte[] doFinal = cipher.doFinal( encData );
           return new String( doFinal, "UTF-8" );
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
    }
}

