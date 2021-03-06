#!/bin/bash -xv

#bash 使用-v 参数的时候，会显示bash 所扫描到的每一个语句，无论是否是在执行，如果语句被执行了，就会在语句执行后的结果
#bash 使用-x 参数的时候，只会在执行的命令前有一个"+" 的符号
function now {
    date +'%H:%M:%S'
}


function header {
    local title=$1
    echo "---------------------------------------------------------------------------"
    #当在字符串里面需要嵌入某个函数的返回结果时，需要使用$(<function_name>) 如: "---$(now)---"
    #当在字符串里面需要嵌入函数自己的局部变量时，需要使用${<function_local_var>) 如: "---${title}---"
    echo "--- $(now) >>> ${title}"
    #也可以使用$<第n个参数> 如: "---$1---", 表示第一个输入参数
    echo "--- $(now) >>> $1"
    echo "---------------------------------------------------------------------------"
}

function loopWhileUsage {

	local i=1
	while [[ $i -le 10 ]]; do
		echo "${i} : HaHa"
		i=$(( $i+1 ))
	done
}

function loopForUsage {
    #in 所指向的集合通常是以空格分割
    #如果想在第一个传进来的参数想带空格 ，可以用双引号标记，如 : "Foundation FoundationAdmin"
    for parameter in $1
    do
        echo ${parameter}
    done
}

function ifUsage {

    if [ -z $NOT_EXIST ]; then
        echo "Variable is Null"
    fi
}

function echoAndAssign {
    local echoOutput=`echo My God`
    echo "${echoOutput}"
}

function functionParamSeq {
    echo "show last command exit status : $?"
    echo "show function or shell parameter counts : $#"
    echo "show function or shell name : $0"
}

function letUsage {
    let _RESULT=${1}+${2}
    echo $_RESULT
}

function whileInfinitLoop {
    #判断语句可以 放在［］ 里面，但是和判断语句要有一个 空格的位置
    while [ 1 -eq 1 ]; do
        #在 while里必须要有一个语句，否则会出错
        test=1 
    done
}

function hput() {
  key=$(echo $2 | sed 's/-/_/g')
  eval "$1""$key"='$3'
}


function hget() {
  key=$(echo $2 | sed 's/-/_/g')
  #将echo 放到最后表示echo的内容是 函数的返回值
  eval echo '${'"$1""$key"'#hash}'
}

function initImpactTable(){
  hput impact adapter-alias "Adapter_Alias"
  hput impact adapter-bwlist "BWList"
  hput impact device-capability "DeviceCapability"
  hput impact foundation "CommonService"
  hput impact integration-msdp "Messaging"
  hput impact location "Location Privacy"
  hput impact messaging "Messaging Adapter_Charging Adapter_Alias Adapter_BWList"
  hput impact messaging-server "Messaging"
  hput impact payment "Payment Adapter_Charging"
  hput impact privacy "Privacy"
}

function pureReturn(){
  #将echo 放到最后表示echo的内容是 函数的返回值
  echo "def"
}



#最简单的函数调用，没有一个参数
now

#调用header 方法，并传入一个参数"No Title"
header "No Title"

loopWhileUsage
ifUsage
echoAndAssign
functionParamSeq 1 2 3
loopForUsage "Foundation FoundationAdmin"
letUsage 20 20
initImpactTable
abc=$(hget impact adapter-alias)
echo $abc
#当要使用函数的返回值的时候，需要 用 eval 或者 $()
abc=$(pureReturn)
echo $abc