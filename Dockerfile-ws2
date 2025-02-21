FROM ubuntu:20.04

ENV DEBIAN_FRONTEND=noninteractive
ENV DISPLAY=:0

RUN apt update && apt install -y \
    net-tools \
    telnet \
    nano \
    nmap \
    iputils-ping \
    curl \
    traceroute \
    iptables \
    iproute2 \
    firefox \
    x11-apps \
    libgtk-3-0 \
    libdbus-glib-1-2 \
    libgdk-pixbuf2.0-0 && \
    rm -rf /var/lib/apt/lists/*

COPY config-ws2.sh /config-ws2.sh
RUN chmod +x /config-ws2.sh

RUN useradd -m -s /bin/bash ws2 && echo 'ws2:password' | chpasswd

RUN usermod -aG sudo ws2 && echo 'ws2 ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers

USER ws2

CMD ["sleep", "infinity"]
