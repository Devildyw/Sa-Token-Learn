package top.devildyw.satoken05_session.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Devil
 * @since 2022-10-31-16:53
 */
@RestController
@RequestMapping("session")
public class SessionController {

    @GetMapping("token-session")
    public SaResult tokenSession(){
        return SaResult.data(StpUtil.getTokenSession());
    }

    @GetMapping("user-session")
    public SaResult userSession(){
        return SaResult.data(StpUtil.getSession());

    }

    @PostMapping("set-token-session")
    public SaResult setTokenSession(String key,String value){
        SaSession tokenSession = StpUtil.getTokenSession();
        tokenSession.set(key,value);
        return SaResult.ok();
    }

    @PostMapping("set-user-session")
    public SaResult setUserSession(String key,String value){
        SaSession session = StpUtil.getSession();
        session.set(key,value);
        return SaResult.ok();
    }


    @GetMapping("get-token-session-data")
    public SaResult getTokenSessionData(String key){
        SaSession tokenSession = StpUtil.getTokenSession();
//      String value =   tokenSession.getString(key); //多种方式
        String value = (String) tokenSession.get(key);
        return SaResult.data(value);
    }

    @GetMapping("get-user-session-data")
    public SaResult getUserSessionData(String key){
        SaSession tokenSession = StpUtil.getTokenSession();
//      String value =   tokenSession.getString(key); //多种方式
        String value = (String) tokenSession.get(key);
        return SaResult.data(value);
    }


    @GetMapping("anon-token-session")
    public SaResult getAnonTokenSession(){
        SaSession anonTokenSession = StpUtil.getAnonTokenSession();
        System.out.println(StpUtil.getTokenInfo());
        return SaResult.data(anonTokenSession);
    }

}
