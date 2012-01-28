package no.komplett.solidify.specification;


/**
 * Implementing the Specification pattern to avoid using a lot of if clauses to filter objects with certain parameters
 * 
 * @author eivind
 *
 */
public abstract class AbstractSpecification implements Specification {

  public abstract boolean isSatisfiedBy(Object o);

  public Specification and(final Specification specification) {
    return new AndSpecification(this, specification);
  }

  public Specification or(final Specification specification) {
    return new OrSpecification(this, specification);
  }

  public Specification not(final Specification specification) {
    return new NotSpecification(specification);
  }
}