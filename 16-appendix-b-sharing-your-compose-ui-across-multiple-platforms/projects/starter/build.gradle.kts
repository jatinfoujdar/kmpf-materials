// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.androidLibrary) apply false
  alias(libs.plugins.google.ksp) apply false
  alias(libs.plugins.jetbrains.kotlin) apply false
  alias(libs.plugins.kotlinMultiplatform) apply false
  alias(libs.plugins.jetbrains.kotlin.parcelize) apply false
  alias(libs.plugins.jetbrains.kotlin.serialization) apply false
  alias(libs.plugins.kmp.nativeCoroutines) apply false
}

allprojects {
  repositories {
    google()
    mavenCentral()
    maven {
      url = uri("https://maven.pkg.github.com/cmota/shared-action")
      credentials(PasswordCredentials::class)
      authentication {
        create<BasicAuthentication>("basic")
      }
    }
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}