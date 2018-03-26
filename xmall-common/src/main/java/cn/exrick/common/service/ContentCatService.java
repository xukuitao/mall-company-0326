package cn.exrick.common.service;

import cn.exrick.common.pojo.ContentCatDto;
import cn.exrick.common.pojo.TbContentCategory;
import cn.exrick.common.pojo.ZTreeNode;

import java.util.List;

public interface ContentCatService {

    TbContentCategory getContentCatById(Long id);

    List<ZTreeNode> getContentCatList(Long parentId);

    int addContentCat(ContentCatDto contentCatDto);

    int updateContentCat(ContentCatDto contentCatDto);

    int deleteContentCat(Long id);
}
