// Para usar build.gradle.kts precisariamos de outras configurações
apply {
    from("$rootDir/library-build.gradle")
}

plugins {
    kotlin(KotlinPlugins.serialization) version Kotlin.version
}

// Dependencias que não estão no arquivo acima, mas desejamos utilizar no modulo
dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.heroDataSource)) // Ktor and Factory
    "implementation"(project(Modules.heroDomain)) //Hero module

    "implementation"(Kotlinx.coroutinesCore) // need for flows
}