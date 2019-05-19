#!/bin/sh

arg1=$1

##directory where jar file is located
dir=out/artifacts/parkinglot_gojek_jar

##jar file name
jar_name=parkinglot-gojek.jar

## Permform some validation on input arguments
if [ -z "$1" ] ; then
        java -jar $dir/$jar_name
        exit 1

else
	java -jar $dir/$jar_name $arg1

fi