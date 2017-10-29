package com.junfly.water.job;

import com.junfly.water.entity.spider.PybbsTopic;
import com.junfly.water.entity.spider.SpiderHis;
import com.junfly.water.service.spider.PybbsTopicService;
import com.junfly.water.service.spider.SpiderHisService;
import org.apache.commons.lang.StringUtils;
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
    private SpiderHisService spiderHisService;

    @Value("${image.filePath}")
    private String filePath;

    @Value("${image.staticPath}")
    private String staticPath;

    /**
     * 30分钟执行一次
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void task() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("imageProcess", "1");
        List<SpiderHis> spiderHisList = spiderHisService.queryList(map);
        if (spiderHisList != null && !spiderHisList.isEmpty()) {
            for (SpiderHis spiderHis : spiderHisList) {
                SpiderHis updateHis = new SpiderHis();
                updateHis.setId(spiderHis.getId());
                updateHis.setImageProcess("2");
                updateHis.setHisTitle(spiderHis.getHisTitle());
                spiderHisService.update(updateHis);
                PybbsTopic pybbsTopic = pybbsTopicService.queryObject(spiderHis.getId());
                logger.info("此次处理的ID是：" + pybbsTopic.getId());
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

                String converImage = pybbsTopic.getCoverImage();
                if (StringUtils.isNotEmpty(converImage)) {
                    converImage = converImage.replace("background-image: url(", "");
                    String[] sourcePathArray = converImage.split("\\?");
                    String imageType = sourcePathArray[1].replace("wx_fmt=", "").replace(";", "");
                    byte[] btImg = getImageFromNetByUrl(converImage);
                    if (null != btImg && btImg.length > 0) {
                        String fileName = System.currentTimeMillis() + "_cover" + "." + imageType;
                        writeImageToDisk(btImg, filePath + pybbsTopic.getId() + "/" + fileName);
                        converImage = staticPath + pybbsTopic.getId() + "/" + fileName;
                    }
                    pybbsTopic.setCoverImage(converImage);
                }

                updateHis.setId(spiderHis.getId());
                updateHis.setImageProcess("3");
                updateHis.setHisTitle(spiderHis.getHisTitle());
                spiderHisService.update(updateHis);

                pybbsTopic.setContent(document.toString());
                pybbsTopicService.update(pybbsTopic);
            }
        }
    }
}
