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

#-print0 表示以ASCII NUL 来分割每一个输出的文件名。 这样做的好处是当文件名存在特殊字符，如 ‘空格’，xargs 命令很难对其进行分割处理
#当使用 -print0 的时候，xargs 可以对应使用 －0 来分割输入参数
echo "Demo print0"  
find . -type f -print0

# -t 表示需要显示每次要 执行util 命令（例子是 echo），非常方便用于调试
# -0 表示使用 ASCII NUL 来分割，是为了前面的 print0 而使用的
# -n 2 表示传给 util 命令 （例子是 echo） 的参数是两个，默认是5000,
find . -type f -print0 |xargs -t -0 -n 2 echo

echo "xargs with -n 1"
find . -type f -print0 |xargs -t -0 -n 1 echo
echo "xargs without -n"
find . -type f -print0 |xargs -t -0 echo


#RANDOM 是 shell 的一个环境变量，当想取0－9 的 随机数，可以：
echo "Demo RANDOM Enviroment Variable"
echo "$(($RANDOM%9))"

# $(()) 可以看成是一种看成一种另类的取值方式，里面的表达式会产生运算以后的值，而且里面的变量可以省略$ 符号
test=10
echo "$((test%9))"
echo "$(($test%9))"

# set  命令如果后面只是跟与系统无关的值，它就会将值按顺序赋值到$1,2,3,4
# 不带 参数的 set 将会显示所有的变量，通常是系统变量
set 11 22 33 44
# eval 可以让命令扫描两次，第一次的时候，如果发现有 $， 就会先做变量替换。第二次就正常执行命令。 如果没有 eval， 命令只会尝试做一次变量替换
echo "print \$4 without eval"
echo "\$$#"
echo "print 44 with eval which scan the command twice."
eval echo "\$$#"