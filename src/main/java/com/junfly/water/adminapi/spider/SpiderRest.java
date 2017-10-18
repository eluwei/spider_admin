package com.junfly.water.adminapi.spider;

import com.junfly.water.entity.spider.Article;
import com.junfly.water.entity.spider.PybbsTopic;
import com.junfly.water.entity.spider.SpiderHis;
import com.junfly.water.entity.spider.SpiderSource;
import com.junfly.water.service.spider.PybbsTopicService;
import com.junfly.water.service.spider.SpiderHisService;
import com.junfly.water.service.spider.SpiderSourceService;
import com.junfly.water.spider.ArticlesByAppSpider;
import com.junfly.water.utils.R;
import com.junfly.water.utils.annotation.IgnoreAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/10/17 23:42
 */
@RestController
@RequestMapping("/admin_api/spider")
@Api("爬虫")
public class SpiderRest {

    @Autowired
    private PybbsTopicService pybbsTopicService;

    @Autowired
    private SpiderHisService spiderHisService;

    @Autowired
    private SpiderSourceService spiderSourceService;

    /**
     * 爬取微信公众号
     */
    @GetMapping("/spiderByNick")
    @ApiOperation("爬取微信公众号")
    @IgnoreAuth
    public R spiderByNick() {
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
        return R.ok("调用成功");
    }
}
