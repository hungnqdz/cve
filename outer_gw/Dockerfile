FROM ubuntu:latest

RUN apt-get update && apt-get install -y \
    iptables \
    iproute2 \
    tcpdump \
    net-tools \
    curl \
    iputils-ping

RUN echo "net.ipv4.ip_forward=1" > /etc/sysctl.conf

COPY config-outer-gw.sh /config-outer-gw.sh

RUN chmod +x /config-outer-gw.sh

RUN useradd -m -s /bin/bash outer_gw && echo 'outer_gw:password' | chpasswd

RUN usermod -aG sudo outer_gw && echo 'outer_gw ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers

USER outer_gw

CMD ["sleep", "infinity"]
