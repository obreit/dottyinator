package codec
import box.Factory

trait FactoryColumn {
  given [B](using F: Factory[B], TColType: BaseColumnType[F.T]) as DatabaseColumn[B] =
    mappedColumn[B, F.T](F.value, x => F.create(x).getOrElse(throw new IllegalArgumentException(s"Couldn't map column '$x'")))

  given [T, B](using F: Factory.Aux[T, B], TColType: MappedColumn[F.T, F.T]) as MappedColumn[F.T, B] = 
    mapColumn[F.T, B](x => F.create(x).getOrElse(throw new IllegalArgumentException(s"Couldn't map column '$x'")), F.value)
}
object FactoryColumn extends FactoryColumn