#!/bin/bash
#php authentication.php
./auth.sh
#Один компонент
touch components-test1_out.json
./components.sh -p JOB_OUTPUT_ORDER_20161220_134946:OUTPUT_Terra_20161201_1352 >> components-test1_out.json
mv components-test1_out.json repo-tests
cd repo-tests
if diff components-test1_out.json components-test1.json
	then
		echo "Сomplete 1 components test"
fi
rm -rf components-test1_out.json
cd ..

#Несколько компонентов
touch components-test2_out.json
./components.sh -p  JOB_OUTPUT_ORDER_20161220_134946:OUTPUT_Terra_20161201_1352 JOB_OUTPUT_ORDER_20161220_134946:OUTPUT_Terra_20161201_1214 >> components-test2_out.json
mv components-test2_out.json repo-tests
cd repo-tests
if diff components-test2_out.json components-test2.json
	then
		echo "Сomplete 2 components test"
fi
rm -rf components-test2_out.json
cd ..

#Некоректный запрос
touch components-test3_out.json
./components.sh -p  adsffadsfdasffdsfas >> components-test3_out.json
mv components-test3_out.json repo-tests
cd repo-tests
if diff components-test3_out.json components-test3.json
	then
		echo "Сomplete 3 components test"
fi
rm -rf components-test3_out.json
cd ..

#Проверка наличия данных
touch components-test4_out.json
./components.sh -c JOB_OUTPUT_ORDER_20161220_134946:OUTPUT_Terra_20161201_1352 JOB_OUTPUT_ORDER_20161220_134946:OUTPUT_Terra_20161201_1214 fdsfdsfsdf >> components-test4_out.json
mv components-test4_out.json repo-tests
cd repo-tests
if diff components-test4_out.json components-test4.json
	then
		echo "Сomplete 4 components test"
fi
rm -rf components-test4_out.json
cd ..

#Проверка products
touch products-test1_out.json
./products.sh -p JOB_OUTPUT_ORDER_20161220_134946:OUTPUT_Terra_20161201_1352 JOB_OUTPUT_ORDER_20161220_134946:OUTPUT_Terra_20161201_1214 >> products-test1_out.json
mv products-test1_out.json repo-tests
cd repo-tests
if diff products-test1_out.json products-test1.json
	then
		echo "Сomplete products test"
fi
rm -rf products-test1_out.json
cd ..

#Проверка collections
touch collections-test1_out.json
./collections.sh  >> collections-test1_out.json
mv collections-test1_out.json repo-tests
cd repo-tests
if diff collections-test1_out.json collections-test1.json
	then
		echo "Сomplete collections test"
fi
rm -rf collections-test1_out.json
cd ..

#Проверка resouces
touch resources-test1_out.json
./resources.sh -p JOB_OUTPUT_ORDER_20161220_134946:OUTPUT_Terra_20161201_1352 JOB_OUTPUT_ORDER_20161220_134946:OUTPUT_Terra_20161201_1214 >> resources-test1_out.json
mv resources-test1_out.json repo-tests
cd repo-tests
if diff resources-test1_out.json resources-test1.json
	then
		echo "Сomplete resources test"
fi
rm -rf resources-test1_out.json
cd ..

exit 1
