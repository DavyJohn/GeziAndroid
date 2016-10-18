package com.geziwulian.geziandroid;

import android.test.InstrumentationTestCase;

/**
 * Created by 志浩 on 2016/10/15.
 */

public class TestAndroidClass extends InstrumentationTestCase {

    private static final String TAG = TestAndroidClass.class.getSimpleName();

    public void test() throws Exception{
        assertEquals(3,2);
    }
}
