#!/bin/bash
source header.sh
./auth.sh
#php authentication.php

#Данный скприпт соответсвует классу SlotsInController

if [ $# -lt 1 ]
then
	exit 1;
fi

while getopts "c:p:" opt
do
	case $opt in
		#"{[/rest/repo/slots-in],methods=[POST]}" 
		c)
			for n in $@
			do 
				if [ "$i" -ge "$j" ]
					then
						curl -X POST "$host""$slots_in""$n"
					else
						i=2
				fi
			done
		;;
		#"{[/rest/repo/slots-in/{reference}/confirm],methods=[POST]}"
		p)
			for n in $@
			do 
				if [ "$i" -ge "$j" ]
					then
						curl -X POST "$host""$slots_in""$n""$confirm"
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