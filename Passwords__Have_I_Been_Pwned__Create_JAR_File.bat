REM-----------------------------
REM Passwords__Have_I_Been_Pwned
REM-----------------------------
echo off
cls
REM



REM echo %JAVA_HOME%
REM echo %CLASS_PATH%

SET "dirlocation=%JAVA_HOME%\bin\"
GOTO STARTCOMPILE

echo To determine your Java JDK folder location, what machine are you using:
echo  1.  HP Z220 Desktop
echo  2.  HP Elitebook Laptop
echo  3.  CQUni Computer Labs:
echo  4.  CQUni Computer Labs #2:
echo  5.  Centacare HP laptop
echo  6.  Centacare BriTS3
echo  7.  Quit
CHOICE /C:1234567
echo %ERRORLEVEL%


REM NOTE: These IF tests *must* be done in DESCENDING ORDER of ERRORLEVEL.
if ERRORLEVEL 7 GOTO END
if ERRORLEVEL 6 GOTO Centacare_BriTS3
if ERRORLEVEL 5 GOTO Centacare_HP_laptop
if ERRORLEVEL 4 GOTO CQUni_Computer_Labs2
if ERRORLEVEL 3 GOTO CQUni_Computer_Labs
if ERRORLEVEL 2 GOTO HP_Elitebook_Laptop
if ERRORLEVEL 1 GOTO HP_Z220_Desktop


:HP_Z220_Desktop
SET "dirlocation=C:\Program Files (x86)\Java\jdk1.8.0_112\bin\"
GOTO STARTCOMPILE

:HP_Elitebook_Laptop
SET "dirlocation=C:\Program Files\Java\jdk1.8.0_121\bin\"
GOTO STARTCOMPILE

:CQUni_Computer_Labs
REM 29-Jul-2016  (still using a very OLD version of Java)
SET "dirlocation=C:\Program Files (x86)\Java\jdk1.7.0_67\bin\"
GOTO STARTCOMPILE

:CQUni_Computer_Labs2
REM 29-Jul-2016  (still using a very OLD version of Java)
SET "dirlocation=C:\Program Files (x86)\Java\jdk1.8.0_66\bin\"
GOTO STARTCOMPILE

:Centacare_HP_laptop
SET "dirlocation=C:\Program Files\Java\jdk1.8.0_121\bin\"
GOTO STARTCOMPILE

:Centacare_BriTS3
SET "dirlocation=C:\Program Files\Java\jdk1.8.0_121\bin\"
GOTO STARTCOMPILE


:STARTCOMPILE
echo "%dirlocation%"
del *.class
echo Create the Manifest file:
echo Main-Class: Passwords__Have_I_Been_Pwned >MANIFEST.MF
echo .

echo Compile the Java code:
REM "%dirlocation%javac.exe" ..\00__common_code\Benchmark.java
"%dirlocation%javac.exe" ..\00__common_code\Network.java
copy ..\00__common_code\*.class .
REM "%dirlocation%javac.exe" ..\00__common_code\Moose_Utils.java
REM "%dirlocation%javac.exe" ..\00__common_code\SaveLoadApplicationSettings.java
REM "%dirlocation%javac.exe" ..\00__common_code\StringAndCounter.java
REM "%dirlocation%javac.exe" ..\00__common_code\ParentDescriptionCodeLookup.java
REM "%dirlocation%javac.exe" ..\00__common_code\Icons.java
REM "%dirlocation%javac.exe" ..\00__common_code\FontScalerMouseWheelListener.java
REM "%dirlocation%javac.exe" ..\00__common_code\WebAPI.java
REM "%dirlocation%javac.exe" ..\00__common_code\Checksum.java
copy ..\00__common_code\*.class .

REM Need the above classes in this local DIR so that the SLK class can access their methods.
REM "%dirlocation%javac.exe" ..\00__common_code\SLK.java
REM "%dirlocation%javac.exe" ..\00__common_code\RandomStringPanel.java
REM copy ..\00__common_code\RandomStringPanel.class .
REM "%dirlocation%javac.exe" ..\00__common_code\SQLDatabase.java
REM copy ..\00__common_code\SQLDatabase*.class .
REM "%dirlocation%javac.exe" ..\00__common_code\TeamMember.java
REM copy ..\00__common_code\TeamMember*.class .
REM "%dirlocation%javac.exe" ..\00__common_code\TeamStatusMonitorPanel*.java
REM NOTE: Use copy * to get the FrameListener as well !!!!
REM copy ..\00__common_code\TeamStatusMonitorPanel*.class .
REM "%dirlocation%javac.exe" ..\00__common_code\BannerChangerPanel.java
REM copy ..\00__common_code\BannerChanger*.class .


"%dirlocation%javac.exe" Passwords__Have_I_Been_Pwned.java

echo .
echo Build the JAR file:
REM "%dirlocation%jar.exe" cvfm Passwords__Have_I_Been_Pwned.jar MANIFEST.MF *.class *.png ..\icons\*.gif *.jpg Passwords__Have_I_Been_Pwned*.txt
REM "%dirlocation%jar.exe" cfm Passwords__Have_I_Been_Pwned.jar MANIFEST.MF *.class *.jpg ..\icons\*.gif moods\*.gif quotes.txt "CentacareCQ testimonies.txt"

REM "%dirlocation%jar.exe" cfm Passwords__Have_I_Been_Pwned.jar MANIFEST.MF *.class *.jpg banners\*.* icons\*.* ..\icons\*.gif moods\*.gif quotes.txt "CentacareCQ testimonies.txt"
REM "%dirlocation%jar.exe" cfm Passwords__Have_I_Been_Pwned.jar MANIFEST.MF *.class *.jpg icons\*.* ..\icons\*.gif moods\*.gif quotes.txt "CentacareCQ testimonies.txt"
REM "%dirlocation%jar.exe" cfm Passwords__Have_I_Been_Pwned.jar MANIFEST.MF *.class *.jpg icons\*.* ..\icons\*.gif ..\icons\*.png  quotes.txt "CentacareCQ testimonies.txt"
"%dirlocation%jar.exe" cfm Passwords__Have_I_Been_Pwned.jar MANIFEST.MF *.class

REM
del *.class
del ..\00__common_code\*.class
REM del *.gif

:END
echo .
echo Finished!
pause