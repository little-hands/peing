import FetcherImpl._
import PosterImpl.toGithub
import domain.{Fetcher, Holder, Poster}

object Main extends App {

    apply(
      FetcherImpl(mock),
      PosterImpl(println),
      HolderImpl()
    )
//  apply(
//    FetcherImpl(fromPeing),
//    PosterImpl(toGithub),
//    HolderImpl()
//  )

  def apply(fetcher: Fetcher, poster: Poster, holder: Holder): Unit = {
    fetcher.items()
      .filter(holder.notMigrated)
      .sortBy(_.at)
      .foreach {
        item => {
          poster.post(item)
          holder.migrated(item)
        }
      }

  }

}
