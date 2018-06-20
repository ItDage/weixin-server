package com.cap.entity;

/**
 * Created by cmhy on 2018/6/20.
 */
public class StepInfo {

    private long timestamp;
    private long step;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getStep() {
        return step;
    }

    public void setStep(long step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "StepInfo{" +
                "timestamp=" + timestamp +
                ", step=" + step +
                '}';
    }
}
