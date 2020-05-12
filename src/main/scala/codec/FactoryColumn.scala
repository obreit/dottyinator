package codec
import box.FactoryBuddy

trait FactoryColumn {
  given [T, B](using F: FactoryBuddy[T, B], TColType: BaseColumnType[T]) as DatabaseColumn[B] = 
    mappedColumn[B, T](F.getValue, x => F(x).getOrElse(throw new IllegalArgumentException(s"Couldn't map column '$x'")))
}
object FactoryColumn extends FactoryColumn