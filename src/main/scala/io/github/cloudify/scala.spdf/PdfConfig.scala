package io.github.cloudify.scala.spdf

import scala.sys.process._
import ParamShow._

/**
 * Holds the configuration parameters of Pdf Kit
 */
trait PdfConfig extends PageConfig {

  /**
   * Options for `wkhtmltopdf` command
   * See `wkhtmltopdf --extended-help` for a description of each option
   */

  @deprecated("Use noPdfCompression instead", "1.3.1")
  val disablePdfCompression = Parameter[Boolean]("disable-pdf-compression")

  val noPdfCompression = Parameter[Boolean]("no-pdf-compression")

  val convertForms = Parameter[Boolean]("forms")

  val grayScale = Parameter[Boolean]("grayscale")

  val lowQuality = Parameter[Boolean]("lowquality")

  val marginBottom = Parameter[String]("margin-bottom")

  val marginLeft = Parameter[String]("margin-left")

  val marginRight = Parameter[String]("margin-right")

  val marginTop = Parameter[String]("margin-top")

  val orientation = Parameter[PageOrientation]("orientation")

  val pageHeight = Parameter[String]("page-height")

  val pageSize = Parameter[String]("page-size")

  val pageWidth = Parameter[String]("page-width")

  val title = Parameter[String]("title")

  val footerCenter = Parameter[String]("footer-center")

  val footerFontName = Parameter[String]("footer-font-name")

  val footerFontSize = Parameter[String]("footer-font-size")

  val footerHtml = Parameter[String]("footer-html")

  val footerLeft = Parameter[String]("footer-left")

  val footerLine = Parameter[Boolean]("footer-line")

  val footerRight = Parameter[String]("footer-right")

  val footerSpacing = Parameter[Float]("footer-spacing")

  val headerCenter = Parameter[String]("header-center")

  val headerFontName = Parameter[String]("header-font-name")

  val headerFontSize = Parameter[String]("header-font-size")

  val headerHtml = Parameter[String]("header-html")

  val headerLeft = Parameter[String]("header-left")

  val headerLine = Parameter[Option[Boolean]]("header-line")

  val headerRight = Parameter[String]("header-right")

  val headerSpacing = Parameter[Float]("header-spacing")

  val outline = Parameter[Option[Boolean]]("outline")

  val outlineDepth = Parameter[Int]("outline-depth")

  val cover = Parameter[CoverConfig]("cover")

  val toc = Parameter[TableOfContentConfig]("toc")

}

object PdfConfig {

  /**
   * An instance of the default configuration
   */
  object default extends PdfConfig

  /**
   * Generates a sequence of command line parameters from a `PdfKitConfig`
   */
  def toParameters(config: PdfConfig): Seq[String] = {
    import config._
    Seq(
      allow.toParameter,
      background.toParameter,
      convertForms.toParameter,
      defaultHeader.toParameter,
      disableExternalLinks.toParameter,
      disableInternalLinks.toParameter,
      disableJavascript.toParameter,
      noPdfCompression.toParameter,
      disableSmartShrinking.toParameter,
      javascriptDelay.toParameter,
      encoding.toParameter,
      footerCenter.toParameter,
      footerFontName.toParameter,
      footerFontSize.toParameter,
      footerHtml.toParameter,
      footerLeft.toParameter,
      footerLine.toParameter,
      footerRight.toParameter,
      footerSpacing.toParameter,
      grayScale.toParameter,
      headerCenter.toParameter,
      headerFontName.toParameter,
      headerFontSize.toParameter,
      headerHtml.toParameter,
      headerLeft.toParameter,
      headerLine.toParameter,
      headerRight.toParameter,
      headerSpacing.toParameter,
      lowQuality.toParameter,
      marginBottom.toParameter,
      marginLeft.toParameter,
      marginRight.toParameter,
      marginTop.toParameter,
      minimumFontSize.toParameter,
      orientation.toParameter,
      outline.toParameter,
      outlineDepth.toParameter,
      pageHeight.toParameter,
      pageOffset.toParameter,
      pageSize.toParameter,
      pageWidth.toParameter,
      password.toParameter,
      printMediaType.toParameter,
      title.toParameter,
      userStyleSheet.toParameter,
      username.toParameter,
      viewportSize.toParameter,
      zoom.toParameter,
      cover.toParameter, // order is important
      toc.toParameter // order is important
    ).flatten
  }

  /**
   * Attempts to find the `wkhtmltopdf` executable in the system path.
   * @return
   */
  def findExecutable: Option[String] = try {
    val os = System.getProperty("os.name").toLowerCase
    val cmd = if(os.contains("windows")) "where wkhtmltopdf" else "which wkhtmltopdf"

    Option(cmd.!!.trim).filter(_.nonEmpty)
  } catch {
    case _: RuntimeException => None
  }

}
