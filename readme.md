##iView配置
成功运行iview-admin之后，您就可以开始动手修改它，将自己的内容替换进去了。 首先最基础也是最重要的，应该说是路由配置，路由配置里目前有三种类型的配置，对应三种页面的展示类型。直接来看代码
###路由配置
- router.js
- 第一种类型：该页面在整个浏览器区域展现，代表性的有登录页、404页等

		export const loginRouter = {
		    path: '/login',  //必填
		    name: 'login',  // 必填，页面都以name值来加载
		    meta: {
		        title: 'Login - 登录'  // 非必填，若不填默认显示iview-admin（后面会介绍在哪修改默认显示的值）
		    },
		    component: resolve => { require(['./views/login.vue'], resolve); }  // 必填，用来加载该路由规则对应的视图，resolve用来控制异步加载
		};
- 第二种类型：该页面在Main组件的子页面区域展示，但不在左侧菜单栏显示，代表性的有首页、消息中心等

		export const otherRouter = {
		    path: '/',  // 必填
		    name: 'otherRouter',  // 必填，在面包屑处理中需要用到，值固定为otherRouter（或者可以在./src/libs/util.js中修改）
		    redirect: '/home',  // 选填，这里如果不填在浏览器地址栏输入域名后自动跳转到首页
		    component: Main,  // 必填，主组件，用于加载侧边栏和右侧面包屑、标签栏、工具条、子页面路由视图等
		    children: [  // 在Main右侧视图显示的页面都要作为otherRouter的children来添加
		        {  // home页面
		            path: 'home',  // 必填，在地址栏将以 '域名/home'的的形式呈现
		            title: '首页', // 必填，这个title会在标签栏显示
		            name: 'home_index',  // 必填，该页面是通过name值来加载的，切记每个路由对象的名字都要和其他的不一样
		            component: resolve => { require(['./views/home/home.vue'], resolve); }   // 必填
		        }
		    ]
		};
- 第三种类型：该页面在Main组件的子页面区域展示，且在左侧菜单栏显示，对应有两种情况

		export const appRouter = [{  // a.第一种情况：只有一级菜单
		        path: '/access',  // 必填
		        redirect: '/access/index',  // 选填，如果不填也会跳转到这个路径
		        icon: 'key',  // 必填，此icon将显示在左侧菜单栏
		        name: 'access',  // 必填
		        title: '权限管理',  // 必填，此title值将显示在左侧菜单栏
		        component: Main,  // 必填，且固定，用于加载Main组件
		        children: [  // 要显示在右侧区域的页面必须作为children来添加
		            { 
		                path: 'index',  // 必填
		                title: '权限管理',  // 必填，将显示在标签栏对应标签
		                name: 'access_index',  // 必填，且不能和其父路由的name不一致（与其他任何路由的name值都不能一致）
		                component: resolve => { require(['./views/access/access.vue'], resolve); }   // 必填
		            }
		        ]
		    },
		    {  // b.第二种情况：有二级菜单
		        path: '/component',  // 必填
		        redirect: '/component/text-editor',  // 选填，如果不填在地址栏输入'域名/access'时将默认打开此一级菜单对应的第一个二级菜单页面
		        icon: 'social-buffer',  // 必填，同上
		        name: 'component',  // 必填，同上
		        title: '组件',  // 必填，同上
		        component: Main,  // 必填，同上
		        children: [  // 必填，同上
		            {
		                path: 'text-editor',  // 必填，同上
		                icon: 'compose',  // 必填，同上
		                name: 'text-editor',  // 必填，同上
		                title: '富文本编辑器',  // 必填，将显示在左侧菜单栏二级菜单
		                component: resolve => { require(['./views/my_components/text-editor/text-editor.vue'], resolve); }  // 必填
		            },
		            {
		                path: 'md-editor',  // 必填，同上
		                icon: 'pound',  // 必填，同上
		                name: 'md-editor',  // 必填，同上
		                title: 'Markdown编辑器',  // 必填，同上
		                component: resolve => { require(['./views/my_components/markdown-editor/markdown-editor.vue'], resolve); }
		            },  // 必填
		        ]
		    }
		}
- 您还可以为页面配置权限，在左侧菜单初始化的时候，会通过当前登录用户的权限值来过滤路由配置，从而决定在左侧菜单栏显示哪些选项。权限配置很简单，只需在路由对象里设置'access'属性即可：

		{
	        path: '/access-test',
	        icon: 'lock-combination',
	        title: '权限测试页',
	        name: 'accesstest',
	        access: 0,  // 如果设置access值，那么当登录用户的权限值不为0时则该菜单及其二级菜单都不会出现在左侧菜单栏；
	                    // 如果不设置access值，那么该菜单默认显示；
	                    // access如果只有一个权限值过滤，那么直接写一个数字即可（如这的0）,如果有多个，则写成数组类型（如[0,1]）。
	        component: Main,
	        children: [
	            { path: 'index', title: '权限测试页', name: 'accesstest_index' }
	        ]
	    },
###Vue开发
- 开发的语法

##Shiro配置
###单点登录
- shiro-cas
- 需要换成ps4j
- https://blog.csdn.net/u010475041/article/details/78140643
- https://blog.csdn.net/hxpjava1/article/details/77934056
- https://blog.csdn.net/ywslakers123/article/details/78288112
###Shiro-Ehcache配置
- 配置文件：src/main/resources/ehcache-shiro.xml
- name：缓存名称。 
- maxElementsInMemory：缓存最大数目
- maxElementsOnDisk：硬盘最大缓存个数。
- eternal：对象是否永久有效，一但设置了，timeout将不起作用。
- overflowToDisk：是否保存到磁盘，当系统当机时
- timeToIdleSeconds：设置对象在失效前的允许闲置时间（单位：秒）。仅当eternal=false对象不是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大。
- timeToLiveSeconds：设置对象在失效前允许存活时间（单位：秒）。最大时间介于创建时间和失效时间之间。仅当eternal=false对象不是永久有效时使用，默认是0.，也就是对象存活时间无穷大。
- diskPersistent：是否缓存虚拟机重启期数据 Whether the disk store persists between restarts of the Virtual Machine. The default value is false.
- diskSpoolBufferSizeMB：这个参数设置DiskStore（磁盘缓存）的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区。
- diskExpiryThreadIntervalSeconds：磁盘失效线程运行时间间隔，默认是120秒。
- memoryStoreEvictionPolicy：当达到maxElementsInMemory限制时，Ehcache将会根据指定的策略去清理内存。默认策略是LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。
- clearOnFlush：内存数量最大时是否清除。
- memoryStoreEvictionPolicy：Ehcache的三种清空策略;
    - FIFO，first in first out，先进先出。
    - LFU， Less Frequently Used，一直最少被使用的。缓存元素有hit属性，其值最小的将会被清理。
    - LRU，Least Recently Used，最近最少使用的，缓存元素有时间戳，离当前时间最远的被清理。

##Redis配置
###依赖配置
- org.springframework.boot:spring-boot-starter-data-redis
- org.springframework.session:spring-session-data-redis:1.3.1.RELEASE

###配置类
- 新建配置类RedisClusterConfig.java
- 添加@Configuration和@EnableRedisHttpSession注解

###单机配置
- spring.redis.host = localhost
- spring.redis.port = 6379
###集群配置
- spring.redis.cluster.nodes=10.3.254.55:6379,10.3.254.55:6380
- 多个地址之间使用逗号隔开
 
##Elasticsearch配置
###加载依赖
- org.elasticsearch.client:transport:${elaSearchVersion}
- org.apache.logging.log4j:log4j-to-slf4j:${log4j2slf4jVersion}

###添加配置类
- com.dyenigma.twinsapi.config.ElasticsearchConfigure.java

###配置文件
- spring.data.elasticsearch.cluster-nodes = 10.3.254.53:9300;10.3.254.54:9300;10.3.254.55:9300
- spring.data.elasticsearchlocal = false
- spring.data.elasticsearch.properties.transport.tcp.connect_timeout = 60s

##MongoDB配置
###加载依赖
- org.springframework.boot:spring-boot-starter-data-mongodb

###添加配置类
- com.dyenigma.twinsapi.config.MasterMongoConfigure.java
- com.dyenigma.twinsapi.config.SlaveMongoConfigure.java

###配置文件，多数据源
- spring.data.mongodb.master.host = 10.3.50.221,10.3.50.221,10.3.50.221
- spring.data.mongodb.master.port = 27221
- spring.data.mongodb.master.database = slave
- spring.data.mongodb.master.username = bigdata
- spring.data.mongodb.master.password = bigdata&2018
- spring.data.mongodb.slave.host = 10.3.50.221
- spring.data.mongodb.slave.port = 27221
- spring.data.mongodb.slave.database = slave
- spring.data.mongodb.slave.username = bigdata
- spring.data.mongodb.slave.password = bigdata&2018

##Druid数据源配置
###加载依赖
- com.alibaba:druid-spring-boot-starter:${druidVersion}

###DruidDataSource配置属性列表
- url：连接数据库的url
- username：连接数据库的用户名
- password：连接数据库的密码
- driverClassName：根据url自动识别
- initialSize：默认值0，初始化时建立物理连接的个数。
- maxActive：最大连接池数量
- minIdle：最小连接池数量
- maxWait：获取连接时最大等待时间，单位毫秒
- poolPreparedStatements：默认值false，是否缓存preparedStatement
- maxPoolPreparedStatementPerConnectionSize：默认值-1，要启用PSCache，必须配置大于0
- validationQuery：用来检测连接是否有效的sql，常用select 'x'
- validationQueryTimeout：单位：秒，检测连接是否有效的超时时间
- testOnBorrow：默认值true，申请连接时执行检测连接是否有效，做了这个配置会降低性能
- testOnReturn：默认值false，归还连接时执行检测连接是否有效，做了这个配置会降低性能
- testWhileIdle：默认值false，建议配置为true，不影响性能，并且保证安全性
- keepAlive：默认值false
- timeBetweenEvictionRunsMillis：默认1分钟
- minEvictableIdleTimeMillis：连接保持空闲而不被驱逐的最小时间
- connectionInitSqls：物理连接初始化的时候执行的sql
- exceptionSorter：当数据库抛出一些不可恢复的异常时，抛弃连接
- filters：属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat，日志用的filter:log4j，防御sql注入的filter:wall
- proxyFilters：Filter集合，如果同时配置了filters和proxyFilters，是组合关系，并非替换关系

###WebStatFilter配置
-  enabled：是否开启该配置
-  url-pattern：配置基本监控路径
-  exclusions：经常需要排除一些不必要的url
-  session-stat-enable：session统计功能开关
-  session-stat-max-count：缺省sessionStatMaxCount是1000个
-  profile-enable：监控单个url调用的sql列表
-  principalSessionName：配置当前的session用户
-  principalCookieName：配置当前的user

###StatViewServlet配置
- enabled：默认true
- url-pattern：内置监控页面访问路径
- reset-enable：清零功能开关
- login-username：监控页面的访问账号
- login-password：监控页面的访问密码
- allow：IP白名单，多个使用逗号隔开，优先级低
- deny：IP黑名单，多个使用逗号隔开，优先级高

###密码加密