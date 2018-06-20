package com.cap.util;

/**
 * Created by cmhy on 2018/6/19.
 */
import org.apache.commons.codec.binary.Base64;

import net.sf.json.JSONObject;


/**
 * 封装对外访问方法
 * @author liuyazhuang
 *
 */
public class WXCore {

    private static final String WATERMARK = "watermark";
    private static final String APPID = "appid";
    /**
     * 解密数据
     * @return
     * @throws Exception
     */
    public static String decrypt(String appId, String encryptedData, String sessionKey, String iv){
        String result = "";
        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                result = new String(WxPKCS7Encoder.decode(resultByte));
                JSONObject jsonObject = JSONObject.fromObject(result);
                String decryptAppid = jsonObject.getJSONObject(WATERMARK).getString(APPID);
                if(!appId.equals(decryptAppid)){
                    result = "";
                }
            }
        } catch (Exception e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }


//    public static void main(String[] args) throws Exception{
//        String appId = "wxe88fcf6c1f564e06";
//        String encryptedData = "ipbTtaQnIMV2zjreSAaBbyjgw1QKMzE6nXlZT8UKqsYq8w/fUvjVwIj8bYA2OuWpkefukvTzRvu0j3Ptn9jhHOOMZ0VPC9PQpsL0S58R1EZyuksMBdJgDiZX7vQCf2Yf3NwkOVG3vXISZVb4Do9FbxlD+dbROy5aeCVgrBj6z9EcOvugQ+PNUR2tWjAezswP7VOPAEOvR22DUxHqKa+6QM71grEzU3Qk7uA53ZuNeZsaz3FrOEnVZqCjyzEbLAenhJ+WqfezhnhelDJsO3B2kHQtgRtqeG11goBFko5kYX+oKzSUCoxCRvMXDBP7jSmrImTZuiFayzE+w+4by3xXT2iV9ixcGRCFE9M6Rs9jWU8nsGtzvDEM1FofiwQsyGW3G51rbBtAeuphX1XUxT/RTO9Tuvqb3srshNhQZA75jAMzCelVCwLQ4e8dlQurAndrpyhh+v1Z1NovQkopXVHOkp3ndT1eHV1+pP2yk2uG7JkcpLz7rZ/f3Um1BxeF3QKvcGMltmZzCeiBTl6EgHTREQnr1wE305pW49GHWF5qOWBpj2XF1497xJHThu7IMmEh2yggH5CunpgWuvrdbPbOohV/MTw+Hi9ANrjYYPp8Oj3bFLW4/L/oy/qJiFO2cN8sCikJVCsjul71fTJ8djTFH0IvyT9TyR+GPqSnS4vEuKqlPI+ALbeW8RCVAC/nN/Jw7GStJKjDM6UIQ1/4mDUHLUUNnViXcMGhtZB99gDwJ7VoZcjB4Ml63m2D3IVNo4DfEj9xceKgrYtjCNNzYjsFBAlUWVkVGLKRtorxaWlvIoS++NkjonOAJzMyIyKbuZMRg/aYAJcXRIKntl8E3toOpr7qq9vhvkUfKwQTiejDMy6KeAxHEZi16GjU+jZbCbQJ812zWFcbyBfwpPbtxu6rL4+k04q4wP7+RFi70ZWwHCfvxxHb5OVndT7LW3GmxiYdDJqCs0A5pvxTxmi4TFKAvKjg7nm7A7t66wNrYCvH/xHpoJfFquH8+FX+5QqAFl+9pAkrp2OP7IW1pdLzZjhg4vYlqeeOJoYet/d5PZ2fFw3jAi4ckFsOGDTieB45vpAkLybDPZdfLdTgfRlL3gj0USm4iH0PK+roS1B9Io3S7lXTv3OkAsFPdbOFXfg/0BlpF1UqWHqWF2LNf2OfEg02g46VS93WbFxL+3MIagEHnFalNKCpRcGBQUQfWDz3ARx3q0bYcEbLQ+bnZjTcvQfB7Lh0qKlihgqugPXt2tJ9DJvTMLY7oTiyV4ePswa23F8CTpDTvnfnA4tulPL4SL7qWmDMQsvrvreNJ9jmLTtoaUxr4jdHFqw7L5ohIV330k6dAh1SO/GmbpKmjWc3+aBHJyhI5FZlGQkNFsio6uH//rBxNn11f6Sb6F9H4zz9GlHRGK8U0ZvlKfmZ0XPz5SxcplH+ctDJtWgqeumtXUiC3DTQxwm8GMQ/62ud2IC+TY9hC+5VIVhkBgpbdb4w0qHQrvLCdE0QBz4dK6bZ4IbUW49uG6ZH5+OBMm9Rq+N2UVG1YwiElPN5AZLxRMPSny4jOxiaygZ6/l7meWSfZXYu8JUJFop5KK2CCPg2WPqEbrpBk2EyF2387ruAIh44VVN5Xak/YSVN/uWV0zHOikrFifESf5BZ3rWrwVi8ha9Fyzsd";
//        String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
//        String iv = "cJbXseN846GyEs+MHfFx/w==";
//        System.out.println(decrypt(appId, encryptedData, sessionKey, iv));
//    }
}
