package com.cap.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Created by cmhy on 2018/6/20.
 */
@Component
public class DateUtil {

    public Date timeStampToDate(long timeStamp){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
        return new Date(Long.parseLong(String.valueOf(timeStamp)));   // 时间戳转换成时间
    }
}
