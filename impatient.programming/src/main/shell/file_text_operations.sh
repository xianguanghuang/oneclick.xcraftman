#!/bin/bash -xv

# Changes properties to test.properties
#
# param 1: file to execute replacement in
# param 2: string to replace
# param 3: string that will be replaced
function replace {
  __FILE=$( basename $1)
  cp $1 /tmp/$__FILE.tmp
  sed -i "s%${2}%${3}%g" /tmp/${__FILE}.tmp > /dev/null
  cp /tmp/$__FILE.tmp $1
  rm /tmp/$__FILE.tmp
}


# Replaces or adds properties to targeProperties.sh
#
# param 1: file to execute replacement in
# param 2: string (full line) to replace or add
# param 3: string (full line) that will be replaced or added
function addOrReplace {
  #当想将命令的输出赋值给变量可以使用<var>=${ command } 的方式。其效果和
  #<var> = `command` 是一样的
  __FILE=$( basename $1)
  grep "$2" $1 > /dev/null
  __RETVAL=$?
  if [ $__RETVAL -eq 0 ]; then
    # It's there, replace it.....
    replace $1 "$2" "$3"
  else
    # It's not there, add it.....
    cat $1 > /tmp/$__FILE.tmp
    echo $3 >> /tmp/$__FILE.tmp
    cp /tmp/$__FILE.tmp $1
    rm /tmp/$__FILE.tmp
  fi
}

echo "Before addOrReplace ..."
echo "Hello World" > ./test.properties
chmod 777 ./test.properties
addOrReplace ./test.properties "Hello" "Happy"
echo "After addOrReplace ..."
cat ./test.properties
rm test.properties





