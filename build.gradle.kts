plugins {
    id("java")
    id("eclipse")
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

eclipse {
  classpath {
    downloadSources = true
    downloadJavadoc = true
  }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<Jar>() {
    manifest {
        attributes["Main-Class"] = "com.example.lox.Lox"
    }
}

tasks.test {
    useJUnitPlatform()
}
