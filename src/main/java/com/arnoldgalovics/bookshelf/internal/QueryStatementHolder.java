package com.arnoldgalovics.bookshelf.internal;

import net.ttddyy.dsproxy.QueryInfo;

import java.util.ArrayList;
import java.util.List;

public abstract class QueryStatementHolder {
    private static ThreadLocal<List<QueryInfo>> queryInfos = ThreadLocal.withInitial(() -> new ArrayList<>());

    public static void add(List<QueryInfo> infos) {
        queryInfos.get().addAll(infos);
    }

    public static List<QueryInfo> get() {
        return queryInfos.get();
    }

    public static void clear() {
        queryInfos.get().clear();
    }
}
