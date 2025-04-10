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

COPY config-ws3.sh /config-ws3.sh
RUN chmod +x /config-ws3.sh

RUN useradd -m -s /bin/bash ws3 && echo 'ws3:password' | chpasswd

RUN usermod -aG sudo ws3 && echo 'ws3 ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers

USER ws3

CMD ["sleep", "infinity"]
