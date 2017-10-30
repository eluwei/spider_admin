package com.junfly.water.job;

import com.junfly.water.entity.spider.Article;
import com.junfly.water.entity.spider.PybbsTopic;
import com.junfly.water.entity.spider.SpiderHis;
import com.junfly.water.entity.spider.SpiderSource;
import com.junfly.water.service.spider.PybbsTopicService;
import com.junfly.water.service.spider.SpiderHisService;
import com.junfly.water.service.spider.SpiderSourceService;
import com.junfly.water.spider.ArticlesByAppSpider;
import com.junfly.water.spider.helper.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/10/18 21:43
 */
@Component
@EnableScheduling
public class WechatSpiderJob {

    private Logger logger = LoggerFactory.getLogger(WechatSpiderJob.class);

    @Autowired
    private PybbsTopicService pybbsTopicService;

    @Autowired
    private SpiderHisService spiderHisService;

    @Autowired
    private SpiderSourceService spiderSourceService;

    /**
     * 1小时执行一次
     */
//    @Scheduled(cron = "0 0 0/1 * * ?")
    public void task() {
        List<SpiderSource> spiderSources = spiderSourceService.queryList(new HashMap<String, Object>(16));
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
                    pybbsTopic.setLabelId("2,");
                    pybbsTopic.setModifyTime(new Date());
                    pybbsTopic.setLastReplyTime(null);
                    pybbsTopic.setTab("精华");
                    pybbsTopic.setReplyCount(0);
                    pybbsTopic.setTop(0);
                    pybbsTopic.setTopicLock(0);
                    pybbsTopic.setUpIds("");
                    pybbsTopic.setUserId(1);
                    pybbsTopic.setView(0);
                    pybbsTopic.setPass(1);
                    pybbsTopic.setCoverImage(article.getImglink().replace("background-image: url(", "").replace(")",""));
                    pybbsTopicService.saveWithHistory(pybbsTopic);
                }
            }
        }
        Browser.driver.quit();
        Browser.driver = null;
    }
}
