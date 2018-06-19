package com.cap.util;

/**
 * Created by cmhy on 2018/6/19.
 */
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;


import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;


/**
 * 封装对外访问方法
 */
public class WXCore {

    private static final String WATERMARK = "watermark";
    private static final String APPID = "appid";
    /**
     * 解密数据
     * @return
     * @throws Exception
     */
    public static String illegalAesKey = "-41001";//非法密钥
    public static String illegalIv = "-41002";//非法初始向量
    public static String illegalBuffer = "-41003";//非法密文
    public static String decodeBase64Error = "-41004"; //解码错误
    public static String noData = "-41005"; //数据不正确

    private String appid;

    private String sessionKey;

    public WXCore(String appid, String sessionKey) {
        this.appid = appid;
        this.sessionKey = sessionKey;
    }

    /**
     * 检验数据的真实性，并且获取解密后的明文.
     * @param encryptedData  string 加密的用户数据
     * @param iv  string 与用户数据一同返回的初始向量
     * @return data string 解密后的原文
     * @return String 返回用户信息
     */
    public String decryptData(String encryptedData, String iv) {
        if (StringUtils.length(sessionKey) != 24) {
            return illegalAesKey;
        }
        // 对称解密秘钥 aeskey = Base64_Decode(session_key), aeskey 是16字节。
        byte[] aesKey = Base64.decodeBase64(sessionKey);

        if (StringUtils.length(iv) != 24) {
            return illegalIv;
        }
        // 对称解密算法初始向量 为Base64_Decode(iv)，其中iv由数据接口返回。
        byte[] aesIV = Base64.decodeBase64(iv);

        // 对称解密的目标密文为 Base64_Decode(encryptedData)
        byte[] aesCipher = Base64.decodeBase64(encryptedData);

        try {
            byte[] resultByte = AES.decrypt(aesCipher, aesKey, aesIV);
            if (null != resultByte && resultByte.length > 0) {
                String userInfo = new String(resultByte, "UTF-8");
                JSONObject jsons = JSONObject.fromObject(userInfo);
                String id = jsons.getJSONObject("watermark").getString("appid");
                if (!StringUtils.equals(id, appid)) {
                    return illegalBuffer;
                }
                return userInfo;
            } else {
                return noData;
            }
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) throws Exception{
        String appId = "wxe88fcf6c1f564e06";
        String encryptedData = "gumAFJwo1uO4iGBW5S2auXQmrfI1hex3UXTJaIda0arRpRKHmuCkx4rccY6EyKJMVqwYBbxfMCioda6om3iOyp/NYhf7bEM3yt/WTCphZNKRcUe344dF4NZ5e4vVQn647NNk5EGlBPm/+myUSPYWhHqTEhPka2If0NlipmA1JQ73Ao3/sDqpLycchFg4Fb53YIgE3xkSzuIxUvw/ndzbsTCeSwHH/f2O3QN5I46E4cD03uoN3//W4e/qCsIX54DcMatM5rJ6aCXu0ycsdnUiV9N1Ltg6Hv3cv5RCBR6yQIIAaLcdnFUVoZAXDtx61Bqxg7H3bjfuD6BkxmGLI/6Z88ZEsd+F3M5+xMKnJZuS1xiImWW9gsb9Zu9gg3MREoR4QBF/VbbUPB/dwxhFipDVg8QPB5aaMxylz4J9HmUnlC5GIzlaDZIQQz4mdVwIY/H6vPzi1AK7JMZvoh28uBqwQJqnmSmP4VoYycUlEnD3uLK0Opa2p5yY5PPT9oYyQAa+tzNVi0I+pO/XfxQO4OXewTK6VzgrSmbINiL2CtD9wQipJwrIbhSKnMHPjuWdVND3JZk7Kg9i/gjDgz1ZwfK0JfZ4Ml+2cMvlY6+D1xZX/yvD17E+ZiBFJi7j7QFqjcrHf6E0AdFdiEItB4lmodulNdIooW/MveG4CEJSYeECg/pXUs+qqwylKNl+I8j8zTOhfjL2W/YSMIcSiX2Lpx+4Ov3W89J+u0zdbZybczSllWpnBrXtdDI1U5aG3myhxhR8i9b0BIiQXKNDYuJvssNwOqiAZAy7sFKcU51NKbPQw7UUaJWUZhKulNw2Rg065mqbr6rSeEXnZDm3RtU0Dx+29o5SuuvKY7vwa3PoYxgCFutvWw4LJStF3iPSr2An440fvNwOVHohOnO+PJcGXYCMc3bC0QVquBuzIf4XlW7vPAKW+DNt4IayRfMmoQKaNQRdgY4qh75xH2DjQVyPgiETz0uKr5NzDl3EDeomhbxjIhXyJ5QdKLFD2jEn/3Y2fmYg82DXfTICEdzITzrNV8KU5bXQhirF01UU7sCCa29JoqxokfRGOLSSpwiXeGdfEL8dZPRqtuP6oik4bIo+K3azG4b+cq3bBZGCWliUhHTCstuDrfB/H+mGwK1rQav6OSMLq4sZyFCjGGnHNtjLV8/RrTz1dUY6kw4VuZbgKg6VlQoVe3Y29epyWAphfDuzUSGE80OEacymUYqeXl0T1ncvfbZyJvJSfhszIjdvC7/B/SlU0P3+/xMrfvaLmiiGbWzGJ3X2yjLqV4q+eO/T12jFFG0ogimbY2voSmA5kRVwnZ0371I6rqPiWDjkvPRX6byObz81HCTUvLndR3KkshOTlfCYTHj4kelhLwU3P/EYpgdCVjRvNtxuGRwOrMbPVt2EF8TEBXlwsLSGGrfgBnOWU7zuGrZsMIzuPlkipANhaJTUNufWYnPicq+73I9W/K8rFcUxVWCMYZnjJ3VTs9b9/+X/fuTQ6MuIfB4i19uPx2BOd3QtcK2wFXTwiyg5NupL53glm+Km++YplmAyVsBsY53ud1JoAA+PsG9h0Xktf/eiE3AqSPfL5GNyBkYR1XnkeJapUdLTMxkiT8Cn+F/InFcixbGMM6r0/LwKnl0SXjxKZBQxI6cwiPC+c4BDC7bN";
        String sessionKey = "Kg1QRHjT+LHpGQG74RE6aA==";
        String iv = "RmHsdtr/FxKvnF/8addeHw==";
        WXCore biz = new WXCore(appId, sessionKey);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(new Date(Long.parseLong(String.valueOf("1529251200000"))));
        System.out.println(s);

        System.out.println(biz.decryptData(encryptedData, iv));
    }
}