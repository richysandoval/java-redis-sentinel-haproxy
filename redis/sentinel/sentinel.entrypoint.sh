#!/usr/bin/env sh

ESCAPE_CHAR='\'

sed -i "s/\$SENTINEL_PORT/$SENTINEL_PORT/g" /redis/sentinel.conf
sed -i "s/\$SENTINEL_DIR/$ESCAPE_CHAR${SENTINEL_DIR}/g" /redis/sentinel.conf
sed -i "s/\$SENTINEL_MASTER_HOST/$SENTINEL_MASTER_HOST/g" /redis/sentinel.conf
sed -i "s/\$SENTINEL_MASTER_PORT/$SENTINEL_MASTER_PORT/g" /redis/sentinel.conf
sed -i "s/\$SENTINEL_QUORUM/$SENTINEL_QUORUM/g" /redis/sentinel.conf
sed -i "s/\$SENTINEL_MASTER_NAME/$SENTINEL_MASTER_NAME/g" /redis/sentinel.conf
sed -i "s/\$SENTINEL_DOWN_AFTER/$SENTINEL_DOWN_AFTER/g" /redis/sentinel.conf
sed -i "s/\$SENTINEL_FAILOVER/$SENTINEL_FAILOVER/g" /redis/sentinel.conf
sed -i "s/\$SENTINEL_PARALLEL_SYNC_NUMSLAVES/$SENTINEL_PARALLEL_SYNC_NUMSLAVES/g" /redis/sentinel.conf

printf "================= Running with config =================\n\n"
cat /redis/sentinel.conf
printf "=======================================================\n\n"

redis-server /redis/sentinel.conf --sentinel