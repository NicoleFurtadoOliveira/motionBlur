import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
import scala.util.{Failure, Success, Try}

object MotionBlurMultiThread extends MotionBlurFactory {
  /**
    * Method to start processing the data, this one should work in parallel
    *
    * @param data            matrix of integers
    * @param numberOfWorkers number of threads that should work in parallel
    * @return matrix of integers
    */
  override def run(data: Seq[Seq[Int]], numberOfWorkers: Int): Future[IndexedSeq[IndexedSeq[Int]]] = {
    require(numberOfWorkers > 0)
    val nLines = data.size
    val nColumns = data.head.size
    implicit val executionContext: ExecutionContextExecutorService = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(numberOfWorkers))
    Future.sequence {
      for (i <- 0 until nLines) yield Future.sequence {
        for (j <- 0 until nColumns) yield Future(calculate(data, i, j))
      }
    }
  }

  private def calculate(data: Seq[Seq[Int]], i: Int, j: Int): Int = {
    var numerator: Double = data(i)(j)
    var denominator = 1
    Seq((i - 1, j), (i + 1, j), (i, j - 1)) foreach (e =>
      Try(data(e._1)(e._2)) match {
        case Success(value) => denominator += 1; numerator += value
        case Failure(_) =>
      })
    math.round(numerator / denominator).toInt
  }

}
