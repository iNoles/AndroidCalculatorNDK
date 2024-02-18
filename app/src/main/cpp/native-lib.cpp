#include <jni.h>

extern "C" JNIEXPORT jint JNICALL
Java_com_jonathansteele_calculatorndk_MainActivity_add(
        JNIEnv* env,
        jobject /* this */,
        jint x,
        jint y) {
        //return an integer
        return x + y;
}

extern "C" JNIEXPORT jint JNICALL
Java_com_jonathansteele_calculatorndk_MainActivity_subtract(
        JNIEnv* env,
        jobject /* this */,
        jint x,
        jint y) {
        //return an integer
        return x - y;
}

extern "C" JNIEXPORT jint JNICALL
Java_com_jonathansteele_calculatorndk_MainActivity_multiply(
        JNIEnv* env,
        jobject /* this */,
        jint x,
        jint y) {
        //return an integer
        return x * y;
}

extern "C" JNIEXPORT jint JNICALL
Java_com_jonathansteele_calculatorndk_MainActivity_divide(
        JNIEnv* env,
        jobject /* this */,
        jint x,
        jint y) {
        //return an integer
        return x / y;
}
