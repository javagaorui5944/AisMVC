# AisMVC,一款仿SpringMVC轻便框架的开发框架
写这个框架的意义：
在过去使用springmvc框架作为项目中的Controller层,springmvc的简洁配置和使用让我们选择了它,但这个框架我用着也发现了它也有很多冗余功能的地方。我们团队开发web项目一般采用前后端分离,所以后端的的Controller层的功能仅仅只有提供ajax接口,页面集成后的跳转,过滤器和拦截器,所以我就想着自己写一款仿springmvc的mvc框架作为自己和团队以后的开发中小型项目的框架,舍掉冗余的部分,只要项目中实际需要的功能。

1.0 初始化项目

1.1 dhy join 1.1

1.2 mvc框架功能雏形

1.3 增加handlerMapping处理器映射,控制器卸耦

1.4 增加config.ini配置文件,实现定向动态扫描项目中的@Controller 类

1.5 增加注解参数RequestMethod 默认http请求类型,请求类型不合法返回405状态码

1.6 下一步准备做项目的容错处理,过滤器准备用原生的不准备加入框架

* 注解
* 请求路由 
* 方法参数注入 
* String类型方法转发到web-inf下的页面
* @ResponseBody注解实现ajax接口 
* 增加config.ini配置文件,实现定向动态扫描项目中的@Controller 类
* 增加注解参数RequestMethod 默认http请求类型,请求类型不合法返回405状态码
