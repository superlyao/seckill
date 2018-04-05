# seckill
- 采用spring+springmvc+mybatis
- 学习并发和spring的demo

## 踩坑记录
- 数据库配置如:用户名，密码不能直接用username,password，可能会造成数据连接失败

## 集成sentry

### 安装sentry-安装环境（centos7）
1. centos7上安装docker
- yum install docker-io

2. 使用docker安装redis,postgres,sentry

- docker pull mysql
- docker pull postgres
- docker pull sentry 

3. 启动redis和postgres

- docker run -d --name sentry-redis redis
- docker run -d --name sentry-postgres -e POSTGRES_PASSWORD=secret -e POSTGRES_USER=sentry postgres
- docker run --rm sentry config generate-secret-key `该指令会生成一个key,复制保存`

4. 启动sentry(将下面所有`<secret-key>`替换成第三步生成的key)
- docker run -it --rm -e SENTRY_SECRET_KEY=`<secret-key>` --link sentry-postgres:postgres --link sentry-redis:redis sentry upgrade
  `这一步会让你输入一个email和password，这里相当于注册管理员账号`
- docker run -d -p 9000:9000 --name my-sentry -e SENTRY_SECRET_KEY=`<secret-key>` --link sentry-redis:redis --link sentry-postgres:postgres sentry
- docker run -d --name sentry-cron -e SENTRY_SECRET_KEY=`<secret-key>` --link sentry-postgres:postgres --link sentry-redis:redis sentry run cron
- docker run -d --name sentry-worker-1 -e SENTRY_SECRET_KEY=`<secret-key>` --link sentry-postgres:postgres --link sentry-redis:redis sentry run worker

5. 访问你的虚拟机地址 如我的`http://192.168.150.128:9000`,就可以见到sentry的登录界面
