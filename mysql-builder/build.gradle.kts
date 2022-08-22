plugins {
    java
}

group = "me.devgabi.sqlbuilder"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(project(":sql-api"))

    testImplementation("org.mockito","mockito-core","2.+" )
    testImplementation("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}