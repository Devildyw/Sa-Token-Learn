package top.devildyw.satoken02_authentication.stpAuthentication;

import cn.dev33.satoken.stp.StpInterface;
import org.checkerframework.checker.units.qual.K;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.devildyw.satokenlearn.satoken_common.service.PermissionService;
import top.devildyw.satokenlearn.satoken_common.service.RoleService;

import java.util.List;

/**
 * @author Devil
 * @since 2022-10-30-18:38
 */
@Component
public class StpAuthenticationImpl implements StpInterface {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        String username = (String) loginId;
        return permissionService.findPermissionList(username);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        String username = (String) loginId;
        return roleService.findRoleListByUsername(username);
    }
}
