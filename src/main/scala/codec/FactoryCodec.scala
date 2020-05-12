package codec
import box.FactoryBuddy

trait FactoryCodec {
  given [T, B](using F: FactoryBuddy[T, B], TCodec: Codec[T]) as Codec[B] {
    def read(s: String): B = F(TCodec.read(s)).getOrElse(throw new IllegalArgumentException(s"Couldn't read '$s'"))
    def write(b: B): String = TCodec.write(F.getValue(b))
  }
}
object FactoryCodec extends FactoryCodec