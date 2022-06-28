FROM redis:5.0.3-alpine

# default environment
ENV SENTINEL_PORT=26379
ENV SENTINEL_DIR=/redis
ENV SENTINEL_MASTER_PORT=6379
ENV SENTINEL_QUORUM=2
ENV SENTINEL_DOWN_AFTER=1000
ENV SENTINEL_FAILOVER=1000

COPY sentinel.conf /redis/sentinel.conf
COPY sentinel.entrypoint.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/sentinel.entrypoint.sh


RUN chown -R redis:redis /redis/

WORKDIR /redis

ENTRYPOINT ["sentinel.entrypoint.sh"]