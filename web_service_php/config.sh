#!/bin/bash
ip route del default via 192.168.10.1
ip route add default via 192.168.10.2
service rsyslog start
