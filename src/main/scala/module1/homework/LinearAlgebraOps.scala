package module1.homework

object LinearAlgebraOps{
  def sum(v1: Array[Int], v2: Array[Int]): Array[Int] =
  {
    if(v1.length != v2.length)
    {
      throw new Exception("Operation is not supported");
    }

    var result = new Array[Int](v1.length);

    for (i <- v1.indices) {
      result(i) = v1(i) + v2(i);
    }

    return result;
  }

  def scale(a: Int, v1: Array[Int]): Array[Int] = {

    var result = new Array[Int](v1.length);

    for(i <- v1.indices)
    {
      result(i) = a * v1(i);
    }

    return result;
  }

  def axpy(a: Int, v1: Array[Int], v2: Array[Int]): Array[Int] = {
    if (v1.length != v2.length) {
      throw new Exception("Operation is not supported");
    }

    var result = new Array[Int](v1.length)

    for (i <- v1.indices) {
      result(i) = a * v1(i) + v2(i);
    }

    return result;
  }
}