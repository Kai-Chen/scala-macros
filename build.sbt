name := "scala-macros"
organization := "com.sorrentocorp"
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
scalaVersion := "2.11.8"
libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
publishTo := Some("Bintray API Realm" at
  "https://api.bintray.com/content/kai-chen/maven/com.sorrentocorp.scala-macros/" + version.value + ";publish=1")
credentials += Credentials(Path.userHome / ".bintray" / ".credentials")
