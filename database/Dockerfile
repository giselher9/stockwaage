FROM container.detss.corpintra.net/library/centos-tss
MAINTAINER Sibylle Hekel - sibylle.hekel@daimler.com

ENV nexus_url=http://s415vm1065.detss.corpintra.net:8082/nexus

# Download mongodb packages
RUN curl -o /tmp/mongodb-org-mongos-3.0.3-1.el7.x86_64.rpm ${nexus_url}/content/repositories/thirdparty/org/mongodb/mongodb-org-mongos/3.0.3-1.el7.x86_64/mongodb-org-mongos-3.0.3-1.el7.x86_64.rpm && \
curl -o /tmp/mongodb-org-3.0.3-1.el7.x86_64.rpm ${nexus_url}/content/repositories/thirdparty/org/mongodb/mongodb-org/3.0.3-1.el7.x86_64/mongodb-org-3.0.3-1.el7.x86_64.rpm && \
curl -o /tmp/mongodb-org-server-3.0.3-1.el7.x86_64.rpm ${nexus_url}/content/repositories/thirdparty/org/mongodb/mongodb-org-server/3.0.3-1.el7.x86_64/mongodb-org-server-3.0.3-1.el7.x86_64.rpm &&\
curl -o /tmp/mongodb-org-shell-3.0.3-1.el7.x86_64.rpm ${nexus_url}/content/repositories/thirdparty/org/mongodb/mongodb-org-shell/3.0.3-1.el7.x86_64/mongodb-org-shell-3.0.3-1.el7.x86_64.rpm && \
curl -o /tmp/mongodb-org-tools-3.0.3-1.el7.x86_64.rpm ${nexus_url}/content/repositories/thirdparty/org/mongodb/mongodb-org-tools/3.0.3-1.el7.x86_64/mongodb-org-tools-3.0.3-1.el7.x86_64.rpm

# Install openssl
RUN yum install -y openssl

# Install mongod
RUN rpm -ivh /tmp/mongodb-org-server-3.0.3-1.el7.x86_64.rpm && \
rpm -ivh /tmp/mongodb-org-tools-3.0.3-1.el7.x86_64.rpm && \
rpm -ivh /tmp/mongodb-org-shell-3.0.3-1.el7.x86_64.rpm

# Create data directory
RUN mkdir /data/db -p

EXPOSE  27017
CMD ["mongod"]