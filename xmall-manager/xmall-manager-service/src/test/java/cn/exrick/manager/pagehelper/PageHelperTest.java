package cn.exrick.manager.pagehelper;

import mapper.TbItemMapper;
import manager.pojo.TbItem;
import manager.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Exrick on 2017/7/31.
 */
public class PageHelperTest {

    @Test
    public void testPageHelper() throws Exception{
        //初始化spring容器
        ApplicationContext applicationContext=new
                ClassPathXmlApplicationContext("spring/spring/applicationContext-dao.xml");
        TbItemMapper itemMapper=applicationContext.getBean(TbItemMapper.class);
        PageHelper.startPage(1,10);
        //执行查询
        TbItemExample example=new TbItemExample();
        List<TbItem> list=itemMapper.selectByExample(example);
        //取分页信息
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        System.out.println(list.size());
    }
}
