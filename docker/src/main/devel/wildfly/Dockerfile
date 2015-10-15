FROM ts/demo-wildfly

MAINTAINER gyalai@mail.thot-soft.com

ADD http://dl.zeroturnaround.com/jrebel-stable-nosetup.zip $JBOSS_HOME/

ADD bin/standalone-jrebel.sh /opt/jboss/wildfly/bin/

USER root
RUN chmod +x /opt/jboss/wildfly/bin/standalone-jrebel.sh
RUN chown -R jboss:jboss /opt/jboss/wildfly/
USER jboss

ENV REBEL_HOME=$JBOSS_HOME/jrebel

RUN unzip $JBOSS_HOME/jrebel-stable-nosetup.zip -d $JBOSS_HOME


CMD ["/opt/jboss/wildfly/bin/standalone-jrebel.sh", "-b", "0.0.0.0"]

