package com.coderbunker.assistant;


import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

public class FakeTest extends BaseTest {

    @Before
    public void setUp() {
        Robolectric.buildActivity(MainActivity.class).create().start().resume();
    }

    @Test
    public void testThis() {
    }
}
