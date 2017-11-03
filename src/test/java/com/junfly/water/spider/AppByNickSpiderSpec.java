package com.junfly.water.spider;

import com.junfly.water.SampleActiveMQApplication;
import com.junfly.water.entity.spider.*;
import com.junfly.water.service.spider.PybbsTopicService;
import com.junfly.water.service.spider.SpiderHisService;
import com.junfly.water.service.spider.SpiderSourceService;
import com.junfly.water.spider.helper.Browser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
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

    @Autowired
    private SpiderSourceService spiderSourceService;

    /**
     * 根据微信号昵称爬取
     */
    @Test
    public void spiderByNick() {
        List<SpiderSource> spiderSources = spiderSourceService.queryList(new HashMap<String, Object>());
        for (SpiderSource spiderSource : spiderSources) {
            ArticlesByAppSpider sp = new ArticlesByAppSpider();
            List<Article> articles = sp.crawlArticles(spiderSource);
            for (Article article : articles) {
                List<SpiderHis> spiderHis = spiderHisService.queryByTitle(article.getTitle());
                if (spiderHis == null || spiderHis.isEmpty()) {
                    article = sp.processArticleDetail(article);
                    PybbsTopic pybbsTopic = new PybbsTopic();
                    pybbsTopic.setContent(article.getContent());
                    pybbsTopic.setTitle(article.getTitle());
                    pybbsTopic.setInTime(new Date());
                    pybbsTopic.setGood(0);
                    pybbsTopic.setLabelId("");
                    pybbsTopic.setModifyTime(new Date());
                    pybbsTopic.setLastReplyTime(null);
                    pybbsTopic.setTab("精华");
                    pybbsTopic.setReplyCount(0);
                    pybbsTopic.setTop(0);
                    pybbsTopic.setTopicLock(0);
                    pybbsTopic.setUpIds("");
                    pybbsTopic.setUserId(1);
                    pybbsTopic.setView(0);
                    pybbsTopic.setPass(0);
                    pybbsTopic.setCoverImage(article.getImglink().replace("background-image: url(", "").replace(")",""));
                    try {
                        pybbsTopicService.saveWithHistory(pybbsTopic, "1");
                    } catch (Exception e) {
                        logger.info(pybbsTopic.toString());
                        logger.error(e.getMessage());
                    }
                }
            }
        }
        Browser.driver.quit();
    }
}
