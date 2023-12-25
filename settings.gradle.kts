pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        // Pre-release artifacts of compose-compiler
        // https://androidx.dev/storage/compose-compiler/repository
        maven("https://androidx.dev/storage/compose-compiler/repository/") {
            name = "compose-compiler-dev"
            content {
                includeGroup("androidx.compose.compiler")
            }
        }
    }
}

rootProject.name = "ComposeFadingEdgeDemo"
include(":app")
 