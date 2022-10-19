import scala.concurrent.Future

trait MotionBlurFactory {
  /**
    * Method to start processing the data
    *
    * @param data            matrix of integers
    * @param numberOfWorkers number of threads that should work in parallel
    * @return matrix of integers
    */
  def run(data: Seq[Seq[Int]], numberOfWorkers: Int): Future[Seq[Seq[Int]]]
}
