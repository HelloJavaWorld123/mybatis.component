package com.rwx.mybatis.component.config;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Author：RXK
 * Date:2018/11/1 17:29
 * Version: V1.0.0
 * Des:
 **/
public class JavaServicePlugin extends PluginAdapter {

    private String targetProject;

    private String targetPackage;

    private String basePackage;

    private String controllerTargetPackage;


    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }


    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);

        String targetPackage = properties.getProperty("targetPackage");
        if(StringUtility.stringHasValue(targetPackage)) {
            this.targetPackage = targetPackage;
        }else{
            throw new IllegalArgumentException("生成service层时缺少包路径");
        }

        String targetProject = properties.getProperty("targetProject");
        if(StringUtility.stringHasValue(targetPackage)){
            this.targetProject = targetProject;
        }else{
            throw new IllegalArgumentException("生成service层时缺少工程路径");
        }

        String basePackage = properties.getProperty("basePackage");
        if(StringUtility.stringHasValue(basePackage)){
            this.basePackage = basePackage;
        }else{
            throw new IllegalArgumentException("生成service层时缺少基本的路径");
        }

        String controllerTargetPackage = properties.getProperty("controllerTargetPackage");
        if(StringUtility.stringHasValue(controllerTargetPackage)){
            this.controllerTargetPackage = controllerTargetPackage;
        }else{
            throw new IllegalArgumentException("生成controller时缺少路径");
        }
    }

    /**
     * 通过该方法 自定义生成 service 层
     * @param introspectedTable ：数据库表的相关信息
     * @return ：service 层文件
     */
    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> list = new ArrayList<>();
        GeneratedJavaFile service = generateService(introspectedTable);
        if(null != service){
            list.add(service);
            GeneratedJavaFile controller = generateController(introspectedTable,service);
            if(null != controller){
                list.add(controller);
            }
        }

        GeneratedJavaFile serviceImpl = generateServiceImpl(introspectedTable);
        if(null != serviceImpl){
            list.add(serviceImpl);
        }

        if(!CollectionUtils.isEmpty(list)){
            return list;
        }
        return super.contextGenerateAdditionalJavaFiles();
    }

    /**
     * 生成controller类
     */
    private GeneratedJavaFile generateController(IntrospectedTable introspectedTable, GeneratedJavaFile service) {
        String domainObjectName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        String controllerName = domainObjectName + "Controller";
        String controllerPath = controllerTargetPackage + "." + controllerName;

//        File file = new File(targetProject+"\\\\"+changePath(controllerPath)+".java");
//        if(file.exists()){
//            System.out.println("controller文件已经存在，不在生成");
//            return null;
//        }

        TopLevelClass controller = new TopLevelClass(new FullyQualifiedJavaType(controllerPath));
        CompilationUnit compilationUnit = service.getCompilationUnit();
        //类名
        String shortName = compilationUnit.getType().getShortName();
        //全路径名
        String fullyQualifiedName = compilationUnit.getType().getFullyQualifiedName();
        //包路径名
        String packageName = compilationUnit.getType().getPackageName();

        controller.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RestController;"));
        controller.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
        controller.addImportedType(new FullyQualifiedJavaType(fullyQualifiedName));
        controller.addAnnotation("@RestController");
        controller.setVisibility(JavaVisibility.PUBLIC);


        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.addAnnotation("@Autowired");
        field.setType(new FullyQualifiedJavaType(shortName));
        field.setName(FirstLetterLowerCase(shortName));
        controller.addField(field);
        return new GeneratedJavaFile(controller, targetProject);
    }


    private GeneratedJavaFile generateService(IntrospectedTable introspectedTable) {
        //获取实体类的java类型 或者说是 java包全路径
//        FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        //再获取主键的java 类型 如果有基类的话 可以作为参数添加进去
//        FullyQualifiedJavaType primaryKeyType = introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType();

        //生成的servicejava
        String serviceName = targetPackage + "."+introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Service";
//        File file = new File(targetProject+"\\\\"+changePath(serviceName)+".java");
//        if(file.exists()){
//            System.out.println("service文件已存在不在生成");
//            return null;
//        }
        //生成service 文件
        Interface inter = new Interface(new FullyQualifiedJavaType(serviceName));
        //设置文件的可见性
        inter.setVisibility(JavaVisibility.PUBLIC);
        //导入包
//        inter.addImportedType(entityType);
        return new GeneratedJavaFile(inter,targetProject);
    }

    private String changePath(String serviceName) {
        if(StringUtils.isEmpty(serviceName)){
            return serviceName;
        }
        return serviceName.replaceAll("\\.", "\\\\");
    }


    private GeneratedJavaFile generateServiceImpl(IntrospectedTable introspectedTable) {
        //获取基本类
        FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        String domainObjectName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();

        String service = targetPackage + "." + entityType.getShortName() + "Service";
        String serviceImpl = targetPackage + "." + "impl." + domainObjectName + "Impl";
        String mapper =domainObjectName+"Mapper" ;
        String mapperInterType = basePackage + ".dao"+"."+mapper;

//        File file = new File(targetProject+"\\\\"+changePath(serviceImpl)+".java");
//        if(file.exists()){
//            System.out.println("service层已经存在不在生成");
//            return null;
//        }
        TopLevelClass aClass = new TopLevelClass(new FullyQualifiedJavaType(serviceImpl));
        aClass.setVisibility(JavaVisibility.PUBLIC);

        //导包 并加注解
        aClass.addImportedType(new FullyQualifiedJavaType(service));
//        aClass.addImportedType(entityType);
        aClass.addImportedType(new FullyQualifiedJavaType(mapperInterType));
        aClass.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Service"));
        aClass.addImportedType(new FullyQualifiedJavaType("org.springframework.transaction.annotation.Transactional"));
        aClass.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
        aClass.addAnnotation("@Service");
        aClass.addAnnotation("@Transactional");
        aClass.addSuperInterface(new FullyQualifiedJavaType(service));

        //添加属性
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.addAnnotation("@Autowired");
        field.setType(new FullyQualifiedJavaType(mapperInterType));
        field.setName(FirstLetterLowerCase(mapper));
        aClass.addField(field);

        return new GeneratedJavaFile(aClass, targetProject);
    }




    private String FirstLetterLowerCase(String mapper) {
        if(StringUtils.isEmpty(mapper)){
            return null;
        }
        char c = mapper.charAt(0);
        if(c>='A'&& c<='Z' ){
            String s = String.valueOf(c);
            return mapper.replaceFirst(s, s.toLowerCase());
        }
        return mapper;
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getControllerTargetPackage() {
        return controllerTargetPackage;
    }

    public void setControllerTargetPackage(String controllerTargetPackage) {
        this.controllerTargetPackage = controllerTargetPackage;
    }
}