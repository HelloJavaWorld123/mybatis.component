package ${basePackage}.web;
import ${basePackage}.core.Result;
import ${basePackage}.util.ResultUtil;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.input.${modelNameUpperCamel}Input;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    /**
    * 增加
    * @param ${modelNameLowerCamel}
    * @return
    */
    @PostMapping("/add")
    public Result add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.add(${modelNameLowerCamel});
        return ResultUtil.success();
    }

    /**
    * 删除
    * @param id
    * @return
    */
    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultUtil.success();
    }

    /**
    * 修改
    * @param ${modelNameLowerCamel}
    * @return
    */
    @PostMapping("/update")
    public Result update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultUtil.success();
    }

    /**
    * 通过id查找
    * @param id
    * @return
    */
    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.selectById(id);
        return ResultUtil.success(${modelNameLowerCamel});
    }

    /**
    * 分页查找
    * @param input 输入对象
    * @return
    */
    @PostMapping("/list")
    public Result list(@RequestBody ${modelNameUpperCamel}Input input) {
        PageInfo<${modelNameUpperCamel}> pageInfo = ${modelNameLowerCamel}Service.selectByInput(input);
        return ResultUtil.success(pageInfo);
    }
}
