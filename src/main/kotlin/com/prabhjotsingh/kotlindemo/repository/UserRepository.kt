package com.prabhjotsingh.kotlindemo.repository

import com.prabhjotsingh.kotlindemo.model.AppUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<AppUser, UUID> {
  fun findByEmail(email: String): AppUser?
  fun findByUsername(username: String): AppUser?
}