package com.arnoldgalovics.bookshelf.internal;

import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.QueryExecutionListener;

import java.util.List;

public class CustomQueryExecutionListener implements QueryExecutionListener {
    @Override
    public void beforeQuery(ExecutionInfo executionInfo, List<QueryInfo> list) {

    }

    @Override
    public void afterQuery(ExecutionInfo executionInfo, List<QueryInfo> list) {
        QueryStatementHolder.add(list);
    }
}