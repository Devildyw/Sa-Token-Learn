package top.devildyw.satokenlearn.satoken_common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.devildyw.satokenlearn.satoken_common.domain.entity.Role;

import java.util.List;

/**
 * @author Devil
 * @since 2022-10-30-17:12
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select * from role where r_id =  (select rid from user_role where uid = #{uid})")
    Role selectRoleById(Integer uid);


    @Select("select r_name from role where r_id = (select rid from user_role where uid = #{id})")
    List<String> selectRoleListByUId(Integer id);
}
