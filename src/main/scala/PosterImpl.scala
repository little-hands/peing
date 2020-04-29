import domain.{Item, Poster}

import scala.io.Source

object PosterImpl {
  def toGithub: String => Unit = {
    val s = Source.fromFile(".github-token")
    val token = s.getLines().toList.head.trim
    s.close()

    json =>
      requests.post(
        "https://api.github.com/repos/little-hands/ddd-q-and-a-trial/issues",
        data = json,
        headers = Map("Authorization" -> s"token $token")
      )
  }
}

case class PosterImpl(f: String => Unit) extends Poster {

  override def post(item: Item): Unit = {
    f(toJson(item))
  }

  private def toJson(item: Item): String = ujson.Obj("title" -> item.title, "body" -> item.body).render()
}

