package ru.geekbrains.lesson9;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import androidx.test.annotation.*;
import androidx.test.core.app.*;
import androidx.test.ext.junit.runners.*;
import androidx.test.rule.*;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    MainActivity activity;

    @Before
    public void init() {
        activity = rule.getActivity();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = ApplicationProvider.getApplicationContext();

        assertEquals("ru.geekbrains.lesson9", appContext.getPackageName());
    }

    @Test
    @UiThreadTest
    public void ensureProgressViewIsShowing() {
        View viewById = activity.findViewById(R.id.loadingView);
        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(ProgressBar.class));
        activity.showLoading();
        assertEquals(viewById.getVisibility(), View.VISIBLE);
    }

    @Test
    @UiThreadTest
    public void ensureProgressViewIsHide() {
        View viewById = activity.findViewById(R.id.loadingView);
        activity.hideLoading();
        assertEquals(viewById.getVisibility(), View.GONE);
    }

    @Test
    @UiThreadTest
    public void ensureEmptyViewExist() {
        View emptyView = activity.findViewById(R.id.emptyView);
        assertThat(emptyView, notNullValue());
    }

    @Test
    @UiThreadTest
    public void ensureContentViewIsShow() {
        View content = activity.findViewById(R.id.contentView);
        View empty = activity.findViewById(R.id.emptyView);
        activity.showRepoList(new ArrayList<>());
        assertEquals(content.getVisibility(), View.VISIBLE);
        assertEquals(empty.getVisibility(), View.GONE);
    }

    @Test
    @UiThreadTest
    public void ensureEmptyContentViewIsShow() {
        View content = activity.findViewById(R.id.contentView);
        View empty = activity.findViewById(R.id.emptyView);
        activity.showEmptyState();
        assertEquals(content.getVisibility(), View.GONE);
        assertEquals(empty.getVisibility(), View.VISIBLE);
    }
}
