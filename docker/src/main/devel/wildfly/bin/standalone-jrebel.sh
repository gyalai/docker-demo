#!/bin/bash
export JAVA_OPTS="-javaagent:$REBEL_HOME/jrebel.jar -Drebel.remoting_plugin=true -Xms256m -Xmx512m -XX:MaxPermSize=256m $JAVA_OPTS"
`dirname $0`/standalone.sh $@