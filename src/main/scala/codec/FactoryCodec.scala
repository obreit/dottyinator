package codec
import box.Factory

trait FactoryCodec {

  given [B](using F: Factory[B], TCodec: Codec[F.T]) as Codec[B] {
    def read(s: String): B = {
      val t = TCodec.read(s)
      F.create(t).getOrElse(throw new IllegalArgumentException(s"Couldn't read '$t'"))
    }
    def write(b: B): String = TCodec.write(F.value(b))
  }
}
object FactoryCodec extends FactoryCodec