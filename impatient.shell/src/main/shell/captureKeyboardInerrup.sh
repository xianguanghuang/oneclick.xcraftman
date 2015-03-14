#/bin/sh


function control_c
# run if user hits control-c
{
    echo "The script was killed"
    exit 127
}


trap control_c SIGINT

while [ 1 -eq 1 ]; do
	#test=1 
done