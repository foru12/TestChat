package com.example.testchat.ui.splash.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.testchat.Logger
import com.example.testchat.repository.profile.ProfileRepositoryImpl
import com.example.testchat.repository.profile.api.ProfileApiRepository
import com.example.testchat.repository.profile.room.ProfileRoomRepository
import com.example.testchat.room.model.ProfileRoomData
import com.example.testchat.usecase.splash.CheckNetworkUseCase
import com.example.testchat.usecase.splash.CheckTokenUseCase
import com.example.testchat.usecase.splash.LoadProfileDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SplashViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var checkNetworkUseCase: CheckNetworkUseCase

    @Mock
    private lateinit var checkTokenUseCase: CheckTokenUseCase

    @Mock
    private lateinit var loadProfileDataUseCase: LoadProfileDataUseCase

    @Mock
    private lateinit var profileApiRepository: ProfileApiRepository

    @Mock
    private lateinit var profileRoomRepository: ProfileRoomRepository

    @Mock
    private lateinit var networkErrorObserver: Observer<String>

    @Mock
    private lateinit var tokenValidObserver: Observer<Boolean>

    private lateinit var viewModel: SplashViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)

        viewModel = SplashViewModel(
            checkTokenUseCase,
            loadProfileDataUseCase,
            checkNetworkUseCase
        )
        Logger.isTestMode = true

        viewModel.networkError.observeForever(networkErrorObserver)
        viewModel.tokenValid.observeForever(tokenValidObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `checkNetwork should set networkError when there is no internet`(): Unit = runBlocking {
        `when`(checkNetworkUseCase.execute()).thenReturn(false)

        viewModel.checkNetwork()

        verify(networkErrorObserver).onChanged("No internet connection. Please retry.")
        verify(checkNetworkUseCase).execute()
    }

    @Test
    fun `checkNetwork should not set networkError when there is internet`(): Unit = runBlocking {
        `when`(checkNetworkUseCase.execute()).thenReturn(true)

        viewModel.checkNetwork()

        verify(networkErrorObserver, never()).onChanged(anyString())
        verify(checkNetworkUseCase).execute()
    }

    @Test
    fun `checkToken should call loadProfileData when token is valid`(): Unit = runBlocking {
        `when`(checkTokenUseCase.execute()).thenReturn(true)

        viewModel.checkToken()

        verify(loadProfileDataUseCase).execute()
    }

    @Test
    fun `checkToken should not call loadProfileData and should trigger navigation when token is invalid`(): Unit =
        runBlocking {
            `when`(checkTokenUseCase.execute()).thenReturn(false)

            viewModel.checkToken()

            verify(tokenValidObserver).onChanged(false)
            verify(checkTokenUseCase).execute()
            verify(loadProfileDataUseCase, never()).execute()
        }

    @Test
    fun `loadProfileData should save data to database when token is valid`(): Unit = runBlocking {
        val profileData = mock(ProfileRoomData::class.java)
        `when`(loadProfileDataUseCase.execute()).thenReturn(profileData)

        viewModel.loadProfileData()

        verify(loadProfileDataUseCase).execute()
    }

    @Test
    fun `loadProfileData should fetch data from API and save to Room database`() = runBlocking {
        val profileData = mock(ProfileRoomData::class.java)
        `when`(profileApiRepository.getProfileData()).thenReturn(Result.success(profileData))
        val profileRepository = ProfileRepositoryImpl(profileApiRepository, profileRoomRepository)

        val result = profileRepository.fetchAndSaveProfileData()

        verify(profileApiRepository).getProfileData()
        verify(profileRoomRepository).insertProfileData(profileData)
        assertNotNull(result)
    }

}

