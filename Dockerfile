FROM eclipse-temurin:21-jdk

ARG APP_PROFILE=prod
ENV SPRING_PROFILES_ACTIVE=${APP_PROFILE}

# Copiar aplicação
COPY target/*.jar /app/app.jar

# Instalar PostgreSQL e Supervisor
RUN apt-get update && \
    apt-get install -y postgresql postgresql-contrib supervisor && \
    rm -rf /var/lib/apt/lists/*

# Criar diretórios e inicializar cluster do Postgres
RUN mkdir -p /var/lib/postgresql/data /app && \
    chown -R postgres:postgres /var/lib/postgresql

USER postgres
RUN /usr/lib/postgresql/16/bin/initdb -D /var/lib/postgresql/data
USER root

WORKDIR /app

# Variáveis de ambiente do Postgres
ENV POSTGRES_DB=tarifadb
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=123

# Script para criar banco e usuário
RUN echo "host all all 0.0.0.0/0 md5" >> /var/lib/postgresql/data/pg_hba.conf && \
    echo "listen_addresses='*'" >> /var/lib/postgresql/data/postgresql.conf && \
    su postgres -c "/usr/lib/postgresql/16/bin/pg_ctl -D /var/lib/postgresql/data -o '-c listen_addresses=*' -w start && \
    psql --username=postgres -c \"CREATE DATABASE tarifadb;\" && \
    psql --username=postgres -c \"ALTER USER postgres WITH PASSWORD '123';\" && \
    /usr/lib/postgresql/16/bin/pg_ctl -D /var/lib/postgresql/data -m fast stop"

# Copiar configuração do supervisor
COPY supervisord.conf /etc/supervisord.conf

EXPOSE 8080 5432

CMD ["/usr/bin/supervisord","-c","/etc/supervisord.conf"]
