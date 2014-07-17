#!/bin/bash

# 一个不断监听服务器端口的例子，120秒后timeout
# param 1: port number of server
function waitUntilServerStart {
    local portNumber=$1

    #header "waitUntilServerStar ${portNumber}"

    echo -n "Wait until server starts on port ${portNumber}"
    
    local started=false
    local timeout=120
    while [[ $started == false ]]; do
        if [ `netstat -na|grep -w $portNumber | grep LISTEN |wc -l` -gt 0 ]; then
	    started=true
	    echo "Server started at port $portNumber"
        else
	    sleep 1
	    timeout=$((timeout - 1))
	    echo -n "."
        fi
	if [[ $timeout -eq 0 ]]; then        
	    echo "Server could not be started at $portNumber"
	    exit 255
	fi
    done
}

waitUntilServerStart 8080
