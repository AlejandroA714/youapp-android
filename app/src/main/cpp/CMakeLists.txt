cmake_minimum_required(VERSION 3.22.1)

project("text_engine")

set(CMAKE_SYSTEM_NAME Android)
set(CMAKE_ANDROID_ARCH_ABI arm64-v8a)
set(CMAKE_SYSTEM_VERSION 21)
set(CMAKE_ANDROID_STL_TYPE c++_shared)

add_library(text_engine SHARED
        text_motor.cpp)

target_link_libraries(text_engine
    android
    log)
