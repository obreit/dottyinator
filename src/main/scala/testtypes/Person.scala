package testtypes

import box.Factory

case class Person private(name: String, age: Int)
object Person {
  given Factory.Aux[(String, Int), Person] = Factory.instance(x => Some(apply.tupled(x)), x => (x.name, x.age))
}