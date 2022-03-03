plugins {
    java
    // groovy
    checkstyle
    distribution
    id("org.omegat.gradle") version "1.5.7"
}

version = "0.0.1"

omegat {
    version = project.property("targetOmegatVersion").toString()
    pluginClass = project.property("mainClass").toString()
}

dependencies {
    packIntoJar("org.apache.commons:commons-lang3:3.12.0")
}

checkstyle {
    isIgnoreFailures = true
    toolVersion = "7.1"
}

distributions {
    main {
        contents {
            from(tasks["jar"], "README.md", "COPYING", "CHANGELOG.md")
        }
    }
}
