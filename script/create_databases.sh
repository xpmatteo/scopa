#!/bin/bash

src=db/sql

# no customization needed beyond this point

if [ ! -d "$src" ]; then
  echo "Run this script from the main directory of the project"
  exit 1
fi

rm -rf db/databases/*

script/dbconsole.sh $src/*.sql
script/dbconsole.sh --environment "test" $src/*.sql
