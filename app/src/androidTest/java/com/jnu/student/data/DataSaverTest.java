package com.jnu.student.data;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.jnu.student.R;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class DataSaverTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() {
        DataSaver dataSaver=new DataSaver();
    }

    @Test
    public void load() {
        DataSaver dataSaver=new DataSaver();

        ArrayList<Book> books = dataSaver.Load(InstrumentationRegistry.getInstrumentation().getTargetContext());
        Assert.assertEquals(0,books.size());
    }

    @Test
    public void picture(){
        DataSaver dataSaver=new DataSaver();

        ArrayList<Book> books = dataSaver.Load(InstrumentationRegistry.getInstrumentation().getTargetContext());
        for (Book book: books){
            Assert.assertEquals(book.getCoverResourceId(), R.drawable.book_no_name);
            // 这是验证图片id一致，图片一致写不出来
        }
    }
}