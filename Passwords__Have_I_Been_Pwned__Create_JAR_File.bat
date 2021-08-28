REM -----------------------------
REM Passwords__Have_I_Been_Pwned
REM -----------------------------
echo off
cls
REM

REM echo %JAVA_HOME%
REM echo %CLASS_PATH%
REM SET "dirlocation=%JAVA_HOME%\bin\"

:STARTCOMPILE
echo "%dirlocation%"
del /q *.class
echo Create the Manifest file:
echo Main-Class: Passwords__Have_I_Been_Pwned >MANIFEST.MF
echo .

echo Compile the Java code:
javac.exe Passwords__Have_I_Been_Pwned.java

echo .
echo Build the JAR file:
jar.exe cfm Passwords__Have_I_Been_Pwned.jar MANIFEST.MF *.class

REM Cleanup:
del /q *.class

:END
echo .
echo Finished!
pause