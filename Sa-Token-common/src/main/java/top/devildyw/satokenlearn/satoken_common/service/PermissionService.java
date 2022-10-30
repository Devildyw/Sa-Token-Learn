package top.devildyw.satokenlearn.satoken_common.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.devildyw.satokenlearn.satoken_common.domain.entity.Role;
import top.devildyw.satokenlearn.satoken_common.domain.entity.User;
import top.devildyw.satokenlearn.satoken_common.mapper.PermissionMapper;
import top.devildyw.satokenlearn.satoken_common.mapper.RoleMapper;

import java.util.List;

/**
 * @author Devil
 * @since 2022-10-30-23:47
 */
@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserService userService;
    public List<String> findPermissionList(String username){
        User user = userService.findUserByUsername(username);
        Role role = roleMapper.selectRoleById(user.getId());
        List<String> list = permissionMapper.selectPermissionListByRId(role.getRId());
        return list;
    }
}
