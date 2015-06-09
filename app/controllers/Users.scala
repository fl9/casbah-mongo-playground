package controllers

import play.api.mvc._
import com.github.tototoshi.play2.scalate._

class Users extends Controller {
  def newUser = Action(parse.urlFormEncoded) { implicit request =>
    val email = request.body.get("email").get.head
    val password = request.body.get("password").get.head

    Ok(Scalate.render("user/successfulRegistered.jade", Map("email" -> email, "password" -> password)))
  }

  def register = Action {
    Ok(Scalate.render("user/register.jade"))
  }

  def login = Action {
    Ok("TODO")
  }
}
