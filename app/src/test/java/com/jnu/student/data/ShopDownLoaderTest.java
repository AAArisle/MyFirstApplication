package com.jnu.student.data;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ShopDownLoaderTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getHttpData() {
        ShopDownLoader shopDownLoader=new ShopDownLoader();
        String fileContent=shopDownLoader.download("http://file.nidama.net/class/mobile_develop/data/bookstore2023.json");
        Assert.assertTrue(fileContent.contains("\"name\": \"暨珠海\","));
        Assert.assertTrue(fileContent.contains("\"memo\": \"珠海二城广场\""));
    }

    @Test
    public void parseJsonData() {
        ShopDownLoader shopDownLoader=new ShopDownLoader();
        String fileContent="{\n" +
                "  \"shops\": [{\n" +
                "    \"name\": \"暨大珠海\",\n" +
                "    \"latitude\": \"22.255925\",\n" +
                "    \"longitude\": \"113.541112\",\n" +
                "    \"memo\": \"暨南大学珠海校区\"\n" +
                "  },\n" +
                "    {\n" +
                "      \"name\": \"沃尔玛(前山店)\",\n" +
                "      \"latitude\": \"22.261365\",\n" +
                "      \"longitude\": \"113.532989\",\n" +
                "      \"memo\": \"沃尔玛(前山店)\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"明珠商业广场\",\n" +
                "      \"latitude\": \"22.251953\",\n" +
                "      \"longitude\": \"113.526421\",\n" +
                "      \"memo\": \"珠海二城广场\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        List<ShopLocation> locations= shopDownLoader.parsonJson(fileContent);

        Assert.assertEquals(3,locations.size());
        Assert.assertEquals("暨大珠海",locations.get(0).getName());
        Assert.assertEquals(22.251953,locations.get(2).getLatitude(),1e-6);
        Assert.assertEquals(113.526421,locations.get(2).getLongitude(),1e-6);
        Assert.assertEquals("珠海二城广场",locations.get(2).getMemo());
    }
}