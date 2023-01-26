package com.zerobase.zerolms.product.controller;

import com.zerobase.zerolms.util.PageUtil;

public class BaseController {

    public String getPaperHtml(long totalCount, long pageSize, long pageIndex, String queryString) {
        PageUtil pageUtil = new PageUtil(totalCount, pageSize, pageIndex, queryString);
        return pageUtil.pager();
    }
}
