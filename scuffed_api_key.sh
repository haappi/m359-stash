#!/bin/bash

search="fsdufhsdufhsdufhsdufyhsduifhsduifhsduifhsdiufhsdui"
replace="$1"
input_file="src/main/java/io/github/haappi/HelloApplication.java"
output_file="src/main/java/io/github/haappi/HelloApplicationn.java"

sed "s/${search}/${replace}/g" "${input_file}" > "${output_file}"

mv "${output_file}" "${input_file}"
