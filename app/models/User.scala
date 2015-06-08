package models

case class User(name: String)

object User {
  def serialize(user: User) : List[(String, Any)] = {
    val attributes = user.getClass.getDeclaredFields.map(_.getName)
    val values = getValues(user)

    attributes.zip(values).toList
  }

  def getValues(user: User) : Seq[Any] = {
    User.unapply(user).productIterator.toSeq
  }
}
