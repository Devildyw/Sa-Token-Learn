package top.devildyw.satoken_login.service;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.druid.sql.visitor.functions.If;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cuit.epoch.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.devildyw.satokenlearn.satoken_common.domain.entity.User;
import top.devildyw.satokenlearn.satoken_common.domain.model.LoginBody;
import top.devildyw.satokenlearn.satoken_common.mapper.UserMapper;
import top.devildyw.satokenlearn.satoken_common.service.UserService;
import top.devildyw.satokenlearn.satoken_common.utils.DigestsUtil;

/**
 * @author Devil
 * @since 2022-10-30-17:17
 */
@Service
public class LoginService {

    private static final Logger LOGGER = LogManager.getLogger(LoginService.class);
    @Autowired
    private UserService userService;

    public boolean login(LoginBody loginBody) {
        User user = userService.findUserByUsername(loginBody.getUsername());
        //如果库中无该用户
        if (ObjectUtil.isNull(user)){
            return false;
        }
        //进行密码匹配
        String rawPwd = user.getPassword();
        String pwd = loginBody.getPassword();
        LOGGER.info("pwd:{}",pwd);
        LOGGER.info("rawPwd:{}",rawPwd);
        pwd = SecureUtil.sha256(pwd);
        LOGGER.info("pwd:{}",pwd);
        if (!match(pwd,rawPwd)){
            throw new AppException("密码错误");
        }

        UUID uuid = UUID.fastUUID();
        StpUtil.login(uuid);

        String token = StpUtil.getTokenInfo().getTokenValue();
        LOGGER.info("token:{}",token);

        return true;

    }

    private boolean match(String pwd, String rawPwd) {
        return BooleanUtil.isTrue(pwd.equals(rawPwd));
    }


}
