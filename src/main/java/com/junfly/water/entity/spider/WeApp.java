package com.junfly.water.entity.spider;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/22.
 */
public class WeApp {
    public String nick;
    public String appcode;
    public String wx_app_id;
    public String openid;
    public String last_docid;
    public WeApp(String _nick, String _appcode){
        nick = _nick;
        appcode = _appcode;

    }
    public WeApp(String _nick, String _appcode, String _openid, String _last_docid){
        this.nick = _nick;
        this.appcode = _appcode;
        this.openid = _openid;
        this.last_docid = _last_docid;
    }
    public static List<WeApp> getAll(){
        List<WeApp> apps = new ArrayList<WeApp>();
        WeApp app;

        app = new WeApp("小小包麻麻","xxbmm123","","");
        apps.add(app);

        return apps;
    }

}
