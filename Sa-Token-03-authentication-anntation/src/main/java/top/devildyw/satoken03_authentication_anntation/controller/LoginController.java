package top.devildyw.satoken03_authentication_anntation.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.BooleanUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.devildyw.satoken03_authentication_anntation.service.LoginService;
import top.devildyw.satokenlearn.satoken_common.domain.model.LoginBody;

import javax.validation.Valid;

/**
 * @author Devil
 * @since 2022-10-29-18:15
 */
@RestController
@RequestMapping("acc")
public class LoginController {

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    //测试登录
    @PostMapping("doLogin")
    public SaResult doLogin(@Valid @RequestBody LoginBody loginBody) {
        //这里做处理 根据传入的数据 在数据库中做比较
        boolean result = loginService.login(loginBody);

        return BooleanUtil.isTrue(result)?SaResult.ok("登录成功"):SaResult.error("登录失败");
    }

    //查询登录状态
    @GetMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    //查询token 信息
    @GetMapping("tokenInfo")
    public SaResult tokenInfo() {
        LOGGER.info(StpUtil.getTokenInfo());
        return SaResult.data(StpUtil.getTokenInfo());
    }


    //测试注销
    @GetMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    @GetMapping("auth")
    public SaResult auth(){
        System.out.println(StpUtil.getPermissionList());
        System.out.println(StpUtil.getRoleList());
        return SaResult.data(StpUtil.getPermissionList()+"\n"+StpUtil.getRoleList());
    }
}
