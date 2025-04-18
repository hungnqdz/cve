#!/bin/bash

docker exec  -it --privileged --user root ws1 "./config-ws1.sh"
#docker exec  -it --privileged --user root ws2 "./config-ws2.sh"
#docker exec  -it --privileged --user root ws3 "./config-ws3.sh"
docker exec  -it --privileged --user root inner_gw "./config-inner-gw.sh"
docker exec  -it --privileged --user root outer_gw "./config-outer-gw.sh"
docker exec  -it --privileged --user root php_internal "/config.sh"
docker exec  -it --privileged --user root wp_internal "/config.sh"
docker exec  --privileged --user root public_web_service "./config-web-server.sh"
