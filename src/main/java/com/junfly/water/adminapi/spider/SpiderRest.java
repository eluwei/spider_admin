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
        String channel = "1";
        if (StringUtils.isNotEmpty(pybbsTopic.getChannel())) {
            channel = pybbsTopic.getChannel();
        }
        pybbsTopicService.saveWithHistory(pybbsTopic, channel);
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
