package com.dudencovgmail.splashes.presentation.viewmodels;

import android.support.v7.widget.RecyclerView;

import com.dudencovgmail.splashes.presentation.notview.viewmodels.MainViewModel;
import com.dudencovgmail.splashes.repository.remote.models.response.ModelResponse;
import com.dudencovgmail.splashes.repository.remote.NetConnection;
import com.dudencovgmail.splashes.repository.remote.Repository;
import com.dudencovgmail.splashes.presentation.interfaces.ViewIF;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;

public class PresenterTest {
    private MainViewModel mPresenter;
    private RecyclerView mRecyclerViewMock;
    private ViewIF mViewIFMock;
    private Repository mRepositoryMock;
    private int mCurrentPage;
    private NetConnection mNetConnectionMock;
    //private NetConnection.LoadInfoCallback mCallbackMock;

    @Before
    public void setUp() throws Exception {
        mRepositoryMock = mock(Repository.class);
        mPresenter = new MainViewModel(mRepositoryMock);
        mRecyclerViewMock = mock(RecyclerView.class);
        mViewIFMock = mock(ViewIF.class);
        mNetConnectionMock = mock(NetConnection.class);
       // mCallbackMock = mock(NetConnection.LoadInfoCallback.class);
        mCurrentPage = 1;
    }

    @Test
    public void loadInfoTest() {
        mPresenter.attachView(mViewIFMock);
        mPresenter.loadInfo(mRecyclerViewMock);

        verify(mRepositoryMock).writeToCash(new ArrayList<ModelResponse>());
        validateMockitoUsage();
    }
}