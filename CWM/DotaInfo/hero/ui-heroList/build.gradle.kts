// Para usar build.gradle.kts precisariamos de outras configurações
apply {
    from("$rootDir/android-library-build.gradle")
}

// Dependencias que não estão no arquivo acima, mas desejamos utilizar no modulo
dependencies {

}