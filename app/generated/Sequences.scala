/*
 * This file is generated by jOOQ.
 */
package generated


import java.lang.Integer

import javax.annotation.Generated

import org.jooq.Sequence
import org.jooq.impl.SequenceImpl

import scala.Array


/**
 * Convenience access to all sequences in public
 */
@Generated(
  value = Array(
    "http://www.jooq.org",
    "jOOQ version:3.11.11"
  ),
  comments = "This class is generated by jOOQ"
)
object Sequences {

  /**
   * The sequence <code>public.company_id_seq</code>
   */
  val COMPANY_ID_SEQ : Sequence[Integer] = new SequenceImpl[Integer]("company_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.INTEGER.nullable(false))

  /**
   * The sequence <code>public.customer_id_seq</code>
   */
  val CUSTOMER_ID_SEQ : Sequence[Integer] = new SequenceImpl[Integer]("customer_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.INTEGER.nullable(false))
}
