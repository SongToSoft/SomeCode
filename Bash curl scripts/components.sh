#!/bin/bash
#Данный скприпт соответсвует классу ComponentsController
source header.sh
./auth.sh
#php authentication.php

if [ $# -lt 1 ]
then
	exit 1
fi

touch $$$$$$$$-trash.json
while getopts "p:c:u:d:f" opt
do
	case $opt in
		#"{[/rest/repo/components/{resource}],methods=[GET]}"
		p)
			touch $$$$$$$$-outcomponents.json
			for n in $@
			do 
				if [ "$i" -ge "$j" ]
					then
						curl "$host""$comp_prod""$n" >> $$$$$$$$-outcomponents.json
						echo ';' >> $$$$$$$$-outcomponents.json
						echo '' >> $$$$$$$$-outcomponents.json
					else
						i=2
				fi
			done
			if [ -s $$$$$$$$-outcomponents.json ];
				then
					if grep -c JOB_OUTPUT_ORDER $$$$$$$$-outcomponents.json >> $$$$$$$$-trash.json || grep -c OUTPUT_Aqua $$$$$$$$-outcomponents.json >> $$$$$$$$-trash.json
						then 
							sed -i -e 's/,/\n/g' $$$$$$$$-outcomponents.json
							grep -v '^ *\(#\|$\)' $$$$$$$$-outcomponents.json
					fi
			fi
			rm -rf $$$$$$$$-outcomponents.json
		;;
		c)
			touch $$$$$$$$-outcomponents.json
			for n in $@
			do 
				if [ "$i" -ge "$j" ]
					then
						curl "$host""$components""$n" >> $$$$$$$$-outcomponents.json
						echo ';' >> $$$$$$$$-outcomponents.json
						echo '' >> $$$$$$$$-outcomponents.json
					else
						i=2
				fi
			done
			if [ -s $$$$$$$$-outcomponents.json ];
				then
					if grep -c true $$$$$$$$-outcomponents.json >> $$$$$$$$-trash.json || grep -c false $$$$$$$$-outcomponents.json >> $$$$$$$$-trash.json
						then 
							sed -i -e 's/,/\n/g' $$$$$$$$-outcomponents.json
							grep -v '^ *\(#\|$\)' $$$$$$$$-outcomponents.json
					fi
			fi
			rm -rf $$$$$$$$-outcomponents.json
		;;
		#"{[/rest/repo/components/{resource}/download],methods=[GET]}"
		u)
			mkdir download
			cd download
			i=1
			j=2
			for n in $@
			do 
				if [ "$i" -ge "$j" ]
					then
						wget "$host""$comp_prod""$n"
					else
						i=2
				fi
			done
		;;

		#"{[/rest/repo/components/{resource}],methods=[DELETE]}"
		d)
			i=1
			j=2
			for n in $@
			do 
				if [ "$i" -ge "$j" ]
					then
						curl -X DELETE "$host""$components""$n"
					else
						i=2
				fi
			done
		;;
		#Чтение из файла
		f)
		
			cd tests
			touch $$$$$$$$-outcomponents.json
			while read -r line; 
			do
			    if [ "$line" = "${line%#*}" -a "$line" ];
			     then
					curl "$host""$comp_prod""$line" >> $$$$$$$$-outcomponents.json
					echo ';' >> $$$$$$$$-outcomponents.json
					echo '' >> $$$$$$$$-outcomponents.json
			    fi
			done < components-test1.txt
			if [ -s $$$$$$$$-outcomponents.json ];
				then
					if grep -c JOB_OUTPUT_ORDER $$$$$$$$-outcomponents.json >> $$$$$$$$-trash.json || grep -c OUTPUT_Aqua $$$$$$$$-outcomponents.json >> $$$$$$$$-trash.json
						then 
							sed -i -e 's/,/\n/g' $$$$$$$$-outcomponents.json
							grep -v '^ *\(#\|$\)' $$$$$$$$-outcomponents.json
					fi
			fi
			rm -rf $$$$$$$$-outcomponents.json
		;;
		*) 
		;;
	esac
done
rm -rf $$$$$$$$-trash.json


exit 1
