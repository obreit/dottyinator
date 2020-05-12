package testtypes

import box.EnumBuddy

enum EnumPet(val str: String) {
  case Cat extends EnumPet("cat")
  case Dog extends EnumPet("dog")
  case Mouse extends EnumPet("mouse")
  case Unsupported(s: String) extends EnumPet(s)
}
object EnumPet extends EnumBuddy[String, EnumPet] {
  override def default(s: String): Option[EnumPet] = Some(Unsupported(s))
  def getValue(e: EnumPet): String = e.str  
  val supported: Map[String, EnumPet] = enumValues(values)
}

type OpPet = "cat" | "dog" | "mouse"
object OpPet {
  def apply(s: String): Option[OpPet] = s match {
    case p: OpPet => Some(p)
    case _ => None
  }
}