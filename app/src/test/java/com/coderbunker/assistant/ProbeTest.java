package com.coderbunker.assistant;

import com.coderbunker.assistant.widget.Repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

public class ProbeTest extends BaseTest {

    private Repository repository;

    @Before
    public void setUp() {
        repository = new Repository(RuntimeEnvironment.application);
    }

    @Test
    public void testSampleCase() {
        Assert.assertEquals(2, 1 + 1);
    }
}
