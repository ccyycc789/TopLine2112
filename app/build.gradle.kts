plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.itheima.topline"
    compileSdk = 34
    
    useLibrary("org.apache.http.legacy")

    packaging {
        resources {
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/license.txt"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/notice.txt"
            excludes += "META-INF/*.kotlin_module"
        }
    }

    defaultConfig {
        applicationId = "com.itheima.topline"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // 移除重复的 okhttp 依赖
    // implementation("com.squareup.okhttp3:okhttp:3.12.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.3") // 更新到较新的版本
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    implementation("org.apache.httpcomponents:httpcore:4.4.15")
    implementation("org.apache.httpcomponents:httpmime:4.5.13")
    implementation("com.google.code.gson:gson:2.9.1") // 更新到较新的版本
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(project(":PullToRefresh"))
    // 使用依赖方式引入 Glide，而不是本地 jar 文件
    implementation("com.github.bumptech.glide:glide:4.13.2")
    implementation(libs.androidx.swiperefreshlayout)
    implementation(project(":boommenu"))
    implementation(project(":hellocharts-library"))
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation(files("lib\\CCSDK.jar"))
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.2")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}