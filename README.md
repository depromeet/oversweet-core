# 13th-2team-backend-
🌟 2팀 백엔드 가보자고.

# 빌드 및 실행 방법
## local
- docker 로 mysql 실행
```text
docker pull mysql:8.0.33
docker images
docker run --name mysql-3307 -e MYSQL_ROOT_PASSWORD='root' -d -p 3307:3306 mysql:8.0.33
```
- schema 생성
```text
create database oversweet;
```