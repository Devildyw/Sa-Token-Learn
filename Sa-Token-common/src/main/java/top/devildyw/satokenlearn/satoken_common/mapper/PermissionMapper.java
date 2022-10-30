package top.devildyw.satokenlearn.satoken_common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.devildyw.satokenlearn.satoken_common.domain.entity.Permission;

import java.util.List;

/**
 * @author Devil
 * @since 2022-10-30-17:13
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("select p_name from permission where p_id in (select pid from role_permission where rid = #{rId})")
    List<String> selectPermissionListByRId(Integer rId);
}
