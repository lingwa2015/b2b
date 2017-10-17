echo @@@@@@@@@删除Admin的Target@@@@@@@@@
rm -rf ./Admin/target
echo @@@@@@@@@更新代码@@@@@@@@@
svn up

echo [INFO]开始打本地包...

call mvn clean install -Dmaven.test.skip=true -e

echo [INFO]打包结束

pause