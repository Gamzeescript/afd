#!/bin/bash
export APP_HOME=$(dirname $(realpath $0))

export JAVA_HOME="$JAVA_HOME"
export PATH=$JAVA_HOME/bin:$PATH
export JARFILE="./target/gramatica-1.0-SNAPSHOT.jar"
export FileName=exit"archivo.afd"

ejecutarPrograma () {

    if [ -n "$1" ]
    then
        $JAVA_HOME/bin/java -jar $JARFILE $1
    else
        echo "Forma de uso $0 -afd|-afn|-check|-min"
    fi

}


if [ -n "$JAVA_HOME" ]
then
    echo "Empleando $JAVA_HOME"

    ejecutarPrograma $1

else
    echo "No se encontro la variable \$JAVA_HOME, favor definirla"
fi

