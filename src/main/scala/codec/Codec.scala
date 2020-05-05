package codec

trait Codec[T] {
  def read(s: String): T
  def write(t: T): String
}
object Codec {
  def apply[T: Codec]: Codec[T] = summon[Codec[T]]

  extension StringOps {
    def [T: Codec](s: String).convertTo: T = Codec[T].read(s) 
  }
  extension TOps {
    def [T: Codec](t: T).writeString: String = Codec[T].write(t)
  }

  given Codec[Int] {
    override def read(s: String): Int = s.toInt
    override def write(i: Int): String = i.toString
  }

  given Codec[Boolean] {
    override def read(s: String): Boolean = s.toBoolean
    override def write(b: Boolean): String = b.toString
  }

  given Codec[String] {
    override def read(s: String): String = s
    override def write(s: String): String = s
  }
}