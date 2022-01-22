plugins {
    java
    // groovy
    checkstyle
    distribution
    id("org.omegat.gradle") version "1.5.7"
}

version = "0.0.1"

omegat {
    version = "5.7.0"
    pluginClass = "org.omegat.gui.glossary.CustomGlossaryRenderer"
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
