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
    sudo \
    libgtk-3-0 \
    libdbus-glib-1-2 \
    libgdk-pixbuf2.0-0 \
    netcat

#COPY config-ws3.sh /config-ws3.sh
#RUN chmod +x /config-ws3.sh

RUN useradd -m -s /bin/bash attacker && echo 'attacker:password' | chpasswd

RUN usermod -aG sudo attacker && echo 'attacker ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers

COPY ./exploit.py /exploit.py
COPY ./poc.xml /poc.xml
RUN chmod 777 /exploit.py
RUN chmod 777 /poc.xml

USER attacker

CMD ["sleep", "infinity"]
