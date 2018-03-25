package cn.exrick.manager.mapper;

import cn.exrick.common.pojo.TbLog;
import cn.exrick.common.pojo.TbLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface TbLogMapper {
    long countByExample(TbLogExample example);

    int deleteByExample(TbLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbLog record);

    int insertSelective(TbLog record);

    List<TbLog> selectByExample(TbLogExample example);

    TbLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbLog record, @Param("example") TbLogExample example);

    int updateByExample(@Param("record") TbLog record, @Param("example") TbLogExample example);

    int updateByPrimaryKeySelective(TbLog record);

    int updateByPrimaryKey(TbLog record);
}