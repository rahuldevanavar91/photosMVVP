package com.glowroad.andriod.api.model;

import com.glowroad.andriod.model.PhotoItem;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PhotoItemTest {
    private final String title = "test title";
    private final String url_q = "www.test.com/image";
    private final int height_q = 100;
    private final int width_q = 100;
    @Mock
    PhotoItem photoItem;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(photoItem.getImageUrl()).thenReturn(url_q);
        Mockito.when(photoItem.getTitle()).thenReturn(title);
        Mockito.when(photoItem.getImageHeight()).thenReturn(height_q);
        Mockito.when(photoItem.getImageWidth()).thenReturn(width_q);

    }

    @Test
    public void testImageUrl() {
        Mockito.when(photoItem.getImageUrl()).thenReturn(url_q);
        Assert.assertEquals("www.test.com/image", photoItem.getImageUrl());
    }
    @Test
    public void testTitle() {
        Mockito.when(photoItem.getImageUrl()).thenReturn(title);
        Assert.assertEquals("test title", photoItem.getTitle());
    }

    @Test
    public void testHeight() {
        Mockito.when(photoItem.getImageHeight()).thenReturn(height_q);
        Assert.assertEquals(100, photoItem.getImageHeight());
    }

    @Test
    public void testWidth() {
        Mockito.when(photoItem.getImageWidth()).thenReturn(width_q);
        Assert.assertEquals(100, photoItem.getImageWidth());
    }
    @Test
    public void testTitleIncorrect() {
        Mockito.when(photoItem.getImageUrl()).thenReturn(title);
        Assert.assertEquals("test fial title", photoItem.getTitle());
    }

    @Test
    public void testHeightIncorrect() {
        Mockito.when(photoItem.getImageHeight()).thenReturn(height_q);
        Assert.assertEquals(0, photoItem.getImageHeight());
    }

    @Test
    public void testWidthIncorrect() {
        Mockito.when(photoItem.getImageWidth()).thenReturn(width_q);
        Assert.assertEquals(0, photoItem.getImageWidth());
    }

    @Test
    public void testImageUrlIncorrect() {
        Mockito.when(photoItem.getImageUrl()).thenReturn(url_q);
        Assert.assertEquals("test www.test.com/image", photoItem.getImageUrl());
    }



    @After
    public void tearDown() {
        photoItem = null;
    }

}
