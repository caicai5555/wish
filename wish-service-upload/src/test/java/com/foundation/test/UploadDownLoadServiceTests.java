package com.foundation.test;

import com.foundation.dao.entity.file.SysFile;
import com.foundation.service.file.service.ISysFileService;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-context.xml",
        "classpath:spring-db.xml"
})
public class UploadDownLoadServiceTests {

    @Autowired
    ISysFileService sysFileService;

    @Test
    public void save() throws Exception {
        SysFile sysFile=new SysFile();
        sysFile.setFileName("test.test");
        String filePath = sysFileService.saveFile(sysFile);
        System.out.println(filePath);
    }


    @Test
    public void queryById() throws Exception {
        SysFile file = sysFileService.queryById("b69c3747-5f02-4ef6-a08c-f524cfd88e37");
        System.out.println(file.getFileName());
    }

    @Test
    public void queryObject() throws Exception {
        Map<String, Object> paramps = Maps.newHashMap();
        //paramps.put("id","b69c3747-5f02-4ef6-a08c-f524cfd88e37");
        paramps.put("fileGroup", "fileGroup");
        SysFile file = sysFileService.queryObject(paramps);
        System.out.println(file.getId());
    }

    @Test
    public void queryList() throws Exception {
        Map<String, Object> paramps = Maps.newHashMap();
        paramps.put("fileGroup", "fileGroup");
        List<SysFile> files = sysFileService.queryList(paramps);
        //assertThat(files).isNotNull();
        System.out.println(files.size());
        for (SysFile file : files) {
            System.out.println(file.getId());
        }
    }
}
