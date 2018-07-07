#!/bin/bash
source header.sh
./auth.sh
#php authentication.php

touch $$$$$$$$-outprotocols-in.json
touch $$$$$$$$-trash.json
curl "$host""$protocols_in" >> $$$$$$$$-outprotocols-in.json
if [ -s $$$$$$$$-outprotocols-in.json ];
then
	if grep -c local $$$$$$$$-outprotocols-in.json >> $$$$$$$$-trash.json || grep -c smb $$$$$$$$-outprotocols-in.json >> $$$$$$$$-trash.json || grep -c http $$$$$$$$-outprotocols-in.json >> $$$$$$$$-trash.json || grep -c http-zipped $$$$$$$$-outprotocols-in.json >> $$$$$$$$-trash.json || grep -c ftp $$$$$$$$-outprotocols-in.json >> $$$$$$$$-trash.json
		then 
			sed -i -e 's/,/\n/g' $$$$$$$$-outprotocols-in.json
			grep -v '^ *\(#\|$\)' $$$$$$$$-outprotocols-in.json
	fi
fi

rm -rf $$$$$$$$-outprotocols-in.json
rm -rf $$$$$$$$-trash.json
exit 1