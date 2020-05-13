package testtypes

import box.EnumBuddy

enum EnumPet(val str: String) {
  case Cat extends EnumPet("cat")
  case Dog extends EnumPet("dog")
  case Mouse extends EnumPet("mouse")
  case Unsupported(s: String) extends EnumPet(s)

  /*
    Simple cases (without parameters) don't override the toString method!
    I.e. 
    Unsupported("abc").toString -> "abc"
    Cat.toString -> "Cat" <-- will always be the name of the enum. 
    
    This issue is being discussed and tracked
    https://github.com/lampepfl/dotty/issues/7227 
  */
  override def toString(): String = str
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