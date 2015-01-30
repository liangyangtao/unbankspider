@echo on



set PATH=%JAVA_HOME%\bin

set EXTRACT_HOME=.


set CLASSPATH=.;%EXTRACT_HOME%\config

FOR %%F IN (%EXTRACT_HOME%\lib\*.jar) DO call :addcp %%F
REM FOR %%F IN (%RAPID_HOME%\lib\beifen\*.jar) DO call :addcp %%F

goto extlibe

:addcp
set CLASSPATH=%CLASSPATH%;%1
goto :eof

:extlibe
java -Xms512M -Xmx1024M com.unbank.UnbankConsole
pause
