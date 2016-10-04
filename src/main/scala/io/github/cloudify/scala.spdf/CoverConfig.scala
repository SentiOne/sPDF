package io.github.cloudify.scala.spdf

trait CoverConfig extends PageConfig {

	val input: String

}

object CoverConfig {

	def toParameters(config: CoverConfig): Seq[String] = {
		import config._
		input +: PageConfig.toParameters(config)
	}

}
