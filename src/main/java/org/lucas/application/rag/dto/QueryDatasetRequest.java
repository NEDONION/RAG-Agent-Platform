package org.lucas.application.rag.dto;

import org.lucas.interfaces.dto.Page;

/** 数据集查询请求
 * @author shilong.zang
 * @date 2024-12-09 */
public class QueryDatasetRequest extends Page {

    /** 搜索关键词 */
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}