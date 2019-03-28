package com.rwx.myabtis.component.common;

import java.util.List;
import java.util.Objects;

/**
 * author: RXK
 * date: 2019/3/28 14:27
 * version: v1.0.0
 * desc: 分页对象参数
 **/
public class PageDTO<T> {

    /**
     * 当前页 条数
     * 默认为10
     */
    private Integer pageSize;

    /**
     * 当前页
     * 默认为 1
     */
    private Integer pageNo;

    /**
     * 总条数
     */
    private Integer totalNum;

    /**
     * 返回的结果
     */
    private List<T> result;

    private static PageDTO pageDTO = null;

    public static PageDTO builder(){
        if(Objects.isNull(pageDTO)){
            synchronized (PageDTO.class){
                if(Objects.isNull(pageDTO)){
                    return pageDTO = new PageDTO<>();
                }
            }
        }
        return pageDTO;
    }

    public static PageDTO builder(Integer pageNo,Integer pageSize){
        if(Objects.isNull(pageDTO)){
            synchronized (PageDTO.class){
                if(Objects.isNull(pageDTO)){
                    return pageDTO = new PageDTO<>(pageSize,pageNo);
                }
            }
        }
        return pageDTO;
    }



   public static Integer offset(){
      return (PageDTO.builder().getPageNo() - 1 ) * PageDTO.builder().getPageSize();
   }

    private PageDTO() {
        this(10,1);
    }

    private PageDTO(Integer pageSize, Integer pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
