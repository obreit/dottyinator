package box

trait FactoryBuddy[T, B] extends (T => Option[B]) {
  def getValue(b: B): T

  given Eql[B, B] = Eql.derived

  given FactoryBuddy[T, B] = this
}

//Usefulness/Stability?
// B -> Some[T] (not Option[T] otherwise case Some(Box(t)) gives wrong compiler warning)
// for opaque types
trait UnapplyBuddy[T, B] {
  def unapply(b: B): Some[T]
}

trait EnumBuddy[T, E <: Enum] extends FactoryBuddy[T, E] {
  val supported: Map[T, E] 

  final def enumValues(values: Array[E]): Map[T, E] = values.map(e => (getValue(e) -> e)).toMap

  final def unapply(t: T): Option[E] = supported.get(t)
  def default(t: T): Option[E] = None

  final override def apply(t: T): Option[E] = unapply(t).orElse(default(t))
}

trait FactoryBuddyIdentity[T, B](using EQ: T =:= B) extends FactoryBuddy[T, B] with UnapplyBuddy[T, B] {
  override def getValue(b: B): T = EQ.flip(b)
  // better to not provide a default definition???
  override def apply(t: T): Option[B] = Some(EQ(t))
  override def unapply(b: B): Some[T] = Some(getValue(b))
}

extension on [T, B](b: B)(using F: FactoryBuddy[T, B]) {
  def value: T = F.getValue(b)
}

implicit class TOps[T](private val t: T) extends AnyVal {
  def toBox[B](using F: FactoryBuddy[T, B]): Option[B] = F(t)
} 