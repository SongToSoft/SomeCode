#!/bin/bash
source header.sh
./auth.sh
#php authentication.php

#Данный скприпт соответсвует классу SlotsOutController
#Проверка на наличие хотя бы одного спецификатора и параметров
if [ $# -lt 1 ]
then
	exit 1
fi

while getopts "c:p:" opt
do                                 
	case $opt in
		#"{[/rest/repo/slots-out],methods=[POST]}"
		c)
			for n in $@          
			do 
				grep -v '^ *\(#\|$\)' test1.json
				if [ "$i" -ge "$j" ]
					then
						curl -O "$host""$slots_out""$n"
					else
						i=2
				fi
			done

		;;
		#"{[/rest/repo/slots-out/http-zipped/{slotRef}]}"
		p)
			for n in $@          
			do 
				if [ "$i" -ge "$j" ]
					then
						curl -O "$host""$slots_out_zip""$n"
					else
						i=2
				fi
			done
		;;
		*) 
		;;
	esac
done

exit 1
