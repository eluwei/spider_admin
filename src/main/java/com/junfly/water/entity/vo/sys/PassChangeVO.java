package com.junfly.water.entity.vo.sys;

import java.io.Serializable;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/5/31 17:14
 */
public class PassChangeVO implements Serializable {

    private static final long serialVersionUID = -8240311896598348191L;

    private String userid;

    private String oldPass;

    private String newPass;

    private String newPassAgain;

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getNewPassAgain() {
        return newPassAgain;
    }

    public void setNewPassAgain(String newPassAgain) {
        this.newPassAgain = newPassAgain;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }
}
