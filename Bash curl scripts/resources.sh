#!/bin/bash
source header.sh
./auth.sh
#php authentication.php

#Данный скприпт соответсвует классу ResourcesController

if [ $# -lt 1 ]
then
	exit 1
fi

touch $$$$$$$$-outresources.json
touch $$$$$$$$-trash.json
while getopts "p:d:" opt
do
	case $opt in
		p)
			for n in $@
			do 
				if [ "$i" -ge "$j" ]
					then
						curl "$host""$resources""$n" >> $$$$$$$$-outresources.json
						echo ';' >> $$$$$$$$-outresources.json
						echo '' >> $$$$$$$$-outresources.json
					else
						i=2
				fi
			done
			if [ -s $$$$$$$$-outresources.json ];
				then
					if grep -c true $$$$$$$$-outresources.json >> $$$$$$$$-trash.json || grep -c false $$$$$$$$-outresources.json >> $$$$$$$$-trash.json
						then 
							sed -i -e 's/,/\n/g' $$$$$$$$-outresources.json
							grep -v '^ *\(#\|$\)' $$$$$$$$-outresources.json
					fi
			fi
		;;

		#"{[/rest/repo/components/{resource}],methods=[DELETE]}"
		d)
			for n in $@
			do 
				if [ "$i" -ge "$j" ]
					then
						curl -X DELETE "$host""$resources""$n"
					else
						i=2
				fi
			done
		;;
		*) 
		;;
	esac
done

rm -rf $$$$$$$$-outresources.json
rm -rf $$$$$$$$-trash.json

exit 1