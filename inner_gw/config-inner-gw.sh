#!/bin/bash
ip route del default via 192.168.10.1
ip route add default via 192.168.30.2
iptables -t nat -A POSTROUTING -o eth2 -j MASQUERADE

