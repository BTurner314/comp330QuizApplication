name := "comp330QuizApplication"

version := "0.1"

javacOptions in compile += "-Xlint:all"

javaOptions += "-enableassertions"

libraryDependencies ++= Seq(
  "com.googlecode.json-simple" % "json-simple" % "1.1.1",
  "com.novocode"       %  "junit-interface"      % "0.11" % Test
)

enablePlugins(JavaAppPackaging)

val osName: SettingKey[String] = SettingKey[String]("osName")

osName := (System.getProperty("os.name") match {
    case name if name.startsWith("Linux") => "linux"
    case name if name.startsWith("Mac") => "mac"
    case name if name.startsWith("Windows") => "win"
    case _ => throw new Exception("Unknown platform!")
})

libraryDependencies += "org.openjfx" % "javafx-base" % "11-ea+25" classifier osName.value

libraryDependencies += "org.openjfx" % "javafx-controls" % "11-ea+25" classifier osName.value

libraryDependencies += "org.openjfx" % "javafx-fxml" % "11-ea+25" classifier osName.value

libraryDependencies += "org.openjfx" % "javafx-graphics" % "11-ea+25" classifier osName.value

libraryDependencies += "com.google.firebase" % "firebase-admin" % "4.0.1"
