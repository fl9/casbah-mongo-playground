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
}
