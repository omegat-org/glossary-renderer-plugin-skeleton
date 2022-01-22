plugins {
    groovy
    distribution
    id("org.omegat.gradle") version "1.5.7"
}

version = "0.0.1"

omegat {
    version = "5.7.0"
    pluginClass = "org.omegat.gui.glossary.CustomGlossaryRenderer"
}

distributions {
    main {
        contents {
            from(tasks["jar"], "README.md", "COPYING", "CHANGELOG.md")
        }
    }
}
