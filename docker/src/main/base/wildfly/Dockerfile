FROM jboss/base-jdk:8

MAINTAINER gyalai@mail.thot-soft.com


# Set the WILDFLY_VERSION env variable
ENV WILDFLY_VERSION 8.2.0.Final

# Add the WildFly distribution to /opt, and make wildfly the owner of the extracted tar content
# Make sure the distribution is available from a well-known place
RUN cd $HOME && curl http://download.jboss.org/wildfly/$WILDFLY_VERSION/wildfly-$WILDFLY_VERSION.tar.gz | tar zx && mv $HOME/wildfly-$WILDFLY_VERSION $HOME/wildfly

# Set the JBOSS_HOME env variable
ENV JBOSS_HOME /opt/jboss/wildfly

# Expose the ports we're interested in
EXPOSE 8080 9990 8787

# Set the default command to run on boot
# This will boot WildFly in the standalone mode and bind to all interface
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]


RUN /opt/jboss/wildfly/bin/add-user.sh admin almafa1234 --silent

ADD bin /opt/jboss/wildfly/bin
ADD standalone /opt/jboss/wildfly/standalone

USER root

RUN chown -R jboss:jboss /opt/jboss/wildfly

USER jboss

ENV DB_NAME demo
