package com.rwx.mybatis.component.coustomer;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.TypedPropertyHolder;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

import java.util.List;

/**
 * Author：RXK
 * Date:2018/11/1 14:27
 * Version: V1.0.0
 * Des: 自定义service层标签 解析类
 **/
public class JavaServiceGeneratorConfiguration extends TypedPropertyHolder {

    private String targetPackage;
    private String targetProject;
    private String implementationPackage;


    public XmlElement toXmlElement(){
        XmlElement element = new XmlElement("JavaServiceGenerator");

        if(StringUtils.isNotEmpty(getConfigurationType())){
            element.addAttribute(new Attribute("type",getConfigurationType() ));
        }

        if(StringUtils.isNotEmpty(targetPackage)){
            element.addAttribute(new Attribute("targetPackage", targetPackage));
        }

        if(StringUtils.isNotEmpty(targetProject)){
            element.addAttribute(new Attribute("targetProject", targetProject));
        }

        if(StringUtils.isNotEmpty(implementationPackage)){
            element.addAttribute(new Attribute("implementationPackage", targetProject));
        }

        addPropertyXmlElements(element);

        return element;
    }

    public JavaServiceGeneratorConfiguration() {
        super();
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public String getImplementationPackage() {
        return implementationPackage;
    }

    public void setImplementationPackage(String implementationPackage) {
        this.implementationPackage = implementationPackage;
    }


    public void validate(List<String> errors,String contextId){
        if(!StringUtility.stringHasValue(targetPackage)){
            errors.add(Messages.getString("ValidationError.2", contextId));
        }

        if(! StringUtility.stringHasValue(targetProject)){
            errors.add(Messages.getString("ValidationError.12","JavaServiceGenerator", contextId));
        }

        if (!StringUtility.stringHasValue(getConfigurationType())) {
            errors.add(Messages.getString("ValidationError.20",contextId));
        }


    }

}
