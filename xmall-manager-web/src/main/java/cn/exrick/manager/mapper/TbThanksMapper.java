package cn.exrick.manager.mapper;

import cn.exrick.common.pojo.TbThanks;
import cn.exrick.common.pojo.TbThanksExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface TbThanksMapper {
    long countByExample(TbThanksExample example);

    int deleteByExample(TbThanksExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbThanks record);

    int insertSelective(TbThanks record);

    List<TbThanks> selectByExample(TbThanksExample example);

    TbThanks selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbThanks record, @Param("example") TbThanksExample example);

    int updateByExample(@Param("record") TbThanks record, @Param("example") TbThanksExample example);

    int updateByPrimaryKeySelective(TbThanks record);

    int updateByPrimaryKey(TbThanks record);
}