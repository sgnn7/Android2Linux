#!/bin/bash

ant_version="1.8.2"
ant_dir="../tools/apache-ant-${ant_version}/bin"
ant="${ant_dir}/ant"

# Execute unit tests
echo "Building and executing unit tests"
pushd ${unit_test_dir} &> /dev/null
	${ant}
popd &> /dev/null
