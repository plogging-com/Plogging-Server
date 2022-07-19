FROM openjdk:11-jdk
# JAR_FILE 변수에 값을 저장
ARG JAR_FILE=./build/libs/plogging-0.*.jar
# 변수에 저장된 것을 컨테이너 실행시 이름을 app.jar파일로 변경하여 컨테이너에 저장
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]