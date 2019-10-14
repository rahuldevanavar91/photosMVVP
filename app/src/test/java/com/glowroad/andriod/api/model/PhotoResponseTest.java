package com.glowroad.andriod.api.model;

import com.glowroad.andriod.model.Photos;
import com.glowroad.andriod.model.PhotosResponse;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PhotoResponseTest {
    private static final String stat = "ok";
    private static final Photos photos = new Photos();

    @Mock
    PhotosResponse photosResponse;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(photosResponse.getStatus()).thenReturn(stat);
        Mockito.when(photosResponse.getPhotos()).thenReturn(photos);
    }

    @Test
    public void testStat() {
        Mockito.when(photosResponse.getStatus()).thenReturn(stat);
        Assert.assertEquals("ok", photosResponse.getStatus());
    }

    @Test
   public void testPhotos() {
        Mockito.when(photosResponse.getPhotos()).thenReturn(photos);
        Assert.assertEquals(photos, photosResponse.getPhotos());
    }

    @Test
    public void testStatIncorrect() {
        Mockito.when(photosResponse.getStatus()).thenReturn(stat);
        Assert.assertEquals("fail", photosResponse.getStatus());
    }

    @Test
    public  void testPhotosIncorrect() {
        Mockito.when(photosResponse.getPhotos()).thenReturn(photos);
        Assert.assertEquals("", photosResponse.getPhotos());

    }

    @After
    public void tearDown() {
        photosResponse = null;
    }
}
