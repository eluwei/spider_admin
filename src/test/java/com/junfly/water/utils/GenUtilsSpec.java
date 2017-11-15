package com.junfly.water.utils;

import com.junfly.water.SampleActiveMQApplication;
import com.junfly.water.service.sys.SysGeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/9/5 17:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleActiveMQApplication.class)
public class GenUtilsSpec {

    @Autowired
    private SysGeneratorService sysGeneratorService;

    @Test
    public void testGeneratorCode() {
        String[] tables = new String[]{"pybbs_user"};
        String packageName = "spider";
        byte[] out = sysGeneratorService.generatorCode(tables, packageName);
        FileUtils.getFile(out, "E:\\outTemplate", "code.zip");
    }
}
