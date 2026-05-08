FROM eclipse-temurin:21-jdk

# Perfil da aplicação
ARG APP_PROFILE=prod
ENV SPRING_PROFILES_ACTIVE=${APP_PROFILE}

# Copiar aplicação
COPY target/*.jar /app/app.jar

# Instalar PostgreSQL e Supervisor
RUN apk add --no-cache postgresql postgresql-contrib supervisor

# Criar diretórios
RUN mkdir -p /var/lib/postgresql/data /app
WORKDIR /app

# Variáveis de ambiente do Postgres (alinhadas com application.yml)
ENV POSTGRES_DB=tarifadb
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=123

# Copiar configuração do supervisor
COPY supervisord.conf /etc/supervisord.conf

EXPOSE 8080 5432

CMD ["/usr/bin/supervisord","-c","/etc/supervisord.conf"]
