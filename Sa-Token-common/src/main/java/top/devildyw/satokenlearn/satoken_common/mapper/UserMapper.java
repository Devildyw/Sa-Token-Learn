package top.devildyw.satokenlearn.satoken_common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.devildyw.satokenlearn.satoken_common.domain.entity.User;

/**
 * @author Devil
 * @since 2022-10-30-17:12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
