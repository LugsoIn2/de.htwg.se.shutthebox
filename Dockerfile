FROM hseeberger/scala-sbt
WORKDIR /shutthebox
ADD . /shutthebox
CMD sbt run