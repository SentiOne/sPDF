package io.github.cloudify.scala.spdf

trait TableOfContentConfig extends PageConfig {

	val disableDottedLines = Parameter[Boolean]("disable-dotted-lines")

	val tocHeaderText = Parameter[String]("toc-header-text")

	val tocLevelIndentation = Parameter[String]("toc-level-indentation")

	val disableTocLinks = Parameter[String]("disable-toc-links")

	val tocTextSizeShrink = Parameter[Float]("toc-text-size-shrink")

	val xslStyleSheet = Parameter[String]("xsl-style-sheet")

}

object TableOfContentConfig {

	def toParameters(config: TableOfContentConfig): Seq[String] = {
		import config._
		PageConfig.toParameters(config) ++ Seq(
			disableDottedLines.toParameter,
			tocHeaderText.toParameter,
			tocLevelIndentation.toParameter,
			disableTocLinks.toParameter,
			tocTextSizeShrink.toParameter,
			xslStyleSheet.toParameter
		).flatten
	}

}
