package com.coderbunker.assistant;

import android.os.Build;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        application = App.class,
        sdk = Build.VERSION_CODES.LOLLIPOP)
public class BaseTest {
}
