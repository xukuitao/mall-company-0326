package cn.exrick.search.mapper;

import cn.exrick.common.pojo.SearchItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ItemMapper {

    List<SearchItem> getItemList();

    SearchItem getItemById(@Param("id") Long id);

}