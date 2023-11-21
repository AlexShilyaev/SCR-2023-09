package module1.homework
import scala.util.Random

class Basket (initBasket: List[Int])
{
  private var basket: List[Int] = List()
  val countBall = 6;

  if(initBasket.length == countBall)
  {
    basket = initBasket;
  }
  else  {
    basket = List(1, 1, 1, 0, 0, 0)
  }

def select(): Boolean = {
  val first = Random.nextInt(countBall)

  // в зависимости от условия задачи, можно сделать выход при выборе первого шага
  //if(basket(first) == 1) return true;
  //if(basket(first) == 1) return false;

  val second = {
    val rand = Random.nextInt(countBall - 1)
    if (rand >= first)
      rand + 1
    else
      rand
  }

//  List(basket(first), basket(second)).contains(1)

  basket(second) == 1
}
}