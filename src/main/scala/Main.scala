import module1.homework.home_work_6.customToString

object Main {

  def main(args: Array[String]): Unit = {
    println("Hello, World!")

    println(customToString("test"))
    println(customToString(false))
    println(customToString(1))
    println(customToString(List(1, 2, 4)))

//val int: Int = 0;

//    val t1 = new Thread{
//      override def run(): Unit ={
//        Thread.sleep(1000)
//        println(s"Hello ${Thread.currentThread().getName}" )
//      }
//    }
//    val t2 = new Thread{
//      override def run(): Unit ={
//        Thread.sleep(2000)
//        println(s"Hello ${Thread.currentThread().getName}" )
//      }
//    }
//    t2.start()
//    t1.start()

//    def rates = {
//       val t1 = ToyFuture(10)(executor.pool1)
//       val t2 = ToyFuture(20)(executor.pool1)
//
//       t1.onComplete{ i1 =>
//         t2.onComplete{i2 =>
//           println(i1 + i2)
//         }
//       }
//
//       val r: ToyFuture[Unit] = for{
//         i1 <- t1
//         i2 <- t2
//       } yield println(i1 + i2)
//    }
//
//    printRunningTime(rates)

//    try_.readFromFile2().foreach(println(_))

    import scala.concurrent.ExecutionContext.Implicits.global

//    val f1 = future.getRatesLocation1
//    val f2 = future.getRatesLocation2
//
//    def sum(v1: Int, v2: Int): Future[Int] = ???
//
//    def zip[T, B](f1: Future[T], f2: Future[B]] = for{
//      r1 <- f1
//      r2 <- f2
//      r3 <- sum(r1, r2)
//    }  yield r3
//
//    future.getRatesLocation1.flatMap(r1 =>
//      future.getRatesLocation2.map(r2 => r1 + r2)
//    )


//
//    future.printRunningTime(
//      future.f7
//    )
//
//    Thread.sleep(4000)


//    val p: executableEncoding.Console[Unit] = executableEncoding.gE
//    val p2: executableEncoding.Console[Unit] = executableEncoding.gE
//
//    val p3: executableEncoding.Console[Unit] = p.flatMap(_ => p2)
//
//    val p4: declarativeEncoding.Console[Unit] = declarativeEncoding.gD
//
//    declarativeEncoding.interpret(p4)



   // zio.Runtime.default.unsafeRun(zioConcurrency.g1)

  }
}

