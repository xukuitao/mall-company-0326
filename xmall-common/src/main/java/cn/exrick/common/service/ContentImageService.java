package cn.exrick.common.service;

import cn.exrick.common.pojo.DataTablesResult;
import cn.exrick.common.pojo.TbImage;

public interface ContentImageService {

    TbImage getContentImageById(Long id);

    DataTablesResult getContentImage();

    int updateContentImage(TbImage tbImage);
}
