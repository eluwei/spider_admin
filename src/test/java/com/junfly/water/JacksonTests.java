package com.junfly.water;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junfly.water.utils.DateUtil;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by bj on 2016/12/7.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class JacksonTests {

    @Test
    public void testDate(){
        String msg = "{\"rWaterWorksId\":"+"\"HN_000001\""+",\"rDateTime\":"+"\"2016/12/06 04:18:11\""
                +",\"rState\":"+"\"1000100010110110011001111100000011000000110000000000001111111111\""+"}";
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
    }

    @Test
    public void testChange(){
        int  systemStateWord = 553713664;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            if ((systemStateWord & 0x00000001) == 0x00000001) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            systemStateWord >>= 1;
        }
        System.out.println(sb.toString());
    }
}
