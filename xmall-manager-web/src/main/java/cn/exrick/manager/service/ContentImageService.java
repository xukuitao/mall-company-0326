package cn.exrick.manager.service;

import cn.exrick.manager.pojo.DataTablesResult;
import cn.exrick.manager.pojo.TbImage;

public interface ContentImageService {

    TbImage getContentImageById(Long id);

    DataTablesResult getContentImage();

    int updateContentImage(TbImage tbImage);
}
