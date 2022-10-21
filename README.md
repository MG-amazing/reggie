# 瑞吉外卖 
  
 本项目是继瑞吉外卖,和瑞吉外卖剩余功能开发做出的.
 
 ## 毕业设计推荐
  
  
  
 ## 在本地运行 
  
 Clone 这个 project 
  
 ```bash 
   git clone https://github.com/MG-amazing/reggie.git 
  
 ``` 
 然后解压此文件夹,导入至项目运行即可. 
  
  
  
 ## 技术栈 
  
 **客户端:** Vue,ElementUI,axios请求库 
  
 **服务端:** SpringBoot,MyBatis-Plus 
  
  
 ## 开发 
  
 要部署这个项目，请将sql导入到本地数据库 
  
 执行data.sql 
 创建数据库插入数据表 
  
  
 ## 异常处理 
  
 在构建本地项目时可能会出现异常 
  
 1.若出现SqlException请检查本地数据库是否配置正常,或者application.yml中信息是否配置正常 
  
 2.若出现其他功能异常,请在Test目录中对数据库进行测试,本次开发使用的数据库版本为5.6版本,若出现异常可切换数据库版本
