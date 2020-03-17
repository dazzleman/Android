package ru.geekbrains.lesson9;

import com.facebook.stetho.okhttp3.*;

import org.jetbrains.annotations.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.junit.*;

import java.io.*;
import java.util.*;

import io.reactivex.functions.*;
import okhttp3.*;
import okhttp3.logging.*;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.*;
import retrofit2.*;
import retrofit2.adapter.rxjava2.*;
import retrofit2.converter.gson.*;
import ru.geekbrains.lesson9.data.*;
import ru.geekbrains.lesson9.data.models.*;

public class MockitoWebServerTest {
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
        return new OkHttpClient.Builder()
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
