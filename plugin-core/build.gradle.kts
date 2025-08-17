import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

repositories {
    maven("https://repo.codemc.io/repository/nms")
    maven("https://repo.codemc.io/repository/maven-public")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    // -- paper api -- (base)
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")

    // -- dream-platform --
    implementation("cc.dreamcode.platform:bukkit:1.13.6")
    implementation("cc.dreamcode.platform:bukkit-config:1.13.6")
    implementation("cc.dreamcode.platform:dream-command:1.13.6")
    implementation("cc.dreamcode.platform:persistence:1.13.6")

    implementation("net.kyori:adventure-text-minimessage:4.22.0")

    // -- dream-notice --
    implementation("cc.dreamcode.notice:bukkit:1.7.1")
    implementation("cc.dreamcode.notice:bukkit-serializer:1.7.1")

    // -- dream-command --
    implementation("cc.dreamcode.command:bukkit:2.2.2")

    // -- menu --
    implementation("dev.triumphteam:triumph-gui:3.1.10")

    // -- tasker (easy sync/async scheduler) --
    implementation("eu.okaeri:okaeri-tasker-bukkit:2.1.0-beta.3")
}

tasks.withType<ShadowJar> {
    archiveFileName.set("Backups-${project.version}.jar")
    mergeServiceFiles()

    relocate("com.cryptomorin", "com.eripe14.backups.libs.com.cryptomorin")
    relocate("eu.okaeri", "com.eripe14.backups.libs.eu.okaeri")
    //relocate("net.kyori", "com.eripe14.backups.libs.net.kyori")

    relocate("cc.dreamcode.platform", "com.eripe14.backups.libs.cc.dreamcode.platform")
    relocate("cc.dreamcode.utilities", "com.eripe14.backups.libs.cc.dreamcode.utilities")
    relocate("cc.dreamcode.menu", "com.eripe14.backups.libs.cc.dreamcode.menu")
    relocate("cc.dreamcode.command", "com.eripe14.backups.libs.cc.dreamcode.command")
    relocate("cc.dreamcode.notice", "com.eripe14.backups.libs.cc.dreamcode.notice")

    relocate("org.bson", "com.eripe14.backups.libs.org.bson")
    relocate("com.mongodb", "com.eripe14.backups.libs.com.mongodb")
    relocate("com.zaxxer", "com.eripe14.backups.libs.com.zaxxer")
    relocate("org.slf4j", "com.eripe14.backups.libs.org.slf4j")
    relocate("org.json", "com.eripe14.backups.libs.org.json")
    relocate("com.google.gson", "com.eripe14.backups.libs.com.google.gson")
}