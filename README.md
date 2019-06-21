基础项目信息  
--------------  
项目文件夹：examination_system  
数据库文件：examination_system.sql  
此项目是使用SSM后端框架+Bootstrap前端框架实现的一个简单考务系统.  
  
  
使用技术   
---------  
IOC容器：Spring   
Web框架：SpringMVC   
ORM框架：Mybatis   
数据源：C3P0   
日志：log4j   
前端框架：Bootstrap  
运行环境 jdk8+tomcat8+mysql+Eclipse+maven  
项目技术： spring+spring mvc+mybatis+bootstrap+jquery  
  
  
搭建并运行此项目  
-----------------  
1、下载eclipse for java ee ：https://www.eclipse.org/downloads/   （可以自寻搜索eclipse的汉化方法进行汉化）  
2、下载jdk1.8：https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html  
3、下载tomcat v8.5：http://tomcat.apache.org/  
4、下载mysql 5.5.62 :https://dev.mysql.com/downloads/mysql/5.5.html  
5、下载Navicat for MySQL：https://www.navicat.com.cn/  
6、安装mysql和navicat，配置mysql：https://blog.csdn.net/watestill/article/details/81532780，并在navicat中创建与mysql的连接  
7、创建好navicat与mysql的连接之后在左边的框框中会出现类似“localhost_3306”这样名字的连接名，右击它选择运行SQL文件...，选择我们提供的examination_system.sql文件，即可导入项目依赖的数据库  
8、安装jdk1.8并设置环境变量：https://blog.csdn.net/write6/article/details/79136388  
9、安装tomcat和eclipse，并配置：https://www.cnblogs.com/kangxingyue-210/p/7489288.html  
10、在eclipse下点文件——导入（选择maven文件夹下的Existing Maven Projects）——路径选择我们的examination_system项目——导入  
11、在左边的项目资源管理器中找到刚刚导入的examination_system项目，右键它——Maven——update project，之后请耐心等待项目更新结束，它正在联网下载examination_system/pom.xml中的指定资源  
12、在eclipse下面的框框中选择Servers——添加server——一步步选择添加tomcat v8.5服务器，创建好服务器之后右键此服务器选择Add And Remove将examination_system项目加入服务器，再右键服务器选择start，此时正在开启服务器请等待  
13、右键左边的项目资源管理器中的examination_system项目——运行方式——run on Server——选择刚刚创建的服务器之后确定就好，此时会打开在eclipse中设置的默认浏览器自动访问该项目的login页面。  
14、之后您就可以随意测试这个考务系统了。  
  
注：在执行以上的装载步骤时，若遇到任何问题请自寻上网搜索解决方案。  
  
  
基础账户  
---------  
在examination_system.sql中我们提供了几个供您接触此项目时可以使用的账户（使用超级管理员账户您就可以创建自己定制的用户了）    
先输入的串是账号，后输入的串是密码：   
  
超级管理员（1个）：  
admin	admin  
  
教师（3个）：  
1000	123  
1001	123  
1002	123  
  
学生（17个）：  
10000	123  
10001	123  
10002	123  
...	...（123）  
10015	123  
10016	123  
  
  
具体页面：  
登录页：  
![Alt text](https://github.com/Zoutao6/examination_system-/raw/master/images/1.png)  
  
管理员页：管理员账户：admin+123  
![Alt text](https://github.com/Zoutao6/examination_system-/raw/master/images/2.png)  
  
学生页：学生登录: 10001+123  
![Alt text](https://github.com/Zoutao6/examination_system-/raw/master/images/3.png)  
  
老师页：教师登录：1001+123  
![Alt text](https://github.com/Zoutao6/examination_system-/raw/master/images/4.png)  
  
一些功能：  
![Alt text](https://github.com/Zoutao6/examination_system-/raw/master/images/5.png)  



