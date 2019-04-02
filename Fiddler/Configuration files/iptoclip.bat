FOR /F "tokens=4 delims= " %%i in ('route print ^| find " 0.0.0.0"') do set myIP=%%i
echo %myIP%:8888|clip