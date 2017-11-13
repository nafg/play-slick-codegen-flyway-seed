import java.io.File

import play.api._
import play.api.inject._
import play.core.DefaultWebCommands

import org.scalatestplus.play.FakeApplicationFactory


trait MyApplicationFactory extends FakeApplicationFactory {

  override def fakeApplication: Application = {
    val env = Environment.simple(new File("."))
    val configuration = Configuration.load(env)
    val context = ApplicationLoader.Context(
      environment = env,
      sourceMapper = None,
      webCommands = new DefaultWebCommands(),
      initialConfiguration = configuration,
      lifecycle = new DefaultApplicationLifecycle()
    )
    val loader = new AppLoader()
    loader.load(context)
  }
}

