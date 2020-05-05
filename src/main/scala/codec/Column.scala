package codec
import box.Factory
import box.Factory.{given _}

trait DatabaseColumn[T] {
  def name(t: T): String
  def from(s: String): T
}
object DatabaseColumn {
  given IntColumn as DatabaseColumn[Int] {
    def name(i: Int): String = i.toString
    def from(s: String): Int = s.toInt
  }
  given StringColumn as DatabaseColumn[String] {
    def name(s: String): String = s
    def from(s: String): String = s
  }
  given BoolColumn as DatabaseColumn[Boolean] {
    def name(i: Boolean): String = i.toString
    def from(s: String): Boolean = s.toBoolean
  }
}

trait MappedColumn[T, B] {
  def toRaw(b: B): T
  def fromRaw(t: T): B
}
object MappedColumn {
  def identityMappedColumn[T]: MappedColumn[T, T] = new MappedColumn[T, T] {
    def toRaw(t: T): T = t 
    def fromRaw(t: T): T = t
  }
  given MappedColumn[Int, Int] = identityMappedColumn[Int]
  given MappedColumn[String, String] = identityMappedColumn[String]
}

type BaseColumnType[T] = DatabaseColumn[T]

def mapColumn[U, T](using UC: MappedColumn[U, U])(toWrap: U => T, fromWrap: T => U): MappedColumn[U, T] = new MappedColumn[U, T] {
  def toRaw(t: T): U = UC.toRaw(fromWrap(t))
  def fromRaw(u: U): T = toWrap(UC.fromRaw(u))
}

def mappedColumn[T, U: BaseColumnType](tmap: T => U, tcomap: U => T): BaseColumnType[T] = new DatabaseColumn[T] {
  def name(t: T): String = summon[BaseColumnType[U]].name(tmap(t))
  def from(s: String): T = tcomap(summon[BaseColumnType[U]].from(s))
}
