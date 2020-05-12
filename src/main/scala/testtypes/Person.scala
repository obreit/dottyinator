package testtypes

import box.FactoryBuddy

case class Person private(name: String, age: Int)
object Person extends FactoryBuddy[(String, Int), Person] {
  def getValue(b: Person): (String, Int) = (b.name, b.age)
  def apply(t: (String, Int)): Option[Person] = Some(Person(t._1, t._2))
}