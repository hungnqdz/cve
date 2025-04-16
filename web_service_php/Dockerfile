FROM httpd:2.4.50

RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    unzip \
    net-tools \
    telnet \
    nano \
    nmap \
    iputils-ping \
    curl \
    traceroute \
    iptables \
    iproute2 
   
RUN apt-get install -y rsyslog

COPY ./rsyslog.conf /etc/rsyslog.conf

RUN mkdir -p /var/log && \
    touch /var/log/syslog /var/log/auth.log /var/log/maillog && \
    chmod 644 /var/log/*

RUN apt-get update && apt-get install -y \
    php \
    libapache2-mod-php7.3
COPY ./config.sh /config.sh
RUN chmod +x /config.sh
COPY ./src /usr/local/apache2/htdocs/
RUN chown -R daemon:daemon /usr/local/apache2/htdocs/upload && chmod 755 /usr/local/apache2/htdocs/upload
RUN rm /usr/local/apache2/htdocs/index.html
COPY ./httpd.conf /usr/local/apache2/conf/httpd.conf
