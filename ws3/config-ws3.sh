#!/bin/bash
ip route del default via 192.168.20.1
ip route add default via 192.168.20.2
