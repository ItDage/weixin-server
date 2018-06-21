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
        System.out.println("appId" + appId + "encryptedData" + encryptedData + "session_key" + session_key + "iv" + iv);
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

//    public static void main(String[] args) {
//        String decrypt = WXCore.decrypt("wxe88fcf6c1f564e06", "L2Gc9PnOURyhfQT/4wcoVprTmO/u4sG7aF8V/yAavaJcI9PZUtMwOiq/0GRvQyp7JCb86YjwixMoj8KT8XPyQ7WoJoeGZb6Uz0EG1ckd3fVceblYXTYdMFB7Hgj4f0oJ1BmMpxYPYyFPdrb92IZCLjfDR6xP1VqonFZggeoZo9Aj21MKrZ4g63aEv18kpDyvGF3MYBXrNAlyRDuORNHOPDePzC0GmXm9Cg1iZCS0/Cp5MNiZ6Pr4fZj/ipR5gLo6OoJUf46bFO3vWnIVPvDFY8trIVNx3aFzXXK1U827nXyyKr5u65hRzvNJD4Xo+4Lih9kcZlXz8JyD4TpfJbLT1H+uujsWkwBS7hlgP7sLPRF40T4MU5RlKJH0QwNKcy1MKPLvkU2BJvcsNilm/zYq4bXiST4kuzZTlmWLsPBDocfOVg+fQsTA7GjmXaguG+9/pItusAg3zQ3yDynPpJxROakUuKq4DvGvRjGeWJN/CCPNIXwA8e/C9tlXBSDvn33ShfT7TFrGcMPidIYnhSbvRSzfDVAGjNuTJQBmDOBX3i1ECmFGst/E4IY67Z4tn8bo+XG2I1cZcNgBVLHORdbPYYci3pmss9rB8QJYg45y4lc40MvAX+1CTO/zl1daQB9Yumpwtet8gub9+3afKTDuuMz5PSAUfalPcCgLARQDS38eojzL6JmEuzvxfgNGy8CKDLXU4JUzbzLes4eaOrU4BAwgQLsC1CmZp6hNW1IaFBKkEHa6GxlZis+///UvmPNdd4cyIXZ4B5E0uDGq7geMDeO37UlfPQPvGF1zz49V/BLkMLAkg5CzClh1zMqgHBpR2xD8GXeKiIfrIzXhpuypHScKFUPzM6A//ZAvsyj9Xco6T2y8+sue/cyyqfWRhR7Rh0tWkztG3wNlijP0c8a0ZX3Xgxn74mjAS558TnL51LtNJ5jigHVXXtl0YbqQZH9cttBw/gQ9w5CCgE4td+jG+cKfuCpd/0yZznWqt50qLtkbhIkwmHmWi4/w31tSQv8sNRcJSqse9IS71N4LgRVDHSevKlYP8IanmePpEO9ub4yy8egSIitvSJsW+/yiTCTI21je3ybD3jwNrFAmbDwFk6/+JXSwBypu9bOHgWRc6I0Dt7pF/dndoHmTVrz+F4wVK6PImfDHVm3n9LsyjERn0vo16zM8csiBFtnKchx6XMlKX53d/xXYEfrvnpw+kfxvrh3goZjA1NpWeMGt72xVi6UzDM+jueLB9ssPVazQWJISJCZ/+5G4b8Bh9TUb6d3LT+/BBGxtzIdgh+cw8oUxGe36eL4Ma35X7j4erKXH20fosPpr7GbpD46ND0jYYJTKcVLFzIrUEhVfwwhd4IVZdd/XiI+dTKYD4w4qHU+C0RbIFKMYfXiaOPIsT5yiPfkNrvzsVKaaNyR3CaDtbvGOrotzFUXZNFoZT/9Y97tHVWi1CnTqPTF3a/33TpORF8F3J7n9cZ1EPAUs3upovXbg98YZrtJmSLJLwCFhhgZuuA1PNIBBM3CWS72FbY0qM9Tdkxc7690Nq3/WMcwo5BGhsTVjX2W3rpxkTHFI0GXgcLVH6XbUWFNpQ2uaWl+9oMSlgpxV1CzTPNKSZp34Qx82uk2I+oKxT3C3SCx0oj8XYmCQtPx7OKX3dJ4KBfBvoplK", "qcxXgiKck3eWe/qdpAmTxQ==", "uroVKlpjtOccnRxweIHRfw==");
//        System.out.println(decrypt);
//    }
}
