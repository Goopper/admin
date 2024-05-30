# 基于java8镜像
FROM openjdk:8-jdk-alpine

# 维护者信息
LABEL maintainer="chenbprime@outlook.com"

RUN apk add fontconfig ttf-dejavu

# VOLUME 指定了临时文件目录为/tmp。其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp

VOLUME /admin-upload

EXPOSE 80

# 复制文件并重命名
ADD ruoyi-admin/target/ruoyi-admin.jar app.jar

# 创建 /admin-upload 文件夹
CMD mkdir -p /admin-upload

# 运行jar包
ENTRYPOINT ["java","-jar","/app.jar"]