plugins {
    java
}

group = "com.lorenzoog"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {


    testImplementation("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}