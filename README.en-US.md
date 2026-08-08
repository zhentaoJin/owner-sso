

# ownerSSO

Implements SSO (Single Sign-On) functionality based on Spring Boot and Spring Security OAuth2.


Author: 撸猫金
QQ: 1660645816


## Project Description

> A single sign-on authentication center based on SpringBoot + Spring Security OAuth2 + MyBatisPlus + MySQL, including modules for user management, role management, permission management, organization management, and more.

> This project adopts a front-end/back-end separation architecture and only provides the back-end code.

> Runtime Environment: JDK 1.8, MySQL 5.6 or above, Tomcat 8.

> If you find any inaccuracies, please feel free to submit an issue for correction.

### Code Structure

``` lua
ownerSSO
├── sso-base-dep -- Provides basic Maven dependencies
├── sso-code-genertor -- Provides a code generator
├── sso-common -- Simple wrapper for MyBatis, Druid, and other utility calls (not currently used)
├── sso-core -- Implements SSO
└── sso-web -- Primarily provides APIs
```
