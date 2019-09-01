## 项目介绍

  对生鲜销售通过全面的信息采集和处理并辅助提高超市的决策水平，可以迅速提升超市的管理水平，为降低经营成本，提高效益，增强超市的扩张力，提供有效的技术保障。

### 组织结构

```
pt-parent
├── pt-api -- controller接口和文档的编写
├── pt-common -- 通用代码
├── pt-gateway[9000] -- 路由模块及权限验证模块
├── pt-log -- 全局日志模块
├── pt-mbg -- 实体类模块
├── pt-registry[9010] -- 微服务注册中心
├── pt-service-bms[9100] -- 基础模块微服务
├── pt-service-oms[9120] -- 订单模块微服务
├── pt-service-ums[9110] -- 用户模块微服务
├── pt-upload[9020] -- 文件上传模块
```

### 技术选型

| 技术             | 说明                   |
| :--------------- | :--------------------- |
| Spring Cloud     | 微服务                 |
| Spring Boot      | 容器+MVC框架           |
| Spring Security  | 认证和授权框架         |
| MyBatis          | ORM框架                |
| MyBatisGenerator | 数据层实体类生成       |
| PageHelper       | MyBatis物理分页插件    |
| Swagger-UI       | 文档生产工具           |
| OSS              | 对象存储               |
| JWT              | JWT登录支持            |
| Lombok           | 简化对象封装工具       |
| 通用Mapper       | 简化单表的增删改查操作 |

#### 前端技术

| 技术                       | 说明                              |
| -------------------------- | --------------------------------- |
| Vue                        | 前端框架                          |
| Vue-router                 | 路由框架                          |
| Vuex                       | 全局状态管理框架                  |
| Element                    | 前端UI框架                        |
| Axios                      | 前端HTTP框架                      |
| vue-element-admin-template | 基于Vue+Element的简单管理后台模板 |

