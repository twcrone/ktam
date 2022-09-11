#!/bin/sh
gradle nativeBinaries
if [ $# -eq 0 ]
  then
  build/bin/native/debugExecutable/ktam.kexe
else
  build/bin/native/debugExecutable/ktam.kexe "$1" "$2"
fi

