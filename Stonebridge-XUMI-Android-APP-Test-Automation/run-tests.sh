#!/bin/bash

## This is the ANDROID version of run-tests.sh
## To use in cloud:
## 		Rename this file to "run-tests.sh"
##		Make sure that testdroid.properties has Android configuration in place
## To run locally:
##		Make sure that testdroid.properties has Android configuration in place
##		run "mvn -Dtest=TestName test"
$(date)
echo $(date)
echo "before adding gradle"
sudo add-apt-repository -y ppa:cwchien/gradle
$(date)
echo $(date)
echo "before updating"
sudo apt-get update
$(date)
echo $(date)
echo "before installing gradle"
sudo apt-get -y install gradle
$(date)
echo $(date)
echo "after installing gradle"
reset
export LD_LIBRARY_PATH=/opt/OpenCV/opencv-2.4.9/build/lib 2>&1 | tee /tmp/ls.txt
# Name of the desired test script file without extension
TEST=${TEST:="ReportsTest"}

# SHORT_NAME should be the same as package name of your test script.
# This is used to relocate JUnit xml file and screenshots at the end of this script
if [[ $TEST == *"LoginTest"* ]] 
then
	SHORT_NAME=""
fi
echo "before starting appium"
echo $(date)
## Cloud setup
echo "Starting Appium ..."

#/opt/appium/appium/bin/appium.js --command-timeout 120 >appium.log 2>&1 &
#appium-1.4 -U ${UDID} --command-timeout 120 >appium.log 2>&1 &
appium-1.5 --command-timeout 120 >appium.log 2>&1 &
echo "after starting appium"
echo $(date)
# Wait for appium to fully launch
sleep 10
echo $(date)
echo "before greping appium"
ps -ef|grep appium
echo "after greping appium"
echo $(date)
echo "Extracting tests.zip..."
unzip tests.zip
echo $(date)
echo "Adding correct automationName to testdroid.properties..."
sed -i_bak -e '/automationName/d' testdroid.properties

APILEVEL=$(adb shell getprop ro.build.version.sdk)
APILEVEL="${APILEVEL//[$'\t\r\n']}"
echo "API level is: ${APILEVEL}"

if [ "$APILEVEL" -gt "16" ]; then
	echo "appium.automationName=android" >> testdroid.properties
else
	echo "appium.automationName=selendroid" >> testdroid.properties
fi

## Start test execution
echo "Running test ${TEST}"
rm -r screenshots


#mvn -Dtest=${TEST} test
echo "-Dtest.single=${TEST}".
ls
echo $(date)
gradle -v 2>&1 | tee /tmp/ls.txt
#gradle -Dtest.single=${TEST} 2>&1 | tee /tmp/ls.txt
#gradle -Dtest.single=*tsTest 2>&1 | tee /tmp/ls.txt
gradle 2>&1 | tee /tmp/ls.txt
echo $(date)

echo "after the gradlew command"
echo $(PWD)
mkdir sd
cd sd
mkdir output
cd output
mkdir files
mkdir screenshots
cd ..
cd ..

# Screenshots need to be at screenshots directory in root.
#cp -R Screenshots/ sd/output/files/screenshots/ 2>&1 | tee /tmp/ls.txt
# JUnit results need to be at root as "TEST-all.xml"
cp build/reports/TESTS-TestSuites.xml TEST-all.xml
# Screenshots need to be at screenshots directory in root.
#cp -R Screenshots/ screenshots/ 2>&1 | tee /tmp/ls.txt
cp -R build/reports/tests/ sd/output/files/htmlReport/ 2>&1 | tee /tmp/ls.txt
cp AndroidAutomationLogs.log sd/output/files/runLogs.log 2>&1 | tee /tmp/ls.txt