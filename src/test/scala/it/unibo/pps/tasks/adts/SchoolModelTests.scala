package it.unibo.pps.tasks.adts

import org.junit.*
import org.junit.Assert.*
import SchoolModel.*
import BasicSchoolModule.*

class schoolTest:
  
  val schoolModule: SchoolModule = BasicSchoolModule 
  
  @Test def testTeacherName() =
    assertEquals("John", teacher("John").name)