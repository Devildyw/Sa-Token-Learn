package top.devildyw.satokenlearn.satoken_common.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.devildyw.satokenlearn.satoken_common.domain.entity.User;
import top.devildyw.satokenlearn.satoken_common.mapper.UserMapper;

/**
 * @author Devil
 * @since 2022-10-30-17:22
 */
@Component
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserByUsername(String username){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);

        return userMapper.selectOne(queryWrapper);
    }
}
