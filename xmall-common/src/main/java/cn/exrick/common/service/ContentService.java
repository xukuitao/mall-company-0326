package cn.exrick.common.service;

import cn.exrick.common.pojo.*;


public interface ContentService {

    int addContent(TbContent content);

    DataTablesResult getContentListByCid(Long cid);

    int deleteContent(Long id);

    int updateContent(TbContent content);

    TbContent getContentById(Long id);

    ProductHome getProductHome();

    ProductDet getProductDet(Long id);

    AllGoodsResult getAllProduct(int page, int size, String sort, int priceGt, int priceLte);
}
