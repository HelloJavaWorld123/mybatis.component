package ${basePackage}.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${basePackage}.dao.${modelNameUpperCamel}Mapper;
import ${basePackage}.input.${modelNameUpperCamel}Input;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.model.${modelNameUpperCamel}Example;
import ${basePackage}.service.${modelNameUpperCamel}Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
* Created by ${author} on ${date}.
*/
@Service
@Transactional(readOnly = true)
public class ${modelNameUpperCamel}ServiceImpl implements ${modelNameUpperCamel}Service {

    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    @Transactional
    @Override
    public void add(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Mapper.insert(${modelNameLowerCamel});
    }

    @Transactional
    @Override
    public void deleteById(Long ${modelNameLowerCamel}Id) {
        ${modelNameLowerCamel}Mapper.deleteByPrimaryKey(${modelNameLowerCamel}Id);
    }

    @Transactional
    @Override
    public void update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Mapper.updateByPrimaryKey(${modelNameLowerCamel});
    }

    @Override
    public ${modelNameUpperCamel} selectById(Long ${modelNameLowerCamel}Id) {
        return ${modelNameLowerCamel}Mapper.selectByPrimaryKey(${modelNameLowerCamel}Id);
    }

    @Override
    public PageInfo<${modelNameUpperCamel}> selectByInput(${modelNameUpperCamel}Input ${modelNameLowerCamel}Input) {
        ${modelNameUpperCamel}Example example=new ${modelNameUpperCamel}Example();

        ${modelNameUpperCamel}Example.Criteria criteria=example.createCriteria();

        //条件查询
        if(${modelNameLowerCamel}Input.getKeywords()!=null&&!${modelNameLowerCamel}Input.getKeywords().equals("")){

        }

        PageHelper.startPage(${modelNameLowerCamel}Input.getPageNum(),${modelNameLowerCamel}Input.getPageSize());

        List<${modelNameUpperCamel}> list=${modelNameLowerCamel}Mapper.selectByExample(example);

        return new PageInfo<>(list);
    }
}
