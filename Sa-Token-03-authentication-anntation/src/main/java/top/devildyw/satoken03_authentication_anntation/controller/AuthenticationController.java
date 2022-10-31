package top.devildyw.satoken03_authentication_anntation.controller;

import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Devil
 * @since 2022-10-31-15:36
 */
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    //登录校验
    @SaCheckLogin
    @GetMapping("info")
    public String info(){
        return "用户信息";
    }

    //角色校验
    @SaCheckRole("admin")
    @GetMapping("add")
    public String add(){
        return "add";
    }

    //权限验证
    @SaCheckPermission("insert")
    @GetMapping("insert")
    public String insert(){
        return "insert";
    }

    //二级认证
    @SaCheckSafe()
    @RequestMapping("add-info")
    public String addInfo(){
        return "用户增加";
    }

    //Http Basic 校验 只有通过 Basic 认证后才能进入该方法
    @SaCheckBasic(account = "sa:123456")
    @GetMapping("basic")
    public String basic(){
        return "basic";
    }

    //校验当前账号是否被风景 comment 服务，如果已被封禁会抛出异常，无法进入方法
    @SaCheckDisable("comment")
    @GetMapping("send")
    public String send(){
        return "查询用户信息";
    }

    // 注解式鉴权：只要具有其中一个权限即可通过校验
    @RequestMapping("atJurOr")
    @SaCheckPermission(value = {"user-add", "user-all", "user-delete"}, mode = SaMode.OR)
    public SaResult atJurOr() {
        return SaResult.data("用户信息");
    }

    // 角色权限双重 “or校验”：具备指定权限或者指定角色即可通过校验
    @RequestMapping("userAdd")
    @SaCheckPermission(value = "user.add", orRole = "admin")
    public SaResult userAdd() {

        return SaResult.data("用户信息");
    }

    // 此接口加上了 @SaIgnore 可以游客访问
    @SaIgnore
    @RequestMapping("getList")
    public SaResult getList() {
        // ...
        return SaResult.ok();
    }

}
