#!/bin/sh
gradle nativeBinaries
build/bin/native/debugExecutable/ktam.kexe $1
