FROM eclipse-temurin:21-jdk

ARG APP_PROFILE=prod
ENV SPRING_PROFILES_ACTIVE=${APP_PROFILE}
COPY target/*.jar /app/app.jar

RUN apt-get update && \
    apt-get install -y curl gnupg lsb-release && \
    curl -fsSL https://www.postgresql.org/media/keys/ACCC4CF8.asc | gpg --dearmor -o /etc/apt/trusted.gpg.d/postgresql.gpg && \
    echo "deb http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/pgdg.list && \
    apt-get update && \
    apt-get install -y postgresql-16 postgresql-contrib-16 supervisor && \
    rm -rf /var/lib/apt/lists/*

RUN mkdir -p /var/lib/postgresql/data /app && \
    chown -R postgres:postgres /var/lib/postgresql

USER postgres
RUN /usr/lib/postgresql/16/bin/initdb -D /var/lib/postgresql/data
USER root

WORKDIR /app

ENV POSTGRES_DB=tarifadb
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=123

RUN echo "host all all 0.0.0.0/0 md5" >> /var/lib/postgresql/data/pg_hba.conf && \
    echo "listen_addresses='*'" >> /var/lib/postgresql/data/postgresql.conf && \
    su postgres -c "/usr/lib/postgresql/16/bin/pg_ctl -D /var/lib/postgresql/data -o '-c listen_addresses=*' -w start && \
    psql --username=postgres -c \"CREATE DATABASE tarifadb;\" && \
    psql --username=postgres -c \"ALTER USER postgres WITH PASSWORD '123';\" && \
    /usr/lib/postgresql/16/bin/pg_ctl -D /var/lib/postgresql/data -m fast stop"

COPY supervisord.conf /etc/supervisord.conf

EXPOSE 8080 5432

CMD ["/usr/bin/supervisord", "-c", "/etc/supervisord.conf"]