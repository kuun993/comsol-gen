@echo off  


set taskId=%1
set properties=%2
set jar=%3
set class=%4

comsolbatch %taskId% %properties% -classpathadd %jar% -inputfile %class% -nosave
 
