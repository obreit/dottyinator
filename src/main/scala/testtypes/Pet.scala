package testtypes

import box.Factory

type Pet = "cat" | "dog" | "mouse"
object Pet {
  given PetFact as Factory.Aux[String, Pet] = Factory.instance[String, Pet]({
    case "cat" => Some("cat")
    case "dog" => Some("dog")
    case "mouse" => Some("mouse")
    case _ => None
  }, p => p)
}

trait ApplySupport[T, B] extends (T => Option[B]) {
  val supported: Map[T, B]

  def unapply(t: T): Option[B] = supported.get(t)
  def default(t: T): Option[B] = None
  def apply(t: T): Option[B] = unapply(t).orElse(default(t))
}

trait FactorySupport[T, B] extends ApplySupport[T, B] { 
  def repr(b: B): T 
  def enumVals(values: Array[B]): Map[T, B] = values.map(e => (repr(e) -> e)).toMap
  
  given Factory.Aux[T, B] = Factory.instance(apply, repr)
}

enum EnumPet(val str: String) {
  override def toString: String = str

  case Cat extends EnumPet("cat")
  case Dog extends EnumPet("dog")
  case Mouse extends EnumPet("mouse")
  case Unsupported(s: String) extends EnumPet(s)
}
object EnumPet extends FactorySupport[String, EnumPet] {
  def repr(e: EnumPet): String = e.str  
  val supported: Map[String, EnumPet] = enumVals(values)
}