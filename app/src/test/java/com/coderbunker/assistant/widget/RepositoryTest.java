package com.coderbunker.assistant.widget;

import com.coderbunker.assistant.BaseTest;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class RepositoryTest extends BaseTest {

    private Repository repository;

    @Before
    public void setUp() {
        repository = new Repository(RuntimeEnvironment.application);
    }

    @Test
    public void saveData_preferenceReturnsSavedData() {
        repository.saveData(getData());

        ArrayList<String> data = repository.getData();
        assertEquals(3, data.size());
    }

    @Test
    public void getData_preferenceReturnsSavedData() {
        repository.saveData(getData());

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