// Para usar build.gradle.kts precisariamos de outras configurações
apply {
    from("$rootDir/library-build.gradle")
}

plugins {
    kotlin(KotlinPlugins.serialization) version Kotlin.version
}

// Dependencias que não estão no arquivo acima (Line 3), mas desejamos utilizar no modulo
dependencies {
    "implementation"(project(Modules.heroDomain))

    "implementation"(Ktor.core)
    "implementation"(Ktor.clientSerialization)
    "implementation"(Ktor.android)
}