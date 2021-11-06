// Para usar build.gradle.kts precisariamos de outras configurações
apply {
    from("$rootDir/library-build.gradle")
}

plugins {
    kotlin(KotlinPlugins.serialization) version Kotlin.version
    id(SqlDelight.plugin)
}

// Dependencias que não estão no arquivo acima (Line 3), mas desejamos utilizar no modulo
dependencies {
    "implementation"(project(Modules.heroDomain))

    "implementation"(Ktor.core)
    "implementation"(Ktor.clientSerialization)
    "implementation"(Ktor.android)

    "implementation"(SqlDelight.runtime)
}

sqldelight {
    database("HeroDatabase") {
        packageName = "br.com.daniel.ramos.hero_datasource.cache"// Where to find SqlDelight file
        sourceFolders = listOf("sqldelight") // Tells to look for a folder named sqldelight
    }
}