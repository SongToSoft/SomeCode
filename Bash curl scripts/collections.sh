#!/bin/bash
#Данный скприпт соответсвует классу CollectionsController
source header.sh
./auth.sh
#php authentication.php

#"{[/rest/repo/collections],methods=[GET]}"
touch $$$$$$$$-outcollections.json
touch $$$$$$$$-trash.json
if [ $# -lt 1 ]
then
	curl "$host""$collections" >> $$$$$$$$-outcollections.json
	if [ -s $$$$$$$$-outcollections.json ];
		then
			if grep -c SML $$$$$$$$-outcollections.json >> $$$$$$$$-trash.json  || grep -c TIG $$$$$$$$-outcollections.json >> $$$$$$$$-trash.json
				then 
					sed -i -e 's/,/\n/g' $$$$$$$$-outcollections.json
					grep -v '^ *\(#\|$\)' $$$$$$$$-outcollections.json
			fi
	fi
fi

#"{[/rest/repo/collections/{resource:.+}],methods=[GET]}"
while getopts "c:" opt
do
	case $opt in
		c)
			for n in $@
			do 
				if [ "$i" -ge "$j" ]
					then
						curl "$host""$collect_prod""$n" >> $$$$$$$$-outcollections.json #Непосредственно обращение к репозиторию
						echo ';' >> $$$$$$$$-outcollections.json
						echo '' >> $$$$$$$$-outcollections.json
					else
						i=2
				fi
			done
			if [ -s $$$$$$$$-outcollections.json ];
				then
					if grep -c SML $$$$$$$$-outcollections.json >> $$$$$$$$-trash.json || grep -c TIG $$$$$$$$-outcollections.json >> $$$$$$$$-trash.json
						then 
							sed -i -e 's/,/\n/g' $$$$$$$$-outcollections.json
							grep -v '^ *\(#\|$\)' $$$$$$$$-outcollections.json
					fi
			fi
		;;
		*) 
		;;
	esac
done

rm -rf $$$$$$$$-outcollections.json
rm -rf $$$$$$$$-trash.json

exit 1