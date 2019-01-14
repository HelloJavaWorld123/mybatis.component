# mybatis.component
mybatis自动生成DAO层、service层的独立项目

## 目录结构
.         
└─src
    └─main
        ├─java
        │  └─com
        │      └─rwx
        │          └─myabtis
        │              └─component
        │                  │  StartExecute.java(启动类)
        │                  │  
        │                  ├─common
        │                  │      BaseBean.java(基类)
        │                  │      
        │                  └─config
        │                          CustomerCommentGenerator.java(自定义Mapper及Entity默认的注释)
        │                          MapperInterfaceAnnotationPlugin.java(自定义Mapper接口的注解)
        │                          ModelSetSwaggerAnnotationPlugin.java(自定义Entity加入Swagger的注解)
        │                          
        └─resources
            │  log4j.properties
            │  
            └─common
                    generator.xml(基本配置)