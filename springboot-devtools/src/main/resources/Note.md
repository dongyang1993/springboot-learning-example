##### 1. 添加maven依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <!--optional为true依赖不会传递-->
    <optional>true</optional>
</dependency>
```

##### 2. IDEA配置
> 1. 在Application配置中勾选update classes and resources
> 2. Settings --> Compile --> Build project automatically --> 勾选
> 3. CTRL + SHIFT + ALT + / --> 查找Registry --> 找到并勾选compiler.automake.allow.when.app.running 

##### 3. 提示
> 1. 只能热部署代码块级别的，不能热部署方法级别的 
> 2. 步骤2中的2、3部是非必要的，手动刷新即可