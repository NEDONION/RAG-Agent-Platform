package org.lucas.interfaces.dto.rag.request;

import org.lucas.interfaces.dto.Page;

public class QueryUserRagVersionRequest extends Page {

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}