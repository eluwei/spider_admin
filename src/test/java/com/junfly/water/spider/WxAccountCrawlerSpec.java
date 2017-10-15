package com.junfly.water.spider;

import com.junfly.water.SampleActiveMQApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/10/14 21:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleActiveMQApplication.class)
public class WxAccountCrawlerSpec {

    @Test
    public void whenInputNameThenGetArtile() throws Exception {
        WxAccountCrawler crawler = new WxAccountCrawler("crawl_weixin", "wx_history.txt");
        crawler.addAccount("宝宝树");
        crawler.setThreads(5);
        crawler.start(10);
    }
}
