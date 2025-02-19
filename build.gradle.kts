
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
}

group = "example.com"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}
sourceSets {
    main{
        java {
            srcDirs("src/main/kotlin")
        }
        resources {
            srcDirs("src/main/resources")
        }
    }
}
repositories {
    mavenCentral()
}
//application {
//    mainClass.set("com.zinc.money.bff.Application.kt")
//}
ktor {
    fatJar {
        archiveFileName.set("bff.jar")
    }

    docker {
        application
        portMappings.set(listOf(
            io.ktor.plugin.features.DockerPortMapping(
                3000,
                3000,
                io.ktor.plugin.features.DockerPortMappingProtocol.TCP
            )
        ))
        jreVersion.set(JavaVersion.VERSION_17)
        localImageName.set("docker-image")
        imageTag.set("$version-preview")
    }
}

dependencies {
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.serialization.gson)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.openapi)
    implementation(libs.ktor.server.default.headers)
    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.server.status)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.org.yaml.snakeyaml)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
}
tasks.register<Jar>("buildInclusiveJar") {
    archiveFileName.set("bff.jar")
    archiveClassifier.set("fat")

    from(sourceSets.main.get().output)

    // Include dependencies
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })

    manifest {
        attributes["Main-Class"] = "io.ktor.server.netty.EngineMain" // Change this to your main class
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
tasks.named<Copy>("processResources") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}