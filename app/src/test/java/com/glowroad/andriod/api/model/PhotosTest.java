package com.glowroad.andriod.api.model;

import com.glowroad.andriod.model.PhotoItem;
import com.glowroad.andriod.model.Photos;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class PhotosTest {
    private static final int page = 1;

    private static final int pages = 100;


    private static final int perPage = 10;

    private static final String total = "1000";

    private static final List<PhotoItem> photo = new ArrayList<>();

    @Mock
    Photos photos;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(photos.getPages()).thenReturn(pages);
        Mockito.when(photos.getPage()).thenReturn(page);
        Mockito.when(photos.getPerPage()).thenReturn(perPage);
        Mockito.when(photos.getTotal()).thenReturn(total);
        Mockito.when(photos.getPhoto()).thenReturn(photo);
    }

    @Test
    public void testPerPage() {
        Mockito.when(photos.getPerPage()).thenReturn(perPage);
        Assert.assertEquals(10, photos.getPerPage());
    }

    @Test
    public void testPages() {
        Mockito.when(photos.getPages()).thenReturn(pages);
        Assert.assertEquals(100, photos.getPages());

    }

    @Test
    public void testTotal() {
        Mockito.when(photos.getTotal()).thenReturn(total);
        Assert.assertEquals("1000", photos.getTotal());
    }

    @Test
    public void testPerPageIncorrect() {
        Mockito.when(photos.getPerPage()).thenReturn(perPage);
        Assert.assertEquals(100, photos.getPage());
    }

    @Test
    public void testPagesIncorrect() {
        Mockito.when(photos.getPages()).thenReturn(pages);
        Assert.assertEquals(0, photos.getPages());

    }

    @Test
    public void testTotalIncorrect() {
        Mockito.when(photos.getTotal()).thenReturn(total);
        Assert.assertEquals("100", photos.getTotal());

    }

    @After
    public void testDown() {
        photos = null;
    }
}
