package com.glowroad.andriod.viewModel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.glowroad.andriod.model.PhotosResponse;
import com.glowroad.andriod.network.ApiEndPoint;
import com.glowroad.andriod.network.Resource;
import com.glowroad.andriod.network.RxSingleSchedulers;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class PhotosViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    ApiEndPoint apiEndPoint;

    private PhotosViewModel viewModel;

    @Mock
    private Observer<Resource> observer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new PhotosViewModel(apiEndPoint, RxSingleSchedulers.DEFAULT);
        viewModel.getMutableLiveData().observeForever(observer);
    }

    @Test
    public void testNull() {
        when(apiEndPoint.getPhotosList(1)).thenReturn(null);
        assertNotNull(viewModel.getMutableLiveData());
        assertTrue(viewModel.getMutableLiveData().hasObservers());
    }

    @Test
    public void testApiFetchDataSuccess() {
        // Mock API response
        PhotosResponse response = new PhotosResponse();
        response.setStatus("ok");
        when(apiEndPoint.getPhotosList(1)).thenReturn(Single.just(response));
        viewModel.fetchPhotos(1);
        verify(observer).onChanged(isA(Resource.class));
    }

    @Test
    public void testApiFetchDataError() {
        when(apiEndPoint.getPhotosList(1)).thenReturn(Single.error(new Throwable("Api error")));
        viewModel.fetchPhotos(1);
        verify(observer).onChanged(isA(Resource.class));
    }

    @After
    public void tearDown() throws Exception {
        apiEndPoint = null;
        viewModel = null;
    }


}
