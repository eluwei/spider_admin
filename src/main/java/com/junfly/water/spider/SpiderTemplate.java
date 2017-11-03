package com.junfly.water.spider;

import com.junfly.water.entity.spider.Article;

import java.util.List;

/**
 * @author: pq
 * @Description:
 * @Date: 2017/10/31 17:41
 */
public abstract class SpiderTemplate {

    protected List<Article> spiderEngine() {
        // 浏览器初始化
        openBrowser();
        // 获取文章列表
        List<Article> articleList  = getArticleList();
        // 获取文章详情
        getArticleDetail(articleList);
        // 浏览器关闭
        closeBrowser();
        return articleList;
    }

    protected abstract void closeBrowser();

    protected abstract void getArticleDetail(List<Article> articleList);

    protected abstract List<Article> getArticleList();

    protected abstract void openBrowser();
}
