#!/bin/bash
source header.sh
./auth.sh
#php authentication.php

touch $$$$$$$$-outprotocols-out.json
touch $$$$$$$$-trash.json
curl "$host""$protocols_out" >> $$$$$$$$-outprotocols-out.json
if [ -s $$$$$$$$-outprotocols-out.json ];
then
	if grep -c local $$$$$$$$-outprotocols-out.json >> $$$$$$$$-trash.json || grep -c smb $$$$$$$$-outprotocols-out.json >> $$$$$$$$-trash.json || grep -c http $$$$$$$$-outprotocols-out.json >> $$$$$$$$-trash.json || grep -c http-zipped $$$$$$$$-outprotocols-out.json >> $$$$$$$$-trash.json || grep -c ftp $$$$$$$$-outprotocols-out.json >> $$$$$$$$-trash.json
		then 
			sed -i -e 's/,/\n/g' $$$$$$$$-outprotocols-out.json
			grep -v '^ *\(#\|$\)' $$$$$$$$-outprotocols-out.json
	fi
fi

rm -rf $$$$$$$$-outprotocols-out.json
rm -rf $$$$$$$$-trash.json
exit 1