package domain

trait Fetcher {
  def items(): Seq[Item]
}
