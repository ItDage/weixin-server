package com.cap.entity;

import java.util.List;

/**
 * Created by cmhy on 2018/6/20.
 */
public class Sport {

    private String encryptedData;
    private String iv;
    private String openId;
    //存储返回的步数
    private List<StepInfo> stepInfoList;

    public void setList(List<StepInfo> stepInfoList) {
        this.stepInfoList = stepInfoList;
    }

    public List<StepInfo> getStepInfoList() {
        return stepInfoList;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
