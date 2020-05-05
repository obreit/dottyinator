package box

trait Creator[B] {
  type T
  def create(t: T): Option[B]
}
trait Extractor[B] {
  type T
  def value(b: B): T
}

abstract class FactoryCompanion[T, B](cstr: T => Option[B], extract: B => T) {
  given Factory.Aux[T, B]  = Factory.instance(cstr, extract)

  def apply(t: T): Option[B] = cstr(t)
}
abstract class FactoryCompanionOf[T, B](using ev: T =:= B) 
  extends FactoryCompanion[T, B](x => identity(Some(ev.apply(x))), x => ev.flip.apply(x))

abstract class FactoryCompanionDefineCstr[T, B](cstr: T => Option[B])(using ev: T =:= B)
  extends FactoryCompanion[T, B](cstr, x => ev.flip(x))

trait Factory[B] extends Creator[B] with Extractor[B] 
object Factory {
  type Aux[T0, B] = Factory[B] { type T = T0 }

  def instance[T0, B](cstr: T0 => Option[B], extract: B => T0): Factory.Aux[T0, B] = new Factory[B] {
    type T = T0
    override def create(t: T0): Option[B] = cstr(t)
    override def value(b: B): T0 = extract(b)
  }  

  def of[T0]: Factory.Aux[T0, T0] = instance(x => Some(identity(x)), identity)

  def withDefaultCreator[T0](extract: T0 => T0): Factory.Aux[T0, T0] = instance(x => Some(identity(x)), extract)
  def withDefaultExtractor[T0](cstr: T0 => Option[T0]): Factory.Aux[T0, T0] = instance(cstr, identity)

  def apply[B](using F: Factory[B]): Factory.Aux[F.T, B] = summon[Factory[B]]

  def create[B](using F: Factory[B])(t: F.T): Option[B] = Factory[B].create(t)
  
  extension on [B](b: B)(using F: Factory[B]) {
    def value: F.T = F.value(b)
  }

  implicit class TOps[T](private val t: T) extends AnyVal {
    def toBox[B](using F: Factory.Aux[T, B]): Option[B] = F.create(t)
  } 
}