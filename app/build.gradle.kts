plugins {
    java
    application
    kotlin("jvm") version "1.7.10"
    id("org.openjfx.javafxplugin").version("0.0.13")

}

group = "edu.austral.dissis.chess"
version = "1.0.0"

repositories {
//    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/austral-ingsis/chess-ui")
        credentials {
            username = System.getenv("GITHUB_USER") ?: project.property("gpr.user") as String
            password = System.getenv("GITHUB_TOKEN") ?: project.property("gpr.token") as String
        }
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("edu.austral.dissis.chess:chess-ui:1.0.0")
    implementation("org.yaml:snakeyaml:2.0") // Asegúrate de usar la versión más reciente de SnakeYAML

}

javafx {
    version = "18"
    modules = listOf("javafx.graphics")
}

application {
    // Define the main class for the application.
    mainClass.set("edu.austral.dissis.chess.AppKt")
}
