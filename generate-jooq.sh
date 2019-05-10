#!/usr/bin/env bash

sbt pack

cp target/pack/lib/jooq*.jar jooq-generate/
cp target/pack/lib/postgresql-42.2.5.jar jooq-generate/
cp conf/gen-postgre.xml jooq-generate/

java -classpath jooq-generate/*:. org.jooq.codegen.GenerationTool jooq-generate/gen-postgres.xml
