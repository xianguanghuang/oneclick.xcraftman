#!/bin/bash 

#如果echo 命令后面带上-n 参数，表示显示语句后不需要带换行符号
echo -n "Hello world"
echo "Keep Going"


echo "Hello world without -n"
echo "Keep Goging"

#如果grep的结果是存在的,"$?" 就会返回0 ，否则是非0 
#grep -w 表示按照"word" 来查
ls -talrh | grep -w command ; if [ $? -ne 0 ]; then echo "Not Found."; fi



#-s 参数的作用是截掉所有的domain 信息
hostname -s
hostname

#-d 表示分隔符 ，f3 表示 取第三个分割后的field
echo 1,2,3,4 | cut -d ',' -f3

Myname="XianGuang"
#export 后的变量，只能在当前shell 或者 它的 subshell内访问。
#比如说：如果我在命令控制台运行了一个带有export语句的 shell，我在控制台依然无法得到export的变量，因为控制台是shell的父进程
export Myname
./for_sub_shell_invocation_only.sh