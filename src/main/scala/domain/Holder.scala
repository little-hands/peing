package domain

trait Holder {
  def notMigrated(item: Item): Boolean

  def migrated(item: Item): Unit
}
