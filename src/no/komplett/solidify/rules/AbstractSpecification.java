package no.komplett.solidify.rules;
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