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

COPY ./config-ws1.sh /config-ws1.sh
RUN chmod +x /config-ws1.sh

RUN useradd -m -s /bin/bash ws1 && echo 'ws1:password' | chpasswd

RUN usermod -aG sudo ws1 && echo 'ws1 ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers

USER ws1

CMD ["sleep", "infinity"]
