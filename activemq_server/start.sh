#!/bin/bash
# Khởi động ActiveMQ
/opt/ApacheActiveMQ/apache-activemq-5.18.2/bin/linux-x86-64/activemq start &

# Khởi động SSH
/usr/sbin/sshd &


# Giữ container chạy
tail -f /dev/null