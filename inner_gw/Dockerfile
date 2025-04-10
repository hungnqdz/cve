FROM ubuntu:latest

RUN apt-get update && apt-get install -y \
    iptables \
    iproute2 \
    tcpdump \
    sudo \
    systemd \
    net-tools \
    curl \
    iputils-ping \
    && rm -rf /var/lib/apt/lists/*
#applying ip forward
RUN echo "net.ipv4.ip_forward=1" > /etc/sysctl.conf

#copy config file
COPY config-inner-gw.sh /config-inner-gw.sh
RUN chmod +x /config-inner-gw.sh

RUN useradd -m -s /bin/bash inner_gw && echo 'inner_gw:password' | chpasswd

RUN usermod -aG sudo inner_gw && echo 'inner_gw ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers

USER inner_gw

CMD ["sleep", "infinity"]
#CMD ["/bin/bash", "-c", "exec /sbin/init --log-target=journal 3>&1"]
