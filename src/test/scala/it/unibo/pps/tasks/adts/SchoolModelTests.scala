package it.unibo.pps.tasks.adts

import org.junit.*
import org.junit.Assert.*
import SchoolModel.*
import BasicSchoolModule.*
import it.unibo.pps.u03.extensionmethods.Sequences.Sequence
import Sequence.*

class schoolTest:
  
  val schoolModule: SchoolModule = BasicSchoolModule
  val john = teacher("John")
  val jessica = teacher("Jessica")
  val math = course("Math")
  val italian = course("Italian")
  val school: School = emptySchool.setTeacherToCourse(john, math)
  val school2: School = school.setTeacherToCourse(jessica, italian)
    .setTeacherToCourse(john, italian)

  @Test def testEmptySchool() =
    assertEquals(schoolImpl(Nil(), Nil(), Nil()), emptySchool)

  @Test def testCourses() =
    assertEquals(Nil(), emptySchool.courses)
    assertEquals(Cons("Math", Nil()), school.courses)
    assertEquals(Cons("Italian", Cons("Math", Nil())), school2.courses)


  @Test def testTeachers() =
    assertEquals(Nil(), emptySchool.teachers)
    assertEquals(Cons("John", Nil()), school.teachers)
    assertEquals(Cons("John", Cons("Jessica", Nil())), school2.teachers)


  @Test def testTeacherToCouse() =
    assertEquals(schoolImpl(Cons(john, Nil()), Cons(math, Nil()), Cons((john, math), Nil())),
      emptySchool.setTeacherToCourse(john, math))

  @Test def testCoursesOfATeacher =
    assertEquals(Nil(), emptySchool.coursesOfATeacher(john))
    assertEquals(Cons(math, Nil()), school.coursesOfATeacher(john))
    assertEquals(Cons(italian, Cons(math, Nil())), school2.coursesOfATeacher(john))
    assertEquals(Cons(italian, Nil()), school2.coursesOfATeacher(jessica))
    assertEquals(Nil(), school2.coursesOfATeacher(teacher("Ned")))

  @Test def testHasTeacher = {
    assertEquals(false, emptySchool.hasTeacher("John"))
    assertEquals(true, school.hasTeacher("John"))
  }

  @Test def testHasCourse = {
    assertEquals(false, emptySchool.hasCourse("Math"))
    assertEquals(true, school.hasCourse("Math"))
  }
