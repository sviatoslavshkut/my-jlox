plugins {
    id("java")
    id("eclipse")
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
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

tasks.register<JavaExec>("generateAst") {
  group = "application"
  description = "Generates the AST classes from the grammar."
  classpath = sourceSets.main.get().runtimeClasspath
  mainClass.set("com.example.tool.GenerateAst")
  args = listOf("src/main/java/com/example/lox")
}

tasks.register<JavaExec>("runLox") {
  group = "application"
  description = "Runs the Lox interpreter."
  classpath = sourceSets.main.get().runtimeClasspath
  mainClass.set("com.example.lox.Lox")
  standardInput = System.`in`
  standardOutput = System.out
  errorOutput = System.err

  if (project.hasProperty("args")) {
      args = project.property("args").toString().split(" ")
  } else {
      args = emptyList()
  }
}

tasks.test {
    useJUnitPlatform()
}
