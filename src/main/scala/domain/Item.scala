package domain

case class Item(id: String, q: String, a: String, at: String) {
  val title: String = {
    q.split("\n").head.take(30)
  }

  val body: String =
    s"""### Question
       |> ${"@[a-zA-Z0-9]+".r.replaceAllIn(q, m => s"`${m.group(0)}`").replace("\n", "\n> ")}
       |
       |### Answer
       |$a""".stripMargin
}
