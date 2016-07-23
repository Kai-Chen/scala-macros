name := "scala-macros"
organization := "com.sorrentocorp"
version := "0.1-SNAPSHOT"
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
scalaVersion := "2.11.8"
libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
publishTo := Some("Bintray API Realm" at
  "https://api.bintray.com/content/kai-chen/maven/com.sorrentocorp.scala-macros/" + version.value)
credentials += Credentials(Path.userHome / ".bintray" / ".credentials")
