package com.prabhjotsingh.kotlindemo.controller

import com.prabhjotsingh.kotlindemo.model.AppUser
import com.prabhjotsingh.kotlindemo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

  @GetMapping
  fun getUsers(): ResponseEntity<List<AppUser>> {
    val users = userService.getUsers()
    return ResponseEntity.ok(users)
  }

  @GetMapping("/{id}")
  fun getUserById(@PathVariable id: UUID): ResponseEntity<AppUser> {
    val user = userService.getUserById(id)
    return if (user != null) {
      ResponseEntity.ok(user)
    } else {
      ResponseEntity.notFound().build()
    }
  }

  @PostMapping
  fun createUser(@RequestBody user: AppUser): ResponseEntity<AppUser> {
    val createdUser = userService.createUser(user)
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser)
  }

  @PostMapping("/login")
  fun login(
    @RequestParam(required = false) email: String?,
    @RequestParam(required = false) username: String?,
    @RequestParam password: String
  ): ResponseEntity<Boolean> {
    val result = userService.login(email, username, password)
    return if (result) {
      ResponseEntity.ok(true)
    } else {
      ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false)
    }
  }

  @PostMapping("/logout")
  fun logout(@RequestParam email: String): ResponseEntity<Boolean> {
    val result = userService.logout(email)
    return if (result) {
      ResponseEntity.ok(true)
    } else {
      ResponseEntity.status(HttpStatus.NOT_FOUND).body(false)
    }
  }
  @PutMapping("/{id}")
  fun updateUser(@PathVariable id: UUID, @RequestBody user: AppUser): ResponseEntity<AppUser> {
    val updatedUser = userService.updateUser(id, user)
    return if (updatedUser != null) {
      ResponseEntity.ok(updatedUser)
    } else {
      ResponseEntity.notFound().build()
    }
  }

  @DeleteMapping("/{id}")
  fun deleteUser(@PathVariable id: UUID): ResponseEntity<Unit> {
    val isDeleted = userService.deleteUser(id)
    return if (isDeleted) {
      ResponseEntity.noContent().build()
    } else {
      ResponseEntity.notFound().build()
    }
  }
}
