pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Multi Module Calorie App"
include(":app")
include(":core:common")
include(":feature:onboarding:ui")
include(":feature:onboarding:domain")
include(":feature:tracker:data")
include(":feature:tracker:domain")
include(":feature:tracker:ui")
include(":core:ui")
include(":core:feature_api")
