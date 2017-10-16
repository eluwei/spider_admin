package com.junfly.water.spider;

import com.junfly.water.SampleActiveMQApplication;
import com.junfly.water.entity.spider.Article;
import com.junfly.water.entity.spider.PybbsTopic;
import com.junfly.water.entity.spider.SpiderHis;
import com.junfly.water.entity.spider.WeApp;
import com.junfly.water.service.spider.PybbsTopicService;
import com.junfly.water.service.spider.SpiderHisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/9/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleActiveMQApplication.class)
public class AppByNickSpiderSpec {

    private Logger logger = LoggerFactory.getLogger(AppByNickSpiderSpec.class);

    @Autowired
    private PybbsTopicService pybbsTopicService;

    @Autowired
    private SpiderHisService spiderHisService;

    /**
     * 根据微信号昵称爬取
     */
    @Test
    public void spiderByNick() {
        List<WeApp> apps = WeApp.getAll();
        WeApp app =  apps.get(0);
        ArticlesByAppSpider sp = new ArticlesByAppSpider();
        List<Article> articles = sp.crawlArticles(app);
        for (Article article : articles) {
            List<SpiderHis> spiderHis = spiderHisService.queryByTitle(article.getTitle());
            if (spiderHis == null || spiderHis.isEmpty()) {
                PybbsTopic pybbsTopic = new PybbsTopic();
                pybbsTopic.setContent(article.getContent());
                pybbsTopic.setTitle(article.getTitle());
                pybbsTopic.setInTime(new Date());
                pybbsTopic.setGood(0);
                pybbsTopic.setLabelId("2,");
                pybbsTopic.setModifyTime(new Date());
                pybbsTopic.setLastReplyTime(null);
                pybbsTopic.setTab("分享");
                pybbsTopic.setReplyCount(0);
                pybbsTopic.setTop(0);
                pybbsTopic.setTopicLock(0);
                pybbsTopic.setUpIds("");
                pybbsTopic.setUserId(1);
                pybbsTopic.setView(0);
                pybbsTopicService.saveWithHistory(pybbsTopic);
            }
        }
    }
}
