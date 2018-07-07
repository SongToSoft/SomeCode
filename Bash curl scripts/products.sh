#!/bin/bash
source header.sh
./auth.sh
#php authentication.php

#Данный скприпт соответсвует классу ProductsController
if [ $# -lt 1 ]
then
	exit 1
fi
touch $$$$$$$$-outproducts.json
touch $$$$$$$$-trash.json
while getopts "p:d:" opt
do
	case $opt in
		#"{[/rest/repo/products/{resource}],methods=[GET]}"
		p)
			for n in $@
			do 
				if [ "$i" -ge "$j" ]
					then
						curl "$host""$products""$n" >> $$$$$$$$-outproducts.json
						echo ';' >> $$$$$$$$-outproducts.json
						echo '' >> $$$$$$$$-outproducts.json
					else
						i=2
				fi
			done
			if [ -s $$$$$$$$-outproducts.json ];
				then
					if grep -c true $$$$$$$$-outproducts.json >> $$$$$$$$-trash.json|| grep -c false $$$$$$$$-outproducts.json >> $$$$$$$$-trash.json
					then 
						sed -i -e 's/,/\n/g' $$$$$$$$-outproducts.json
						grep -v '^ *\(#\|$\)' $$$$$$$$-outproducts.json
					fi
			fi
		;;
		#"{[/rest/repo/products/{resource}],methods=[DELETE]}" 
		d)
			i=1
			j=2
			for n in $@
			do 
				if [ "$i" -ge "$j" ]
					then
						curl -X DELETE "$host""$products""$n"
					else
						i=2
				fi
			done
		;;
		*) 
		;;
	esac
done

rm -rf $$$$$$$$-outproducts.json
rm -rf $$$$$$$$-trash.json

exit 1