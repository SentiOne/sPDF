package io.github.cloudify.scala.spdf

trait PageConfig {

	val allow = Parameter[Iterable[String]]("allow")

	val background = Parameter[Option[Boolean]]("background")

	val defaultHeader = Parameter[Boolean]("default-header")

	val encoding = Parameter[String]("encoding", "UTF-8")

	val disableExternalLinks = Parameter[Boolean]("disable-external-links")

	val disableInternalLinks = Parameter[Boolean]("disable-internal-links")

	val disableJavascript = Parameter[Boolean]("disable-javascript")

	val javascriptDelay = Parameter[Int]("javascript-delay")

	val minimumFontSize = Parameter[Int]("minimum-font-size")

	val pageOffset = Parameter[String]("page-offset")

	val password = Parameter[String]("password")

	val printMediaType = Parameter[Option[Boolean]]("print-media-type")

	val disableSmartShrinking = Parameter[Boolean]("disable-smart-shrinking")

	val userStyleSheet = Parameter[String]("user-style-sheet")

	val username = Parameter[String]("username")

	val viewportSize = Parameter[String]("viewport-size")

	val zoom = Parameter[Float]("zoom")

}

object PageConfig {

	def toParameters(config: PageConfig): Seq[String] = {
		import config._
		Seq(
			allow.toParameter,
			background.toParameter,
			defaultHeader.toParameter,
			disableExternalLinks.toParameter,
			disableInternalLinks.toParameter,
			disableJavascript.toParameter,
			disableSmartShrinking.toParameter,
			javascriptDelay.toParameter,
			encoding.toParameter,
			minimumFontSize.toParameter,
			pageOffset.toParameter,
			password.toParameter,
			printMediaType.toParameter,
			userStyleSheet.toParameter,
			username.toParameter,
			viewportSize.toParameter,
			zoom.toParameter
		).flatten
	}

}
