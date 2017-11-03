package com.junfly.water.spider;

import com.junfly.water.entity.spider.Article;
import com.junfly.water.spider.helper.Browser;
import com.junfly.water.spider.helper.Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

/**
 * @author: pq
 * @Description:
 * @Date: 2017/10/31 20:23
 */
@Component
public class TencentChildSpider {

    public void openBrowser() {
        Browser.init();
    }

    public Article processArticleDetail(Article article) {
        Browser.driver.get(article.getUrl());
        Util.sleepRandom(100, 4000);
        String htmlStr = Browser.driver.getPageSource();
        Document doc = Jsoup.parse(htmlStr);
        Element content = doc.select(".Cnt-Main-Article-QQ").first();
        String contentHtml = "空";
        if (content != null) {
            contentHtml = content.html();
        }
        System.out.println("最后 " + contentHtml);
        article.setContent(contentHtml);
        return article;
    }
}
