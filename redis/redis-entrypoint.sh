#!/bin/sh

if [ "$IS_SLAVE" == true ]; then
	redis-server --slaveof $MASTER_HOST $MASTER_PORT
else
    redis-server
fi
