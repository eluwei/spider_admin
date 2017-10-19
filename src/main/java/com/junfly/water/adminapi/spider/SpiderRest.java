package com.junfly.water.adminapi.spider;

import com.junfly.water.entity.spider.*;
import com.junfly.water.service.spider.PybbsTopicService;
import com.junfly.water.service.spider.SpiderHisService;
import com.junfly.water.service.spider.SpiderProcessService;
import com.junfly.water.service.spider.SpiderSourceService;
import com.junfly.water.spider.ArticlesByAppSpider;
import com.junfly.water.utils.R;
import com.junfly.water.utils.annotation.IgnoreAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.junfly.water.utils.ImageUtil.getImageFromNetByUrl;
import static com.junfly.water.utils.ImageUtil.writeImageToDisk;

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

    @Autowired
    private SpiderProcessService spiderProcessService;

    @Value("${image.filePath}")
    private String filePath;

    @Value("${image.staticPath}")
    private String staticPath;

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

    @GetMapping("/imageContentProcess")
    @ApiOperation("内容图片处理")
    @IgnoreAuth
    public R imageContentProcess() {
        Map<String, Object> map = new HashMap<>();
        map.put("imageProcess", "1");
        List<SpiderProcess> spiderProcesss = spiderProcessService.queryList(map);
        if (spiderProcesss != null && !spiderProcesss.isEmpty()) {
            SpiderProcess spiderProcess = spiderProcesss.get(0);
            SpiderProcess updateProcess = new SpiderProcess();
            updateProcess.setId(spiderProcess.getId());
            updateProcess.setImageProcess("2");
            spiderProcessService.update(updateProcess);
            PybbsTopic pybbsTopic = pybbsTopicService.queryObject(spiderProcess.getId());
            System.out.println("此次处理的ID是：" + pybbsTopic.getId());
            String html = pybbsTopic.getContent();
            Document document = Jsoup.parse(html);
            Elements imgEles = document.select("img");  //选择器的形式
            for (int i = 0; i < imgEles.size(); i++) {
                Element imgEle = imgEles.get(i);
                String sourcePath = imgEle.attr("data-src");
                String[] sourcePathArray = sourcePath.split("\\?");
                if (sourcePathArray != null && sourcePathArray.length > 1) {
                    String imageType = sourcePathArray[1].replace("wx_fmt=", "");
                    byte[] btImg = getImageFromNetByUrl(sourcePath);
                    if (null != btImg && btImg.length > 0) {
                        String fileName = new Date().getTime() + i + "." + imageType;
                        writeImageToDisk(btImg, filePath + pybbsTopic.getId() + "/" + fileName);
                        imgEle.attr("src", staticPath + pybbsTopic.getId() + "/" + fileName);
                        imgEle.attr("data-src", "");
                    }
                }
            }
            updateProcess.setId(spiderProcess.getId());
            updateProcess.setImageProcess("3");
            spiderProcessService.update(updateProcess);
            System.out.println(document.toString());

            pybbsTopic.setContent(document.toString());
            pybbsTopicService.update(pybbsTopic);
        }
        return R.ok("调用成功");
    }
}
