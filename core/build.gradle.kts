repositories {
    maven("https://repo.codemc.io/repository/nms")
    maven("https://repo.codemc.io/repository/maven-public")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    // -- spigot api -- (base)
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")

    // -- dream-platform --
    implementation("cc.dreamcode.platform:bukkit:1.13.6")
    implementation("cc.dreamcode.platform:bukkit-config:1.13.6")

    // -- dream-utilties --
    implementation("cc.dreamcode:utilities-adventure:1.5.8")

    // -- dream-notice --
    implementation("cc.dreamcode.notice:bukkit:1.7.1")
    implementation("cc.dreamcode.notice:bukkit-serializer:1.7.1")

    // -- dream-menu --
    implementation("cc.dreamcode.menu:bukkit:1.4.3")
    implementation("cc.dreamcode.menu:bukkit-serializer:1.4.3")

    // -- tasker (easy sync/async scheduler) --
    implementation("eu.okaeri:okaeri-tasker-bukkit:2.1.0-beta.3")
}