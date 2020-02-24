package com.coderbunker.assistant;

import com.coderbunker.assistant.widget.Repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

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

    @Test
    public void testPlatformDependentCode() {
        int widgetId = 10;
        repository.saveData(widgetId, getData());

        ArrayList<String> data = repository.getData();
        assertEquals(3, data.size());
    }

    private ArrayList<String> getData() {
        ArrayList<String> result = new ArrayList<>();
        result.add("one");
        result.add("two");
        result.add("three");
        return result;
    }
}
