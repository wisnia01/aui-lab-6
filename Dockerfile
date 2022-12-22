FROM maven:3.8.6-amazoncorretto-19 AS MAVEN_BUILD

COPY ./ ./

RUN mkdir zdjecia
# RUN yum install -y wget\
#     wget https://dl.fedoraproject.org/pub/epel/epel-release-latest-7.noarch.rpm\
#     yum install -y epel-release-latest-7.noarch.rpm\
#     yum install -y nginx

# RUN mkdir -p /usr/share/nginx/html

# COPY /lab4-ui /usr/share/nginx/html

CMD [ "./start.sh" ]
#todo how to install nginx on maven so both apps can run simulaneously