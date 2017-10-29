package com.junfly.water.utils;

import com.junfly.water.SampleActiveMQApplication;
import com.junfly.water.entity.spider.PybbsTopic;
import com.junfly.water.entity.spider.SpiderHis;
import com.junfly.water.service.spider.PybbsTopicService;
import com.junfly.water.service.spider.SpiderHisService;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.junfly.water.utils.ImageUtil.getImageFromNetByUrl;
import static com.junfly.water.utils.ImageUtil.writeImageToDisk;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/10/18 22:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleActiveMQApplication.class)
public class ImageUtilSpec {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PybbsTopicService pybbsTopicService;

    @Autowired
    private SpiderHisService spiderHisService;

    @Value("${image.filePath}")
    private String filePath;

    @Value("${image.staticPath}")
    private String staticPath;

    @Test
    public void getImageByHttpUrl() {
        String url = "http://mmbiz.qpic.cn/mmbiz_gif/z7kZjMoQ3yGWNJeZSz6gd21t9ANmS74YgKyuVc9F4Y3ASQDhIjROz3CXavVgsFZZ0icpXC2ayEEZIHFo2PZXHyA/0?wx_fmt=gif";
        byte[] btImg = getImageFromNetByUrl(url);
        if (null != btImg && btImg.length > 0) {
            System.out.println("读取到：" + btImg.length + " 字节");
            String fileName = "百度.gif";
            writeImageToDisk(btImg, fileName);
        } else {
            System.out.println("没有从该连接获得内容");
        }
    }

    @Test
    public void replaceImagePath() {
        Map<String, Object> map = new HashMap<>();
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

                String converImage = pybbsTopic.getCoverImage();
                if (StringUtils.isNotEmpty(converImage)) {
                    converImage = converImage.replace("background-image: url(", "");
                    String[] sourcePathArray = converImage.split("\\?");
                    String imageType = sourcePathArray[1].replace("wx_fmt=", "").replace(";","");
                    byte[] btImg = getImageFromNetByUrl(converImage);
                    if (null != btImg && btImg.length > 0) {
                        String fileName = new Date().getTime() + "_cover" + "." + imageType;
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

//                Map<String, String> param = new HashMap<>();
//                param.put("coverImage", converImage);
//                param.put("id", String.valueOf(pybbsTopic.getId()));
//                param.put("content", pybbsTopic.getContent());
//                param.put("token", "d20b9a5c-8693-41a6-8943-ddb2cb78eebd");
//                HttpUtil.sendPost("http://localhost:8083/api/topic/update", param);
            }
        }
    }
}
