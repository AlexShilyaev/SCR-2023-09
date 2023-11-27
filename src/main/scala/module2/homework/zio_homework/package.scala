package module2.homework

import zio.{ZIO}
import zio.clock.{currentTime, sleep}
import zio.console._
import zio.duration.durationInt
import zio.random._

import java.io.IOException
import java.util.concurrent.TimeUnit
import scala.language.postfixOps
import scala.util.Try

package object zio_homework {
  /**
   * 1.
   * Используя сервисы Random и Console, напишите консольную ZIO программу которая будет предлагать пользователю угадать число от 1 до 3
   * и печатать в консоль угадал или нет. Подумайте, на какие наиболее простые эффекты ее можно декомпозировать.
   */


  lazy val guessProgram =
  for {
    findValue: Int <- nextIntBetween(1, 4)
    _ <- putStrLn("Угадай загаданное число. Введите число в интервале от 1 до 4?")
    inputValue <- getStrLn
    success <- ZIO.succeed(findValue == Try(inputValue.toInt).getOrElse(0))
    _ <- if (success) putStrLn("Верно") else putStrLn("Не верное. Загаданное число: " + findValue)
  } yield success

  /**
   * 2. реализовать функцию doWhile (общего назначения), которая будет выполнять эффект до тех пор, пока его значение в условии не даст true
   *
   */

def doWhile[R, E, A](effect: ZIO[R, E, A])(cond: A => Boolean): ZIO[R, E, A] =
for {
  effectRes <- effect
  result <- if (cond(effectRes)) ZIO.succeed(effectRes) else doWhile(effect)(cond)
} yield result

  /**
   * 3. Следуйте инструкциям ниже для написания 2-х ZIO программ,
   * обратите внимание на сигнатуры эффектов, которые будут у вас получаться,
   * на изменение этих сигнатур
   */


  /**
   * 3.1 Создайте эффект, который будет возвращать случайеым образом выбранное число от 0 до 10 спустя 1 секунду
   * Используйте сервис zio Random
   */
  lazy val eff =
  for {
    _ <- sleep(1 second)
    result <- nextIntBetween(0, 11)
  } yield result

  /**
   * 3.2 Создайте коллукцию из 10 выше описанных эффектов (eff)
   */
  lazy val effects = List.range(0, 10).map(_ => eff)


  /**
   * 3.3 Напишите программу которая вычислит сумму элементов коллекци "effects",
   * напечатает ее в консоль и вернет результат, а также залогирует затраченное время на выполнение,
   * можно использовать ф-цию printEffectRunningTime, которую мы разработали на занятиях
   */

  def printEffectRunningTime = for {
    start <- currentTime(TimeUnit.MILLISECONDS)
    sum <- ZIO.collectAll(effects).map(_.sum)
    end <- currentTime(TimeUnit.MILLISECONDS)
    _ <- putStrLn("Вычисленная сумма равна " + sum)
    _ <- putStrLn("Время вычисления " + (end - start))

  } yield ()


  /**
   * 3.4 Усовершенствуйте программу 4.3 так, чтобы минимизировать время ее выполнения
   */

  def printEffectRunningTimeSpeed = for {
    start <- currentTime(TimeUnit.MILLISECONDS)
    sum <- ZIO.collectAllPar(effects).map(_.sum)
    end <- currentTime(TimeUnit.MILLISECONDS)
    _ <- putStrLn("Вычисленная сумма равна " + sum)
    _ <- putStrLn("Время вычисления " + (end - start))

  } yield ()


  /**
   * 4. Оформите ф-цию printEffectRunningTime разработанную на занятиях в отдельный сервис, так чтобы ее
   * молжно было использовать аналогично zio.console.putStrLn например
   */

  /**
   * 5.
   * Воспользуйтесь написанным сервисом, чтобы созадть эффект, который будет логировать время выполнения прогаммы из пункта 4.3
   *
   *
   */


  /**
   *
   * Подготовьте его к запуску и затем запустите воспользовавшись ZioHomeWorkApp
   */

  lazy val runApp = (guessProgram).exitCode

}
