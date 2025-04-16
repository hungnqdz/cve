FROM ubuntu:20.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y \
    git \
    maven \
    net-tools \
    vim \
    curl \
    wget \
    tar \
    telnet \
    nano \
    nmap \
    iputils-ping \
    traceroute \
    iptables \
    iproute2 \
    firefox \
    x11-apps \
    libgtk-3-0 \
    libdbus-glib-1-2 \
    libgdk-pixbuf2.0-0 \
    netcat-openbsd \
    gcc \
    make \
    lsb-core \
    sudo=1.8.31-1ubuntu1 && \
    wget https://github.com/adoptium/temurin18-binaries/releases/download/jdk-18.0.2%2B9/OpenJDK18U-jdk_x64_linux_hotspot_18.0.2_9.tar.gz && \
    tar -xzf OpenJDK18U-jdk_x64_linux_hotspot_18.0.2_9.tar.gz && \
    mv jdk-18.0.2+9 /usr/lib/jvm/openjdk-18 && \
    rm OpenJDK18U-jdk_x64_linux_hotspot_18.0.2_9.tar.gz && \
    update-alternatives --install /usr/bin/java java /usr/lib/jvm/openjdk-18/bin/java 1 && \
    update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/openjdk-18/bin/javac 1

ENV JAVA_HOME=/usr/lib/jvm/openjdk-18
ENV PATH=$JAVA_HOME/bin:$PATH
RUN apt update && apt-get install -y sqlmap python3 python3-pip
RUN pip install colorama urllib3 requests
RUN apt install -y apache2 rsyslog 
COPY ./rsyslog.conf /etc/rsyslog.conf

RUN useradd -u 5000 chat && mkdir -p /home/chat && chown -R chat:chat /home/chat

WORKDIR /home/chat

COPY exploit.c .
COPY shellcode.c .
COPY Makefile .
RUN make

RUN mkdir -p /var/log /var/run/apache2 /var/lock/apache2 /app/logs && \
    touch /var/log/syslog /var/log/auth.log /var/log/maillog /var/log/apache2_access.log /var/log/apache2_error.log && \
    chmod 777 /var/log/* /var/run/apache2 /var/lock/apache2 /app/logs && \
    chown -R www-data:www-data /var/run/apache2 /var/lock/apache2 && \
    chown -R chat:chat /app/logs && \
    a2enmod proxy proxy_http && \
    a2dissite 000-default
 
COPY ./springboot.conf /etc/apache2/sites-available/springboot.conf

RUN a2ensite springboot.conf && \
    echo "local0.info    /var/log/apache2_access.log" >> /etc/rsyslog.d/apache2.conf && \
    echo "local0.error   /var/log/apache2_error.log" >> /etc/rsyslog.d/apache2.con

# --- Maven build ---
WORKDIR /app
COPY . /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN chown -R chat:chat /app
## --- SSH ---
#RUN mkdir /var/run/sshd && \
#    echo 'root:password' | chpasswd && \
#    sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config

# --- ActiveMQ ---

RUN mkdir /opt/ApacheActiveMQ
COPY ./apache-activemq-5.18.2-bin.tar.gz /opt/ApacheActiveMQ/apache-activemq-5.18.2-bin.tar.gz
RUN cd /opt/ApacheActiveMQ && \
    tar -xf apache-activemq-5.18.2-bin.tar.gz && \
    chown -R chat:chat /opt/ApacheActiveMQ && \
    chmod +x /opt/ApacheActiveMQ/apache-activemq-5.18.2/bin/linux-x86-64/activemq

EXPOSE 61616 8080 8161

COPY ./config-web-server.sh /config-web-server.sh
RUN chmod +x config-web-server.sh
RUN apache2ctl start && service rsyslog start
USER chat
CMD ["/bin/sh", "-c", "/opt/ApacheActiveMQ/apache-activemq-5.18.2/bin/linux-x86-64/activemq start && ./mvnw spring-boot:run"]
