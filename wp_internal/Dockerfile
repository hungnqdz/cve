FROM wordpress:latest

# Installe les outils nécessaires
RUN apt-get update && apt-get install -y wget unzip net-tools nmap iputils-ping iptables iproute2

RUN apt-get install -y rsyslog

COPY ./rsyslog.conf /etc/rsyslog.conf

RUN mkdir -p /var/log && \
    touch /var/log/syslog /var/log/auth.log /var/log/maillog && \
    chmod 644 /var/log/*
COPY ./config.sh /config.sh
RUN chmod +x /config.sh
# Télécharge et installe le plugin Ultimate Member
RUN wget -O /tmp/ultimate-member.zip https://downloads.wordpress.org/plugin/ultimate-member.2.8.2.zip \
    && unzip /tmp/ultimate-member.zip -d /usr/src/wordpress/wp-content/plugins/ \
    && rm /tmp/ultimate-member.zip
