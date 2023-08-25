pluginManagement {
  repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
  }
}

rootProject.name = "learn"
include(":androidApp")
include(":desktopApp")
include(":iosAppCompose")

include(":shared")
include(":shared-ui")
include(":shared-action")

includeBuild("plugins/multiplatform-swiftpackage-m1_support")