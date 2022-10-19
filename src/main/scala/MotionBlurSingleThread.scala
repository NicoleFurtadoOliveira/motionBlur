import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object MotionBlurSingleThread extends MotionBlurFactory {
  /**
    * Method to start processing the data, this one uses only the current thread
    *
    * @param data            matrix of integers
    * @param numberOfWorkers this parameter should be ignored
    * @return matrix of integers
    */
  override def run(data: Seq[Seq[Int]], numberOfWorkers: Int): Future[IndexedSeq[IndexedSeq[Int]]] = {
    Future {
      val nLines = data.size
      val nColumns = data.head.size
      for (i <- 0 until nLines) yield {
        for (j <- 0 until nColumns) yield calculate(data, i, j)
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
