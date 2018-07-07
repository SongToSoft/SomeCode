#!/bin/bash
source header.sh
touch $$$$$$$$-output-slot.json
echo "{" >> $$$$$$$$-output-slot.json
echo " " >> $$$$$$$$-output-slot.json
echo "	{" >> $$$$$$$$-output-slot.json
echo "		"$1":" >> $$$$$$$$-output-slot.json
for n in $@
do 
 	if [ "$i" -ge "$j" ]
		then
			echo "					"$n"" >> $$$$$$$$-output-slot.json
		else
			i=2
	fi
done
echo "	}" >> $$$$$$$$-output-slot.json
echo "	protolocs: "http-zipped" " >> $$$$$$$$-output-slot.json
echo "}" >> $$$$$$$$-output-slot.json

exit 1