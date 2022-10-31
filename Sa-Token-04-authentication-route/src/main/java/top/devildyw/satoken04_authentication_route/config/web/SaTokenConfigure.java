package top.devildyw.satoken04_authentication_route.config.web;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 使用路由来鉴权
 *
 * @author Devil
 * @since 2022-10-31-16:03
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //自定义认证规则：new SaInterceptor(handle -> StpUtil.checkLogin()) 是最简单的写法，代表只进行登录校验功能。
        //注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin 登录校验
//        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin())) //lambda 表达式
//                .addPathPatterns("/**")
//                .excludePathPatterns("/acc/doLogin");

        //注册Sa-Token 拦截器 ，定义详细的认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
            SaRouter
                    .match("/**") //拦截的 path 列表，可以写多个
                    .notMatch("/acc/doLogin") // 排除掉的 path 列表，可以写多个
                    .check(r -> StpUtil.checkLogin()); // 要执行的校验动作，可以写完整的 lambda表达式

            // 根据路由划分模块，不同模块不同鉴权
            SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
            SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
            SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
            SaRouter.match("/orders/**", r -> StpUtil.checkPermission("orders"));
            SaRouter.match("/notice/**", r -> StpUtil.checkPermission("notice"));
            SaRouter.match("/comment/**", r -> StpUtil.checkPermission("comment"));
        })).addPathPatterns("/**");
    }
}
