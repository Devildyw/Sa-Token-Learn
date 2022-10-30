package top.devildyw.satokenlearn.satoken_common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Devil
 * @since 2022-10-30-17:29
 */
@Configuration
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("top.devildyw.**.mapper")
public class ApplicationConfig {
}
