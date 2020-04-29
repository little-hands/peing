import domain.{Holder, Item}

case class HolderImpl() extends Holder {

  override def notMigrated(item: Item): Boolean = {
    true // todo
  }

  override def migrated(item: Item): Unit = {
    println(item.title) // todo
  }
}

