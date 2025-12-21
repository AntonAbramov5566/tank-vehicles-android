package ru.abramov.tank_reference_system

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.abramov.tank_reference_system.data.db.AppDatabase
import ru.abramov.tank_reference_system.data.db.entity.User
import ru.abramov.tank_reference_system.data.storage.UserStorage

@RunWith(AndroidJUnit4::class)
class UserDaoInstrumentedTest {
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun authenticate_returnsUserWhenCredentialsMatch() = runBlocking {
        val passwordHash = UserStorage.hash("secret")
        db.userDao().insert(User(username = "user", password_hash = passwordHash))

        val found = db.userDao().authenticate("user", passwordHash)

        assertNotNull(found)
    }

    @Test
    fun authenticate_returnsNullWhenPasswordWrong() = runBlocking {
        val passwordHash = UserStorage.hash("secret")
        db.userDao().insert(User(username = "user", password_hash = passwordHash))

        val found = db.userDao().authenticate("user", UserStorage.hash("wrong"))

        assertNull(found)
    }

    @Test
    fun authenticate_returnsNullWhenUsernameMissing() = runBlocking {
        val passwordHash = UserStorage.hash("secret")
        db.userDao().insert(User(username = "user", password_hash = passwordHash))

        val found = db.userDao().authenticate("missing", passwordHash)

        assertNull(found)
    }

    @Test
    fun authenticate_matchesExactUser() = runBlocking {
        val hashA = UserStorage.hash("secretA")
        val hashB = UserStorage.hash("secretB")
        db.userDao().insert(User(username = "userA", password_hash = hashA))
        db.userDao().insert(User(username = "userB", password_hash = hashB))

        val found = db.userDao().authenticate("userB", hashB)

        assertNotNull(found)
    }

    @Test
    fun authenticate_rejectsOtherUsersHash() = runBlocking {
        val hashA = UserStorage.hash("secretA")
        val hashB = UserStorage.hash("secretB")
        db.userDao().insert(User(username = "userA", password_hash = hashA))
        db.userDao().insert(User(username = "userB", password_hash = hashB))

        val found = db.userDao().authenticate("userA", hashB)

        assertNull(found)
    }
}
