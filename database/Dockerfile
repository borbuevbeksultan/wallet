FROM mysql:5.7

ENV MYSQL_DATABASE=root \
    MYSQL_ROOT_PASSWORD=iceknight

ADD database.sql /docker-entrypoint-initdb.d

EXPOSE 3306