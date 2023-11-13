# master_slave_db
> 数据库读写分离演示
>。参考自[知乎京东云账号](https://zhuanlan.zhihu.com/p/666528202)
* 多数据源情况下，需要手动向容器内注入SqlSessionFactory，默认由MybatisAutoConfiguration注入的会出现 Invalid bound statement (not found): ...
* 手动注入的SqlSessionFactory需要手动添加plugins，以使用Mybatis提供的Intercepts拦截器