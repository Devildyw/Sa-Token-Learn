package top.devildyw.satokenlearn.satoken_common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.devildyw.satokenlearn.satoken_common.domain.entity.User;
import top.devildyw.satokenlearn.satoken_common.mapper.RoleMapper;

import java.util.List;

/**
 * @author Devil
 * @since 2022-10-30-23:46
 */
@Service
public class RoleService {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleMapper roleMapper;

    public List<String> findRoleListByUsername(String username) {
        User user = userService.findUserByUsername(username);
        List<String> list = roleMapper.selectRoleListByUId(user.getId());
        return list;
    }
}
