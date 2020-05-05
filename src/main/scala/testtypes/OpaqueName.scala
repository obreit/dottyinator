package testtypes

import box.Factory
import box.FactoryCompanion
import box.FactoryCompanionOf

opaque type OpaqueName = String
object OpaqueName extends FactoryCompanionOf[String, OpaqueName]

/*{
  given Factory.Aux[String, OpaqueName] = Factory.of[OpaqueName]
}*/