package ru.geekbrains.lesson9;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.jetbrains.annotations.NotNull;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import androidx.test.ext.junit.runners.*;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.geekbrains.lesson9.data.Endpoints;
import ru.geekbrains.lesson9.data.models.GithubUser;
import ru.geekbrains.lesson9.data.models.RepsModel;

@RunWith(AndroidJUnit4.class)
public class NetApiTest {
    private static MockWebServer mockWebServer;
    private static Endpoints endPointsMocked;

    @BeforeClass
    public static void setupServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(8080);
        mockWebServer.setDispatcher(new RequestDispatcher());

        endPointsMocked = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build()
                .create(Endpoints.class);
    }

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    @Test
    public void testSuccess() {
        RepsModel model = new RepsModel("Alex", "Petrov", new GithubUser("petrovAv", "ssssss"));

        endPointsMocked.getRepos().test()
                .assertNoErrors()
                .assertComplete()
                .assertValue(new ArrayList<RepsModel>(Arrays.asList(model)))
                .dispose();
    }

    @AfterClass
    public static void shutdownServer() throws IOException {
        mockWebServer.shutdown();
    }

    private static class RequestDispatcher extends Dispatcher {
        @NotNull
        @Override
        public MockResponse dispatch(RecordedRequest request) {
            if (request.getPath().equals("/repositories")) {
                return new MockResponse().setResponseCode(200)
                        .setBody("[{\"full_name\":\"Petrov\",\"name\":\"Alex\",\"owner\":{\"avatar_url\":\"ssssss\",\"login\":\"petrovAv\"}}]");
            }

            return new MockResponse().setResponseCode(404);
        }
    }

}
