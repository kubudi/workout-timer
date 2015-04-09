resolvers += Resolver.sbtPluginRepo("snapshots")

addSbtPlugin("com.hanhuy.sbt" % "android-sdk-plugin" % "1.3.18")

libraryDependencies += "net.sf.proguard" % "proguard-base" % "5.0"

addSbtPlugin("com.hanhuy.sbt" % "sbt-idea" % "1.7.0-SNAPSHOT")