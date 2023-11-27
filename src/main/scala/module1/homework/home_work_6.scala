package module1.homework

object home_work_6 {

  trait Show[T] {
    def customToString(x: T): String
  }
  object Show
  {
    def apply[T](implicit ev: Show[T]): Show[T] = ev

    def fromFunction[T](func: T => String): Show[T] = (value: T) => func(value);

    def fromJvm[T]: Show[T] = (value: T) => value.toString

//    implicit val stringCustomToString = Show.fromFunction("customToString:" + _)


    implicit val stringCustomToString = new Show[String] {
      override def customToString(v: String): String = "customToString:" + v;
    }

    //implicit val intCustomToString = Show.fromJvm

    implicit val intCustomToString = new Show[Int] {
      override def customToString(v: Int): String = v.toString
    }

    implicit val boolCustomToString = new Show[Boolean] {
      override def customToString(v: Boolean): String = v.toString
    }


    implicit def listCustomToString[A: Show]: Show[List[A]] = new Show[List[A]] {
      override def customToString(t: List[A]): String = t.toString()
    }

    implicit val setCustomToString = new Show[Set[_]] {
      override def customToString(v: Set[_]): String = v.toString
    }

    implicit class ShowSyntax[T: Show](a: T) {
      def show: String = Show[T].customToString(a)
    }
  }

  def customToString[T](b: T)(implicit m: Show[T]) = m.customToString(b)

  trait Monad[F[_]] {
    def flatMap[T1, T2](f1: F[T1])(f2: T1 => F[T2]): F[T2];

    def flatten[T](f: F[F[T]]): F[T] = flatMap(f)(x => x);
  }
  object Monad {
    def apply[F[_]](implicit value: Monad[F]): Monad[F] = value;

    implicit def listMonad: Monad[List] = new Monad[List] {
      def flatMap[T1, T2](f1: List[T1])(f2: T1 => List[T2]): List[T2] = f1.flatMap(f2);
    }

    implicit def optionMonad: Monad[Option] = new Monad[Option] {
      def flatMap[T1, T2](f1: Option[T1])(f2: T1 => Option[T2]): Option[T2] = f1 match {
        case Some(x) => f2(x)
        case None => None
      }
    }

    implicit def setMonad: Monad[Set] = new Monad[Set] {
      def flatMap[T1, T2](f1: Set[T1])(f2: T1 => Set[T2]): Set[T2] = f1.flatMap(f2);
    }
  }
}
