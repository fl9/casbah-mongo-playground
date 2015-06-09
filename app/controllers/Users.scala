package controllers

import play.api.mvc._
import com.github.tototoshi.play2.scalate._
import org.mindrot.jbcrypt.BCrypt
import repositories.UserRepository
import models.User

class Users extends Controller {
  def newUser = Action(parse.urlFormEncoded) { implicit request =>
    val salt = BCrypt.gensalt()
    val email = request.body.get("email").get.head
    val password = BCrypt.hashpw(request.body.get("password").get.head, salt)

    UserRepository.insert(User(email, password, salt))
    Ok(Scalate.render("main.jade", Map("title" -> "Register thanks", "content" -> "user/successfulRegistered.jade")))
  }

  def register = Action {
    Ok(Scalate.render("main.jade", Map("title" -> "Register", "content" -> "user/register.jade")))
  }

  def login = Action(parse.urlFormEncoded) { implicit request =>
    val email = request.body.get("email").get.head
    val password = request.body.get("password").get.head

    val user = UserRepository.findByEmail(email)

    if(user.isDefined) {
      val salt = user.get.salt
      val passwordCorrect = BCrypt.checkpw(BCrypt.hashpw(password, salt), BCrypt.hashpw(user.get.password, salt))

      if(passwordCorrect) {
        val sessionId = java.util.UUID.randomUUID.toString
        Redirect("/user/secret").withSession("sessionId" -> sessionId)
      } else {
        Redirect("/")
      }
    } else {
      Ok("User existiert nicht")
    }
  }

  def logout = Action {
    Redirect("/").withNewSession
  }

  def afterLogin = Action { request =>
    val sessionId = request.session.get("sessionId")

    if(sessionId.isDefined) {
      Ok(Scalate.render("main.jade", Map("title" -> "User Area", "content" -> "user/afterLogin.jade")))
    } else {
      Unauthorized("Not allowed")
    }
  }
}
