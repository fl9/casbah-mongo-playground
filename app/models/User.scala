package models

case class User(email: String, password: String, salt: String)

object User {
  def serialize(user: User) : List[(String, Any)] = {
    val attributes = user.getClass.getDeclaredFields.map(_.getName)
    val values = getValues(user)

    attributes.zip(values).toList
  }

  def getValues(user: User) : List[Any] = {
    user.productIterator.toList
  }
}
