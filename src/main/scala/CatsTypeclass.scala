import cats.Show

object CatsTypeclass extends App {
  case class Cat(name: String, age: Int, color: String)


  object PrintableInstance {
    implicit val catInstance = new Show[Cat] {
      override def show(value: Cat): String = value.age + value.name + "----entityInstance"
    }
  }

  implicit class ShowClass[T](value: T) {
    def show(implicit show: Show[T]) =println(show.show(value))
  }

  import PrintableInstance._

  Cat("name", 123, "color").show
}
