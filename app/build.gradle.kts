plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.itheima.topline"
    compileSdk = 34

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
    implementation("com.google.code.gson:gson:2.9.1") // 更新到较新的版本
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(project(":PullToRefresh"))
    // 使用依赖方式引入 Glide，而不是本地 jar 文件
    implementation("com.github.bumptech.glide:glide:4.13.2")
    implementation(libs.okhttp)
    implementation(libs.gson)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(project(":boommenu"))
    implementation(project(":hellocharts-library"))
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.2")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}