FROM ts/demo-tokumx

MAINTAINER gyalai@mail.thot-soft.com
ADD init/* /tmp/

RUN mkdir -p /dev-data/db

ADD conf/* /etc/

RUN chmod +x /tmp/init.sh

RUN /tmp/init.sh

RUN chown -R tokumx:tokumx /dev-data/db

CMD ["mongod", "--dbpath", "/dev-data/db"]