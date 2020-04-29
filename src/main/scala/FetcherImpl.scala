import domain.{Fetcher, Item}
import play.api.libs.json.{Json, Reads}

object FetcherImpl {
  def fromPeing(n: Int): String = requests.get(s"https://peing.net/api/v2/items/?type=answered&account=little_hands&page=$n").text

  def mock(n: Int): String = n match {
    case 1 => """{ "items": [ { "id": 1, "body": "あいうえお　かきくけこ　さしすせそ　たちつてと　なにぬねの　はひふへほ", "answer_body": "bar", "answer_created_at": "2020-04-29T12:34:56.000+09:00" } ] }"""
    case 2 => """{ "items": [ { "id": 2, "body": "ほげ @Service\nふが", "answer_body": "ぴよ @Bean(\"conf.xml\")", "answer_created_at": "2020-04-29T12:34:56.000+09:00" } ] }"""
    case 3 => """{ "items": [] } """
  }
}

case class FetcherImpl(f: Int => String) extends Fetcher {

  override def items(): Seq[Item] = fetchWithPagination(1, Seq())

  private def fetchWithPagination(n: Int, acc: Seq[Item]): Seq[Item] = {
    println(s"fetching... ( $n )")

    val items = toItems(f(n))

    if (items.nonEmpty)
      fetchWithPagination(n + 1, acc ++ items)
    else
      acc ++ items
  }

  private def toItems(json: String): Seq[Item] = {
    (Json.parse(json) \ "items").validate[Seq[Data]].get.map(_.toItem)
  }

  case class Data(id: Int, body: String, answer_body: String, answer_created_at: String) {
    def toItem: Item = Item(id.toString, body, answer_body, answer_created_at)
  }

  object Data {
    implicit val jsonReads: Reads[Data] = Json.reads[Data]
  }

}

