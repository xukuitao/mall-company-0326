package cn.exrick.search.mapper;

import java.util.List;

import cn.exrick.common.pojo.TbAddress;
import cn.exrick.common.pojo.TbAddressExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface TbAddressMapper {
    long countByExample(TbAddressExample example);

    int deleteByExample(TbAddressExample example);

    int deleteByPrimaryKey(Long addressId);

    int insert(TbAddress record);

    int insertSelective(TbAddress record);

    List<TbAddress> selectByExample(TbAddressExample example);

    TbAddress selectByPrimaryKey(Long addressId);

    int updateByExampleSelective(@Param("record") TbAddress record, @Param("example") TbAddressExample example);

    int updateByExample(@Param("record") TbAddress record, @Param("example") TbAddressExample example);

    int updateByPrimaryKeySelective(TbAddress record);

    int updateByPrimaryKey(TbAddress record);
}