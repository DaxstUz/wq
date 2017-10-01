# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\androidsdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


#--------------------------1.实体类---------------------------------

#-keepclasscom.com.bigpush.bean.**{*;}

#--------------------------2.第三方包-------------------------------

#Gson
#-keepattributes Signature
#-keepattributes *Annotation*
#-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }
#-keep class com.google.gson.examples.android.model.** { *; }
#-keep class com.google.gson.* { *;}
#-dontwarncom.google.gson.**

#umeng
#-dontwarncom.umeng.**
#-keepclasscom.umeng.**{*;}
#-keepclassu.aly.**{*;}
#-keepclasscom.google.**{*;}

#nineoldandroids
#-dontwarncom.nineoldandroids.*
#-keepclasscom.nineoldandroids.**{*;}

#weixin
#-dontwarncom.tencent.mm.**
#-keepclasscom.tencent.mm.**{*;}

#JGPUSH
#-dontwarn cn.jpush.**
#-keep class cn.jpush.** { *; }
#-dontwarn com.google.**
#-keep class com.google.protobuf.** {*;}
#-keep class com.google.gson.** {*;}

#-------------------------3.与js互相调用的类------------------------

#-------------------------4.反射相关的类和方法----------------------

#-------------------------5.基本不用动区域--------------------------

#指定代码的压缩级别
-optimizationpasses5

#不去忽略非公共的库类
#-dontskipnonpubliclibraryclasses
#-dontskipnonpubliclibraryclassmembers

# 是否使用大小写混合
#-dontusemixedcaseclassnames

#预校验
#-dontpreverify

#混淆时是否记录日志
#-verbose

#忽略警告，避免打包时某些警告出现
-ignorewarning

# 混淆时所采用的算法
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#保护注解
#-keepattributes *Annotation*

#记录生成的日志数据,gradle build时在本项目根目录输出
#apk 包内所有 class 的内部结构
#-dump class_files.txt
#未混淆的类和成员
#-printseeds seeds.txt
#列出从 apk 中删除的代码
#-printusage unused.txt
#混淆前后的映射
#-printmapping mapping.txt

#-----------------------------6.默认保留区-----------------------

# 保持哪些类不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService


#如果有引用v4、v7包可以添加下面这行
-keep publicclass*extendsandroid.support.*

-keepclasseswithmembersclass*{
         public(android.content.Context,android.util.AttributeSet);
         public(android.content.Context,android.util.AttributeSet,int);
}

-keepclassmembers class * implements java.io.Serializable {
        static final long serialVersionUID;
        private static final java.io.ObjectStreamField[] serialPersistentFields;
        private void writeObject(java.io.ObjectOutputStream);
        private void readObject(java.io.ObjectInputStream);
        java.lang.Object writeReplace();
        java.lang.Object readResolve();
}

# 保持 native 方法不被混淆
-keepclasseswithmembernamesclass*{
        native;
}

## 保持自定义控件类不被混淆
#-keepclasseswithmembersclass*{
#       public(android.content.Context,android.util.AttributeSet);
#}
## 保持自定义控件类不被混淆
#-keepclasseswithmembersclass*{
#       public(android.content.Context,android.util.AttributeSet,int);
#}
## 保持自定义控件类不被混淆
#-keepclassmembersclass*extendsandroid.app.Activity{
#       publicvoid*(android.view.View);
#}

# 保持枚举 enum 类不被混淆
-keepclassmembersenum*{
       publicstatic**[]values();
       publicstatic**valueOf(java.lang.String);
}

# 保持 Parcelable 不被混淆
-keepclass*implementsandroid.os.Parcelable{
       publicstaticfinalandroid.os.Parcelable$Creator *;
}

#---------------------------7.webview-----------------------

-keepclassmembersclassfqcn.of.javascript.interface.for.webview{
       public*;
}

-keepclassmembersclass*extendsandroid.webkit.webViewClient{
       publicvoid*(android.webkit.WebView,java.lang.String,android.graphics.Bitmap);
       publicboolean*(android.webkit.WebView,java.lang.String);
}

-keepclassmembersclass*extendsandroid.webkit.webViewClient{
       publicvoid*(android.webkit.webView,jav.lang.String);
}

#-----------------------------end-------------------------------




#百川代码混淆
-keepattributes Signature
    -keep class sun.misc.Unsafe { *; }
    -keep class com.taobao.** {*;}
    -keep class com.alibaba.** {*;}
    -keep class com.alipay.** {*;}
    -dontwarn com.taobao.**
    -dontwarn com.alibaba.**
    -dontwarn com.alipay.**
    -keep class com.ut.** {*;}
    -dontwarn com.ut.**
    -keep class com.ta.** {*;}
    -dontwarn com.ta.**
    -keep class org.json.** {*;}
    -keep class com.ali.auth.**  {*;}
