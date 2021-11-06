// Para usar build.gradle.kts precisariamos de outras configurações
apply {
    from("$rootDir/android-library-build.gradle")
}

// Dependencias que não estão no arquivo acima, mas desejamos utilizar no modulo
dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.heroDomain))
    "implementation"(project(Modules.heroInteractors))

    "implementation"(Coil.coil)
    "implementation"(SqlDelight.androidDriver)
}