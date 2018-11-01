package ${basePackage}.input;

import ${basePackage}.model.${modelNameUpperCamel};
import javax.validation.constraints.NotNull;
/**
* Created by ${author} on ${date}.
*/
public class ${modelNameUpperCamel}Input extends ${modelNameUpperCamel} {//输入对象

    @NotNull(message = "分页参数pageNum不能为空")
    private Integer pageNum=1;

    @NotNull(message = "分页参数pageSize不能为空")
    private Integer pageSize=10;

    private String keywords;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
