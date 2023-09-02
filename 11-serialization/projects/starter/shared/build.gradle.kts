import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.jetbrains.kotlin.multiplatform)
  alias(libs.plugins.cash.sqldelight)
}

version = "2.0"

sqldelight {
  databases {
    create("AppDb") {
      packageName.set("data")
    }
  }
}

android {
  compileSdk = libs.versions.android.sdk.compile.get().toInt()

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

  defaultConfig {
    minSdk = libs.versions.android.sdk.min.get().toInt()
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  namespace = "com.kodeco.learn.shared"
}

kotlin {
  // FIXME: Currently not possible to update due to SQLDelight forcing android()
  android()

  jvm("desktop")

  val xcf = XCFramework("SharedKit")

  listOf(
      iosX64(),
      iosArm64(),
      iosSimulatorArm64()
  ).forEach {
    it.binaries.framework {
      baseName = "SharedKit"
      xcf.add(this)

      // FIXME: https://youtrack.jetbrains.com/issue/KT-60230/Native-unknown-options-iossimulatorversionmin-sdkversion-with-Xcode-15-beta-3
      if (System.getenv("XCODE_VERSION_MAJOR") == "1500") {
        linkerOpts += "-ld64"
      }
    }
  }

  sourceSets {
    getByName("commonMain") {
      dependencies {
        implementation(libs.kotlinx.datetime)

        implementation(libs.okio)
        implementation(libs.korio)
      }
    }

    getByName("commonTest") {
      dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }

    getByName("androidMain") {
      dependencies {
        implementation(libs.cash.sqldelight.android)
      }
    }

    getByName("desktopMain") {
      dependencies {
        implementation(libs.cash.sqldelight.jvm)
      }
    }

    getByName("iosX64Main")
    getByName("iosArm64Main")
    getByName("iosSimulatorArm64Main")
    create("iosMain") {
      dependsOn(getByName("commonMain"))

      dependencies {
        implementation(libs.cash.sqldelight.native)
      }

      getByName("iosX64Main").dependsOn(this)
      getByName("iosArm64Main").dependsOn(this)
      getByName("iosSimulatorArm64Main").dependsOn(this)
    }
  }
}

kotlin.sourceSets.all {
  languageSettings.optIn("kotlin.RequiresOptIn")
}