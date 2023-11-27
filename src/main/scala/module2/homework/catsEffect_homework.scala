package module2.homework


import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits.toFunctorOps

import scala.io.StdIn
import scala.language.higherKinds
import scala.util.Try

object catsEffectHomework{

  /**
   * Тайп класс для генерации псевдо случайных чисел
   * @tparam F
   */
  trait Random[F[_]] {
    /***
     *
     * @param min значение от (включительно)
     * @param max значение до (исключается)
     * @return псевдо случайное число в заданном диапазоне
     */
    def nextIntBetween(min: Int, max: Int): F[Int]
  }



  object Random{
    /**
     * 1. реализовать сумонер метод для класса Random, в последствии он должен позволить
     * использовать Random например вот так для IO:
     * Random[IO].nextIntBetween(1, 10)
     *
     * @return Random[F]
     */
    def apply[F[_]](implicit value: Random[F]): Random[F] = value;


    /**
     * 2. Реализовать инстанс тайп класса для IO
     */
    implicit val ioRandom = new Random[IO] {

      override def nextIntBetween(min: Int, max: Int): IO[Int] =
        IO.fromEither(Right(scala.util.Random.nextInt(max - min) + min))
    }
  }

  /**
   * Тайп класс для совершения операций с консолью
   * @tparam F
   */
  trait Console[F[_]]{
    def printLine(str: String): F[Unit]
    def readLine(): F[String]
  }

  object Console{
    /**
     * 3. реализовать сумонер метод для класса Console, в последствии он должен позволить
     * использовать Console например вот так для IO:
     * Console[IO].printLine("Hello")
     *
     * @return Console[F]
     */
    def apply[F[_]](implicit value: Console[F]): Console[F] = value;

    /**
     * 4. Реализовать инстанс тайп класса для IO
     */
    implicit val ioConsole = new Console[IO] {
      override def printLine(str: String): IO[Unit] = IO(println(str))

      override def readLine(): IO[String] = IO(StdIn.readLine())
    }
  }

  /**
   * 5.
   * Используя Random и Console для IO, напишите консольную программу которая будет предлагать пользователю угадать число от 1 до 3
   * и печатать в когнсоль угадал или нет. Программа должна выполняться до тех пор, пока пользователь не угадает.
   * Подумайте, на какие наиболее простые эффекты ее можно декомпозировать.
   */

  val guessProgram: IO[Unit] =
    for
    {
      findValue <- Random[IO].nextIntBetween(1, 4)
      _ <- Console[IO].printLine("Угадай загаданное число. Введите число в интервале от 1 до 4.")
      inputValue <- Console[IO].readLine()
      intValue <- IO.delay(Try(inputValue.toInt).getOrElse(0))
      equals <- IO.delay(findValue == intValue)
      _ <- if (equals)
              Console[IO].printLine("Верно")
            else {
              Console[IO].printLine("Не верное. Загаданное число: " + findValue);
              guessProgram
            }
    }  yield ()



  /**
   * 6. реализовать функцию doWhile (общего назначения) для IO, которая будет выполнять эффект до тех пор, пока его значение в условии не даст true
   * Подумайте над сигнатурой, вам нужно принимать эффект и условие относительно его значения, для того чтобы повторять либо заканчивать выполнение.
   */

  def doWhile[A](func: IO[A])(cond: A => Boolean): IO[A] =
    for {
      result <- func
      _ <- if (cond(result)) IO.delay(result) else doWhile(func)(cond)
    } yield result;
}

/**
 * 7. Превратите данный объект в исполняемую cats effect программу, которая будет запускать
 * guessProgram
 */
object HomeworkApp extends IOApp{
  override def run(args: List[String]): IO[ExitCode] = {
    catsEffectHomework.guessProgram.as(ExitCode.Success)
  }
}