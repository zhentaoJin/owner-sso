# ownerSSO

基于spring boot,spring security oauth2实现sso单点登录功能。


作者：撸猫金
QQ：1660645816


## 项目说明

> 基于SpringBoot+Spring Security Oauth2+MyBatisPlus+Mysql的单点登录认证中心，包括用户管理、角色管理、权限管理、机构管理等模块。

> 项目是前后分离开发，只提供后端代码。

> 运行环境：jdk1.8 ,mysql 5.6或以上，tomcat8。

> 如果有写得不正确的地方，欢迎提交流issuez指正。

### 代码组织结构

``` lua
ownerSSO
├── sso-base-dep -- 提供基本的maven依赖
├── sso-code-genertor -- 提供代码生成器
├── sso-common -- 简单封装mybatis,druid等工具调用（目前没有用到）
├── sso-core -- 实现sso
└── sso-web -- 主要提供api


```



