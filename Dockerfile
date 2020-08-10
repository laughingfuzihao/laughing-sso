FROM java:8

COPY sso.jar /sso.jar

CMD ["--server.port=7070"]

EXPOSE 9527

ENTRYPOINT ["java","-jar","/sso.jar"]

# docker build -t sso .
# docker run -d -e TZ="Asia/Shanghai" -p 7070:7070 --name sso sso
