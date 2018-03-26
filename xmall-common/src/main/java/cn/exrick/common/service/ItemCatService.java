package cn.exrick.common.service;

import cn.exrick.common.pojo.TbItemCat;
import cn.exrick.common.pojo.ZTreeNode;

import java.util.List;

/**
 * Created by Exrick on 2017/8/2.
 */
public interface ItemCatService {

    TbItemCat getItemCatById(Long id);

    List<ZTreeNode> getItemCatList(int parentId);

    int addItemCat(TbItemCat tbItemCat);

    int updateItemCat(TbItemCat tbItemCat);

    void deleteItemCat(Long id);

    void deleteZTree(Long id);
}
