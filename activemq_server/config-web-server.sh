#!/bin/bash
ip route del default
ip route add default via 192.168.30.2
apache2ctl start
service rsyslog start
/usr/sbin/sshd -D
