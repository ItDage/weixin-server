package com.cap.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.cap.entity.Sport;
import com.cap.entity.StepInfo;
import com.cap.util.DateUtil;
import com.cap.util.WXCore;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cmhy on 2018/6/20.
 */
@Service
public class SportsServiceImpl {

    @Autowired
    private DateUtil dateUtil;

    public List<StepInfo> getStepInfo(String appId, String encryptedData, String session_key, String iv){
        String decrypt = WXCore.decrypt(appId, encryptedData, session_key, iv);
        Gson gson = new Gson();
        Sport sport = gson.fromJson(decrypt, Sport.class);
        List<StepInfo> list = sport.getStepInfoList();
        Collections.sort(list, new Comparator<StepInfo>() {
            @Override
            public int compare(StepInfo o1, StepInfo o2) {
                if(o1.getTimestamp() > o2.getTimestamp()){
                    return -1;
                }else if(o1.getTimestamp() == o2.getTimestamp()){
                    return 0;
                }
                return 1;
            }
        });

        System.out.println(list.subList(0,9));
        return list.subList(0,9);
    }
}
