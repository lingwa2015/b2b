echo @@@@@@@@@ɾ��Admin��Target@@@@@@@@@
rm -rf ./Admin/target
echo @@@@@@@@@���´���@@@@@@@@@
svn up

echo [INFO]��ʼ�򱾵ذ�...

call mvn clean install -Dmaven.test.skip=true -e

echo [INFO]�������

pause