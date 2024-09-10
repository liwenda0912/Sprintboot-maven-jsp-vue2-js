package com.example.webproject.dto;

import lombok.Data;

//
//public class RowBounds extends org.apache.ibatis.session.RowBounds {
//        /* 默认offset是0**/
//        public static final int NO_ROW_OFFSET = 0;
//        /* 默认Limit是int的最大值，因此它使用的是逻辑分页**/
//        public static final int NO_ROW_LIMIT = Integer.MAX_VALUE;
//        public static final RowBounds DEFAULT = new RowBounds();
//
//        private int offset;
//        private int limit;
//
//        public RowBounds() {
//            this.offset = NO_ROW_OFFSET;
//            this.limit = NO_ROW_LIMIT;
//        }
//
//        public RowBounds(int offset, int limit) {
//            this.offset = offset;
//            this.limit = limit;
//        }
//
//        public int getOffset() {
//            return offset;
//        }
//
//        public int getLimit() {
//            return limit;
//        }
//
//
@Data
public class RowBounds{
    private Integer pageNum;
    private Integer pageSize;
}