#!/bin/bash
ip route del default via 192.168.30.1
ip route add default via 172.18.0.1
iptables -t nat -A PREROUTING -p tcp --dport 80 -j DNAT --to-destination 192.168.30.100:80
iptables -t nat -A PREROUTING -p tcp --dport 61616 -j DNAT --to-destination 192.168.30.100:61616
iptables -t nat -A PREROUTING -p tcp --dport 22 -j DNAT --to-destination 192.168.30.100:22
#iptables -t nat -A POSTROUTING -d 192.168.30.100 -p tcp --dport 22 -j MASQUERADE
iptables -t nat -A POSTROUTING -o eth1 -j MASQUERADE
iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE
