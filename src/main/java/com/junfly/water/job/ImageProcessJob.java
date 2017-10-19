package com.junfly.water.job;

import com.junfly.water.entity.spider.*;
import com.junfly.water.service.spider.PybbsTopicService;
import com.junfly.water.service.spider.SpiderHisService;
import com.junfly.water.service.spider.SpiderProcessService;
import com.junfly.water.service.spider.SpiderSourceService;
import com.junfly.water.spider.ArticlesByAppSpider;
import com.junfly.water.utils.R;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.junfly.water.utils.ImageUtil.getImageFromNetByUrl;
import static com.junfly.water.utils.ImageUtil.writeImageToDisk;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/10/18 21:43
 */
@Component
@EnableScheduling
public class ImageProcessJob {

    private Logger logger = LoggerFactory.getLogger(ImageProcessJob.class);

    @Autowired
    private PybbsTopicService pybbsTopicService;

    @Autowired
    private SpiderProcessService spiderProcessService;

    @Value("${image.filePath}")
    private String filePath;

    @Value("${image.staticPath}")
    private String staticPath;

    @Scheduled(cron = "0 25 11 * * ?") // 每天11点25分执行一次
    public void task() {
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
    }
}
