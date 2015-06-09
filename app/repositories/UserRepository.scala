package repositories

import com.mongodb.casbah.Imports._
import models.User

object UserRepository {
  val mongoClient = MongoClient("localhost", 27017)
  val db = mongoClient("test")
  val collection = db("test")

  def insert(user: User) = {
    val test = MongoDBObject(User.serialize(user))

    collection.insert(test)
  }

  def findByEmail(email: String) : Option[User] = {
    val query = MongoDBObject("email" -> email)
    val doc = collection.findOne(query)

    if(doc.isDefined) {
      val password = doc.get("password").toString()
      val salt = doc.get("salt").toString()

      Some(User(email, password, salt))
    } else {
      None
    }
  }
}
