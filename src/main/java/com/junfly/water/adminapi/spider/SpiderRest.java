package com.junfly.water.adminapi.spider;

import com.junfly.water.entity.spider.*;
import com.junfly.water.service.spider.PybbsTopicService;
import com.junfly.water.service.spider.SpiderHisService;
import com.junfly.water.service.spider.SpiderSourceService;
import com.junfly.water.spider.ArticlesByAppSpider;
import com.junfly.water.spider.helper.Browser;
import com.junfly.water.utils.R;
import com.junfly.water.utils.StringUtils;
import com.junfly.water.utils.annotation.IgnoreAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
    private SpiderSourceService spiderSourceService;

    @Autowired
    private SpiderHisService spiderHisService;

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
        List<SpiderSource> spiderSources = spiderSourceService.queryList(new HashMap<String, Object>(16));
//        for (SpiderSource spiderSource : spiderSources) {
        SpiderSource spiderSource = spiderSources.get(6);
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
                    pybbsTopic.setPass(1);
                    pybbsTopic.setCoverImage(article.getImglink().replace("background-image: url(", "").replace(")",""));
                    pybbsTopicService.saveWithHistory(pybbsTopic, "1");
                }
            }
//        }
        Browser.driver.quit();
        Browser.driver = null;
        return R.ok("调用成功");
    }

    @GetMapping("/imageContentProcess")
    @ApiOperation("内容图片处理")
    @IgnoreAuth
    public R imageContentProcess() {
        Map<String, Object> map = new HashMap<>();
        map.put("imageProcess", "1");
        List<SpiderHis> spiderHisList = spiderHisService.queryList(map);
        if (spiderHisList != null && !spiderHisList.isEmpty()) {
            SpiderHis spiderHis = spiderHisList.get(0);
            SpiderHis updateHis = new SpiderHis();
            updateHis.setId(spiderHis.getId());
            updateHis.setImageProcess("2");
            updateHis.setHisTitle(spiderHis.getHisTitle());
            spiderHisService.update(spiderHis);
            PybbsTopic pybbsTopic = pybbsTopicService.queryObject(spiderHis.getId());
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
                        String fileName = System.currentTimeMillis() + i + "." + imageType;
                        writeImageToDisk(btImg, filePath + pybbsTopic.getId() + "/" + fileName);
                        imgEle.attr("src", staticPath + pybbsTopic.getId() + "/" + fileName);
                        imgEle.attr("data-src", "");
                    }
                }
            }
            updateHis.setId(spiderHis.getId());
            updateHis.setImageProcess("3");
            updateHis.setHisTitle(spiderHis.getHisTitle());
            spiderHisService.update(updateHis);
            System.out.println(document.toString());

            pybbsTopic.setContent(document.toString());
            pybbsTopicService.update(pybbsTopic);
        }
        return R.ok("调用成功");
    }

    /**
     * 判断是否已经爬取过
     */
    @PostMapping("/judgeIsSpider")
    @ApiOperation("判断是否已经爬取过")
    @IgnoreAuth
    public R judgeIsSpider(@ApiParam("标题") @RequestBody PybbsTopic pybbsTopic) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("hisTitle", pybbsTopic.getTitle());
        List<SpiderHis> spiderHisList  = spiderHisService.queryList(map);
        if (spiderHisList != null && !spiderHisList.isEmpty()) {
            return R.ok().put("is_spider", true);
        }
        return R.ok().put("is_spider", false);
    }

    /**
     * 新增文章
     */
    @PostMapping("/addTopic")
    @ApiOperation("新增文章")
    @IgnoreAuth
    public R addTopic(@ApiParam("topic") @RequestBody PybbsTopic pybbsTopic) {
        pybbsTopic.setInTime(new Date());
        pybbsTopic.setGood(0);
        pybbsTopic.setLabelId("2,");
        pybbsTopic.setModifyTime(new Date());
        pybbsTopic.setLastReplyTime(null);
        if (StringUtils.isEmpty(pybbsTopic.getTab())) {
            pybbsTopic.setTab("精华");
        }
        pybbsTopic.setReplyCount(0);
        pybbsTopic.setTop(0);
        pybbsTopic.setTopicLock(0);
        pybbsTopic.setUpIds("");
        pybbsTopic.setUserId(1);
        pybbsTopic.setView(0);
        pybbsTopic.setPass(0);
        pybbsTopicService.saveWithHistory(pybbsTopic, "1");
        return R.ok().put("save_result", true);
    }

    /**
     * 信息来源
     */
    @PostMapping("/spiderSourceList")
    @ApiOperation("信息来源")
    @IgnoreAuth
    public R sourceList() {
        List<SpiderSource> spiderSources = spiderSourceService.queryList(new HashMap<String, Object>());
        return R.ok().put("source_list", spiderSources);
    }


}
