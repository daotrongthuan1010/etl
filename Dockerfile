FROM prom/prometheus:latest

COPY src/main/resources/prometheus.yml /etc/prometheus/prometheus.yml