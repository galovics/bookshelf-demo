package com.arnoldgalovics.bookshelf;

import static java.lang.String.format;

import junit.framework.AssertionFailedError;
import net.ttddyy.dsproxy.QueryCountHolder;

public abstract class QueryAssertions {
    public static void assertSelectCount(final int expectedSelectCount) {
        int actualSelectCount = QueryCountHolder.getGrandTotal().getSelect();
        if (actualSelectCount != expectedSelectCount) {
            throw new AssertionFailedError(format("Select query count differs from the expected. Actual count was %d but expected %d queries",
                    actualSelectCount, expectedSelectCount));
        }
    }

    public static void assertUpdateCount(final int expectedUpdateCount) {
        int actualUpdateCount = QueryCountHolder.getGrandTotal().getUpdate();
        if (actualUpdateCount != expectedUpdateCount) {
            throw new AssertionFailedError(format("Update query count differs from the expected. Actual count was %d but expected %d queries",
                    actualUpdateCount, expectedUpdateCount));
        }
    }
}
