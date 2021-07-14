package ru.rzn.javaschool.runtime;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToStringTest {
    @ToString
    public class FullName {
        @ToString int intField = 15;
        @ToString String strField = "test @ToString";
    }

    @ToString()
    public class OnlyClass {
        int intField = 15;
        String strField = "test @ToString";
    }


    public class OnlyField {
        int intField = 15;
        @ToString(includeName = true)
        String strField = "test @ToString";
    }

    @ToString(includeName = false)
    public class NotIncludeClassName {
        @ToString int intField = 15;
        @ToString String strField = "test @ToString";
    }

    @ToString
    public class NotIncludeFieldName {
        @ToString int intField = 15;
        @ToString(includeName = false) String strField = "test @ToString";
    }

    @Test
    public void full() {
        String result = ToStrings.toString(new FullName());
        assertEquals("ru.rzn.javaschool.runtime.ToStringTest$FullName[intField = 15, strField = test @ToString]",
                result);
    }

    @Test
    public void onlyClass() {
        String result = ToStrings.toString(new OnlyClass());
        assertEquals("ru.rzn.javaschool.runtime.ToStringTest$OnlyClass[]",
                result);
    }

    @Test
    public void onlyField() {
        String result = ToStrings.toString(new OnlyField());
        assertEquals("[strField = test @ToString]",
                result);
    }

    @Test
    public void notIncludeClassName() {
        String result = ToStrings.toString(new NotIncludeClassName());
        assertEquals("[intField = 15, strField = test @ToString]",
                result);
    }


    @Test
    public void notIncludeFieldName() {
        String result = ToStrings.toString(new NotIncludeFieldName());
        assertEquals("ru.rzn.javaschool.runtime.ToStringTest$NotIncludeFieldName[intField = 15, test @ToString]",
                result);
    }
}
