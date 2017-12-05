# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM jboss/wildfly:11.0.0.Final

ARG DB_HOST=172.19.0.2
ARG DB_NAME=go-admin
ARG DB_USER=root
ARG DB_PASS=root

ENV JBOSS_HOME /opt/jboss/wildfly

ADD mysql-connector-java-5.1.45.jar /tmp

RUN /bin/sh -c '$JBOSS_HOME/bin/standalone.sh &' && \
  sleep 20 && \
  cd /tmp && \
  #curl --location --output  --url http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.45/mysql-connector-java-5.1.45.jar && \
  $JBOSS_HOME/bin/jboss-cli.sh --connect --command="module add --name=com.mysql --resources=/tmp/mysql-connector-java-5.1.45.jar --dependencies=javax.api,javax.transaction.api" && \
  $JBOSS_HOME/bin/jboss-cli.sh --connect --command="/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql)" && \
  $JBOSS_HOME/bin/jboss-cli.sh --connect --command="data-source add --jndi-name=java:/jboss/MySQLDS --name=MySQLPool --connection-url=jdbc:mysql://${DB_HOST}:3306/${DB_NAME} --driver-name=mysql --user-name=root --password=root" && \
  $JBOSS_HOME/bin/jboss-cli.sh --connect --command=:shutdown
#rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/ $JBOSS_HOME/standalone/log/* && \
#rm /tmp/postgresql-9.4*.jar && \
#rm -rf /tmp/postgresql-*.jar
## Workaround due to https://issues.jboss.org/browse/WFCORE-1501
## RUN rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history/current/

RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#70365 --silent
