package cn.exrick.manager.service;

import cn.exrick.manager.dto.ContentCatDto;
import cn.exrick.manager.pojo.TbContentCategory;
import cn.exrick.manager.pojo.ZTreeNode;

import java.util.List;

public interface ContentCatService {

    TbContentCategory getContentCatById(Long id);

    List<ZTreeNode> getContentCatList(Long parentId);

    int addContentCat(ContentCatDto contentCatDto);

    int updateContentCat(ContentCatDto contentCatDto);

    int deleteContentCat(Long id);
}
