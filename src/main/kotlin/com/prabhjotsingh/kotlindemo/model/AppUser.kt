package com.prabhjotsingh.kotlindemo.model

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "app_user")
data class AppUser(
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  val id: UUID = UUID.randomUUID(),

  @Column(nullable = false)
  var firstName: String,

  @Column(nullable = false)
  var lastName: String,

  @Column(unique = true, nullable = false)
  val email: String,

  @Column(unique = true, nullable = false)
  val username: String,

  @Column(nullable = false)
  var password: String,

  @Column(nullable = false)
  var isLoggedIn: Boolean = false
) {
  // Default (no-argument) constructor
  constructor() : this(UUID.randomUUID(), "", "", "", "", "", false)
}
