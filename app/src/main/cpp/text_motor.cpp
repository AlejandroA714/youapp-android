#include <jni.h>
#include <string>
#include <algorithm>

using std::string;

string processText(const std::string& input) {
    string output = input;
    reverse(output.begin(), output.end());
    for (char &c : output) c = toupper(c);
    return output;
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_sv_youapp_app_test_TextEngine_processFrom(
        JNIEnv* env,
        jobject,
        jstring input) {

    const char* nativeInput = env->GetStringUTFChars(input, 0);
    std::string result = processText(std::string(nativeInput));
    env->ReleaseStringUTFChars(input, nativeInput);

    return env->NewStringUTF(result.c_str());
}