/*
 * This file is generated by jOOQ.
 */
package generated.tables.records


import generated.tables.Customer

import java.lang.Integer
import java.lang.String
import java.math.BigDecimal

import javax.annotation.Generated

import org.jooq.Field
import org.jooq.Record1
import org.jooq.Record5
import org.jooq.Row5
import org.jooq.impl.UpdatableRecordImpl

import scala.Array


/**
 * This class is generated by jOOQ.
 */
@Generated(
  value = Array(
    "http://www.jooq.org",
    "jOOQ version:3.11.11"
  ),
  comments = "This class is generated by jOOQ"
)
class CustomerRecord extends UpdatableRecordImpl[CustomerRecord](Customer.CUSTOMER) with Record5[Integer, String, Integer, String, BigDecimal] {

  /**
   * Setter for <code>public.customer.id</code>.
   */
  def setId(value : Integer) : Unit = {
    set(0, value)
  }

  /**
   * Getter for <code>public.customer.id</code>.
   */
  def getId : Integer = {
    val r = get(0)
    if (r == null) null else r.asInstanceOf[Integer]
  }

  /**
   * Setter for <code>public.customer.name</code>.
   */
  def setName(value : String) : Unit = {
    set(1, value)
  }

  /**
   * Getter for <code>public.customer.name</code>.
   */
  def getName : String = {
    val r = get(1)
    if (r == null) null else r.asInstanceOf[String]
  }

  /**
   * Setter for <code>public.customer.age</code>.
   */
  def setAge(value : Integer) : Unit = {
    set(2, value)
  }

  /**
   * Getter for <code>public.customer.age</code>.
   */
  def getAge : Integer = {
    val r = get(2)
    if (r == null) null else r.asInstanceOf[Integer]
  }

  /**
   * Setter for <code>public.customer.address</code>.
   */
  def setAddress(value : String) : Unit = {
    set(3, value)
  }

  /**
   * Getter for <code>public.customer.address</code>.
   */
  def getAddress : String = {
    val r = get(3)
    if (r == null) null else r.asInstanceOf[String]
  }

  /**
   * Setter for <code>public.customer.salary</code>.
   */
  def setSalary(value : BigDecimal) : Unit = {
    set(4, value)
  }

  /**
   * Getter for <code>public.customer.salary</code>.
   */
  def getSalary : BigDecimal = {
    val r = get(4)
    if (r == null) null else r.asInstanceOf[BigDecimal]
  }

  // -------------------------------------------------------------------------
  // Primary key information
  // -------------------------------------------------------------------------
  override def key : Record1[Integer] = {
    return super.key.asInstanceOf[ Record1[Integer] ]
  }

  // -------------------------------------------------------------------------
  // Record5 type implementation
  // -------------------------------------------------------------------------

  override def fieldsRow : Row5[Integer, String, Integer, String, BigDecimal] = {
    super.fieldsRow.asInstanceOf[ Row5[Integer, String, Integer, String, BigDecimal] ]
  }

  override def valuesRow : Row5[Integer, String, Integer, String, BigDecimal] = {
    super.valuesRow.asInstanceOf[ Row5[Integer, String, Integer, String, BigDecimal] ]
  }
  override def field1 : Field[Integer] = Customer.CUSTOMER.ID
  override def field2 : Field[String] = Customer.CUSTOMER.NAME
  override def field3 : Field[Integer] = Customer.CUSTOMER.AGE
  override def field4 : Field[String] = Customer.CUSTOMER.ADDRESS
  override def field5 : Field[BigDecimal] = Customer.CUSTOMER.SALARY
  override def component1 : Integer = getId
  override def component2 : String = getName
  override def component3 : Integer = getAge
  override def component4 : String = getAddress
  override def component5 : BigDecimal = getSalary
  override def value1 : Integer = getId
  override def value2 : String = getName
  override def value3 : Integer = getAge
  override def value4 : String = getAddress
  override def value5 : BigDecimal = getSalary

  override def value1(value : Integer) : CustomerRecord = {
    setId(value)
    this
  }

  override def value2(value : String) : CustomerRecord = {
    setName(value)
    this
  }

  override def value3(value : Integer) : CustomerRecord = {
    setAge(value)
    this
  }

  override def value4(value : String) : CustomerRecord = {
    setAddress(value)
    this
  }

  override def value5(value : BigDecimal) : CustomerRecord = {
    setSalary(value)
    this
  }

  override def values(value1 : Integer, value2 : String, value3 : Integer, value4 : String, value5 : BigDecimal) : CustomerRecord = {
    this.value1(value1)
    this.value2(value2)
    this.value3(value3)
    this.value4(value4)
    this.value5(value5)
    this
  }

  /**
   * Create a detached, initialised CustomerRecord
   */
  def this(id : Integer, name : String, age : Integer, address : String, salary : BigDecimal) = {
    this()

    set(0, id)
    set(1, name)
    set(2, age)
    set(3, address)
    set(4, salary)
  }
}
