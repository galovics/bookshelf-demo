package com.arnoldgalovics.bookshelf;

import com.arnoldgalovics.bookshelf.internal.QueryStatementHolder;
import net.ttddyy.dsproxy.QueryCountHolder;
import net.ttddyy.dsproxy.QueryInfo;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public abstract class QueryAssertions {
    public static void assertSelectCount(final int expectedSelectCount) {
        int actualSelectCount = QueryCountHolder.getGrandTotal().getSelect();
        if (actualSelectCount != expectedSelectCount) {
            throw new AssertionError(format("Select query count differs from the expected. Actual count was %d but expected %d queries",
                    actualSelectCount, expectedSelectCount));
        }
    }

    public static void assertUpdateCount(final int expectedUpdateCount) {
        int actualUpdateCount = QueryCountHolder.getGrandTotal().getUpdate();
        if (actualUpdateCount != expectedUpdateCount) {
            throw new AssertionError(format("Update query count differs from the expected. Actual count was %d but expected %d queries",
                    actualUpdateCount, expectedUpdateCount));
        }
    }

    public static void assertSelectionFields(String... fields) {
        assertSelectCount(1);
        String query = QueryStatementHolder.get().stream().map(QueryInfo::getQuery).map(String::toUpperCase).filter(upperCaseQuery -> upperCaseQuery.contains("SELECT")).findFirst().get();
        String selection = query.substring(7, query.indexOf("FROM"));
        List<String> attrs = Arrays.asList(selection.split(",")).stream().map(String::toUpperCase).collect(toList());
        List<String> attrsToCheck = Arrays.asList(fields).stream().map(String::toUpperCase).collect(toList());
        attrsToCheck.forEach(toCheck -> {
            attrs.removeIf(attr -> attr.contains(toCheck));
        });
        if (!attrs.isEmpty()) {
            List<String> remainingCols = attrs.stream().map(attr -> attr.substring(attr.indexOf(".") + 1, attr.indexOf(" AS"))).collect(toList());
            throw new AssertionError(format("Oops, you are selecting more data than needed. Remaining columns: %s", remainingCols));
        }
    }
}
