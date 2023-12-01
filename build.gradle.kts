plugins {
    kotlin("jvm") version "1.9.20"
}
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
//    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
//
//tasks.withType<KotlinCompile> {
//    kotlinOptions.jvmTarget = "1.8"
//}
tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}
