package com.junfly.water.spider;

import com.junfly.water.SampleActiveMQApplication;
import com.junfly.water.entity.spider.Article;
import com.junfly.water.entity.spider.PybbsTopic;
import com.junfly.water.entity.spider.SpiderHis;
import com.junfly.water.service.spider.PybbsTopicService;
import com.junfly.water.service.spider.SpiderHisService;
import com.junfly.water.spider.helper.Browser;
import com.junfly.water.spider.helper.Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: pq
 * @Description:
 * @Date: 2017/10/31 20:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleActiveMQApplication.class)
public class TencentChildSpiderSpec {

    @Autowired
    private TencentChildSpider spider;

    @Autowired
    private SpiderHisService spiderHisService;

    @Autowired
    private PybbsTopicService pybbsTopicService;

    @Before
    public void before() {
        spider.openBrowser();
    }

    @Test
    public void getAllArticleList() {
        Browser.driver.get("http://baby.qq.com/dc_article2016/tagsList.htm?tags=%E4%BB%8A%E6%97%A5%E7%83%AD%E7%82%B9");
        Util.sleepRandom(2000, 3000);
        List<Article> articles = new ArrayList<>();
        List<?> divEles = Browser.driver.findElements(By.cssSelector(".styleOne "));
        for (Object divEle : divEles) {
            Util.sleepRandom(1000, 2000);
            WebElement divElement = (WebElement) divEle;
            Article article = new Article();
            WebElement aElement = divElement.findElement(By.cssSelector(".listTitle a"));
            String title = aElement.getText();
            String aUrl = aElement.getAttribute("href");
            article.setTitle(title);
            article.setUrl(aUrl);

            WebElement imgElement = divElement.findElement(By.cssSelector(".cf a img"));
            String imagePath = imgElement.getAttribute("src");
            article.setImglink(imagePath);
            articles.add(article);
        }

        for (Article article : articles) {
            List<SpiderHis> spiderHis = spiderHisService.queryByTitle(article.getTitle());
            if (spiderHis == null || spiderHis.isEmpty()) {
                article = spider.processArticleDetail(article);
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
                pybbsTopic.setCoverImage(article.getImglink().replace("background-image: url(", "").replace(")", ""));
                try {
                    pybbsTopicService.saveWithHistory(pybbsTopic,"2");
                } catch (Exception e) {
                }
            }
        }
    }

    @Test
    public void returnAllArticleWhenQuery() {

    }
}
